/********************************************************************************
 * Copyright (C) 2018 Ericsson and others.
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
 ********************************************************************************/
import { bindViewContribution, PreferenceProvider, PreferenceScope, WidgetFactory } from '@theia/core/lib/browser';
import { ContainerModule, interfaces } from 'inversify';
import { createJsonFormsTreeWidget } from 'jsonforms-tree-extension/lib//browser/util';
import { JsonFormsTreeEditorWidgetOptions } from 'jsonforms-tree-extension/lib/browser/editor/json-forms-tree-editor-widget';
import { JsonFormsTreeWidget } from 'jsonforms-tree-extension/lib/browser/tree/json-forms-tree-widget';

import { UserPreferenceProvider } from './duplicate/user-preference-provider';
import { PreferenceEditorContainer } from './preference-editor-container';
import { PreferenceEditorContribution } from './preference-editor-contribution';
import { PreferencesTreeNodeFactory } from './preferences-tree-editor-node-factory';
import { PreferencesTreeEditorWidget } from './preferences-tree-editor-widget';
import { PreferencesTreeLabelProvider } from './preferences-tree-label-provider';

export function bindPreferences(bind: interfaces.Bind, unbind: interfaces.Unbind): void {
    // bindPreferenceProviders(bind, unbind);

    bindViewContribution(bind, PreferenceEditorContribution);

    bind(PreferenceEditorContainer).toSelf();
    bind(WidgetFactory).toDynamicValue(({ container }) => ({
        id: PreferenceEditorContainer.ID,
        createWidget: () => container.get(PreferenceEditorContainer)
    }));

    bind(PreferenceProvider).to(UserPreferenceProvider).inSingletonScope().whenTargetNamed(PreferenceScope.User);

    bind(PreferencesTreeNodeFactory).toSelf();
    bind(PreferencesTreeLabelProvider).toSelf();
    bind(PreferencesTreeEditorWidget).toSelf();

    bind(WidgetFactory).toDynamicValue(({ container }) => ({
        id: PreferencesTreeEditorWidget.WIDGET_ID,
        // createWidget: () => container.get(PreferencesTreeEditorWidget)
        createWidget: () => {
            const child = container.createChild();

            // Create and bind tree widget only for this editor creation
            const tree = createJsonFormsTreeWidget(container, PreferencesTreeLabelProvider, PreferencesTreeNodeFactory);
            child.bind(JsonFormsTreeWidget).toConstantValue(tree);

            child
                .bind<JsonFormsTreeEditorWidgetOptions>(
                    JsonFormsTreeEditorWidgetOptions
                )
                .toConstantValue({
                    uri: undefined
                });

            return child.get(PreferencesTreeEditorWidget);
        }
    }));
    // TODO bind in non conflicting way (see coffee editor)

    // bind(WidgetFactory).toDynamicValue(({ container }) => ({
    //     id: PreferencesTreeWidget.ID,
    //     createWidget: () => createPreferencesTreeWidget(container)
    // })).inSingletonScope();

    // bind(PreferencesEditorsContainer).toSelf();
    // bind(WidgetFactory).toDynamicValue(({ container }) => ({
    //     id: PreferencesEditorsContainer.ID,
    //     createWidget: () => container.get(PreferencesEditorsContainer)
    // }));

    // bind(PreferencesMenuFactory).toSelf();
    // bind(FrontendApplicationContribution).to(PreferencesFrontendApplicationContribution).inSingletonScope();
}

export default new ContainerModule((bind, unbind, isBound, rebind) => {
    bindPreferences(bind, unbind);
});
