/*!
 * Copyright (C) 2019 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */
import { PreferenceSchemaProvider } from '@theia/core/lib/browser';
import { ILogger } from '@theia/core/lib/common';
import { WorkspaceService } from '@theia/workspace/lib/browser/workspace-service';
import { inject, injectable } from 'inversify';
import { JsonFormsTreeEditorWidget } from 'jsonforms-tree-extension/lib/browser/editor/json-forms-tree-editor-widget';
import { JSONFormsWidget } from 'jsonforms-tree-extension/lib/browser/editor/json-forms-widget';
import { JsonFormsTree } from 'jsonforms-tree-extension/lib/browser/tree/json-forms-tree';
import { AddCommandProperty, JsonFormsTreeWidget } from 'jsonforms-tree-extension/lib/browser/tree/json-forms-tree-widget';

@injectable()
export class PreferencesTreeEditorWidget extends JsonFormsTreeEditorWidget {

  constructor(
    // @inject(JsonFormsTreeEditorWidgetOptions)
    // readonly options: JsonFormsTreeEditorWidgetOptions,
    @inject(JsonFormsTreeWidget)
    readonly treeWidget: JsonFormsTreeWidget,
    @inject(JSONFormsWidget)
    readonly formWidget: JSONFormsWidget,
    @inject(WorkspaceService)
    readonly workspaceService: WorkspaceService,
    @inject(ILogger) readonly logger: ILogger,
    @inject(PreferenceSchemaProvider)
    protected readonly schemaProvider: PreferenceSchemaProvider) {
    super(
      treeWidget,
      formWidget,
      workspaceService,
      logger,
      PreferencesTreeEditorWidget.WIDGET_ID
    );

    this.instanceData = schemaProvider.getCombinedSchema();
    this.treeWidget.setData({ error: false, data: this.instanceData });
  }

  protected handleFormUpdate(data: any, node: JsonFormsTree.Node): void {
    // TODO implement handleFormUpdate
    // throw new Error('Method not implemented.');
  }

  public save(): void {
    // TODO actually save data
    this.logger.info('Save called.');
  }

  protected deleteNode(node: Readonly<JsonFormsTree.Node>): void {
    // TODO implement deleteNode
    this.logger.info('deleteNode was called but is not implemented, yet.');
  }

  // TODO remove model server usage
  protected addNode({ node, type, property }: AddCommandProperty): void {
    // TODO implement addNode
    this.logger.info('addNode was called but is not implemented, yet.');
  }

  dispose() {
    super.dispose();
  }
}
export namespace PreferencesTreeEditorWidget {
  export const WIDGET_ID = 'org.eclipse.emfcloud.theia.preferences.editor';
}
