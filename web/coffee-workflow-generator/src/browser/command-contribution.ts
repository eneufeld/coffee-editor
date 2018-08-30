import { CommandContribution, CommandRegistry, MenuContribution, MenuModelRegistry, Command, SelectionService } from "@theia/core/lib/common";
import { injectable, inject } from "inversify";

import { Workspace } from "@theia/languages/lib/common";
import { EditorManager } from "@theia/editor/lib/browser";

import { UriAwareCommandHandler, UriCommandHandler } from "@theia/core/lib/common/uri-command-handler"
import URI from "@theia/core/lib/common/uri";
// import { MiniBrowserOpenHandler } from "@theia/mini-browser/lib/browser/mini-browser-open-handler";
// import { IFileServer } from "coffee-workflow-analyzer-editors/lib/common/request-file-protocol";
import { WorkspaceStorageServiceFilesystem } from "coffee-workspace-filesystem-storage-service/lib/browser/workspace-storage-service-filesystem";
import { CoffeeModel } from './CofeeModel';
import { Random } from "@phosphor/coreutils";
export const GENERATE_WORKFLOW_COMMAND: Command = {
    id: "workflow.generate.command",
    label: "Generate workflow model"
}
@injectable()
export class WorkflowGenerateCommandContribution implements CommandContribution, MenuContribution {

    constructor(
        @inject(Workspace) protected readonly workspace: Workspace,
        @inject(EditorManager) protected readonly editorManager: EditorManager,
        @inject(SelectionService) protected readonly selectionService: SelectionService,
        // @inject(MiniBrowserOpenHandler) private readonly openHandler: MiniBrowserOpenHandler,
        // @inject(IFileServer) private readonly fileServer: IFileServer,
        @inject(WorkspaceStorageServiceFilesystem) private readonly workpaceFilesystemService: WorkspaceStorageServiceFilesystem
    ) { }

    registerMenus(menus: MenuModelRegistry): void {

        menus.registerMenuAction([...['navigator-context-menu'], '0_addition'], {
            commandId: GENERATE_WORKFLOW_COMMAND.id
        });
    }

    registerCommands(registry: CommandRegistry): void {
        registry.registerCommand(GENERATE_WORKFLOW_COMMAND, this.newUriAwareCommandHandler({
            execute: async (uri: URI) => {
                const jFilePath = uri.path.toString();
                const jFileContent = this.workpaceFilesystemService.readFileContent(new URI(jFilePath));

                // const outputFilePath = uri.path.toString().replace(".json", ".wf");

                jFileContent.then(content => {
                    const structuralModel = JSON.parse(content) as CoffeeModel;
                    const workflow = {
                        'revision': 0,
                        'type': "graph",
                        'id': "sprotty",
                        'children': []
                    };

                    this.generateActivities(structuralModel, workflow);

                    if (structuralModel.activities) {
                        structuralModel.activities.forEach(a => console.log(a.name))
                    }

                });
            },
            isVisible: (uri: URI) => uri.toString().endsWith("json"),
            isEnabled: (uri: URI) => uri.toString().endsWith("json")
        }),
        );
    }

    private generateActivities(rootElement: CoffeeModel, workflow: {}): void {

    }

    private createTask(name: string, taskId: string, xPosition: number, yPosition: number): string {
        const task = {
            "name": name,
            "expanded": false,
            "duration": Math.random() * 50,
            "taskType": "automated",
            "reference": "ControlUnit",
            "layout": "vbox",
            "position": {
                "x": xPosition,
                "y": yPosition
            },
            "type": "node:task",
            "id": taskId,
            "children": [
                {
                    "layout": "hbox",
                    "type": "comp:header",
                    "id": taskId + "_header",
                    "children": [
                        {
                            "layout": "stack",
                            "position": {
                                "x": 0.0,
                                "y": 0.0
                            },
                            "layoutOptions": {
                                "resizeContainer": false,
                                "hAlign": "center"
                            },
                            "type": "icon",
                            "id": taskId + "_icon",
                            "children": [
                                {
                                    "text": "A",
                                    "type": "label:icon",
                                    "id": taskId + "_ticon"
                                }
                            ]
                        },
                        {
                            "text": name,
                            "type": "label:heading",
                            "id": taskId + "_classname"
                        }
                    ]
                }
            ]
        }
        return JSON.stringify(task);

    }

    protected newUriAwareCommandHandler(handler: UriCommandHandler<URI>): UriAwareCommandHandler<URI> {
        return new UriAwareCommandHandler(this.selectionService, handler)

    };
}
