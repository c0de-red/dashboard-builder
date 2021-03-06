/**
 * Copyright (C) 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.dashboard.ui.config.components.panelInstance;

import org.jboss.dashboard.ui.UIServices;
import org.jboss.dashboard.ui.components.HandlerFactoryElement;
import org.jboss.dashboard.workspace.WorkspaceImpl;
import org.jboss.dashboard.workspace.PanelInstance;
import org.jboss.dashboard.workspace.WorkspacesManager;
import org.jboss.dashboard.workspace.PanelInstance;
import org.jboss.dashboard.workspace.WorkspaceImpl;

public class PanelInstanceHandler  extends HandlerFactoryElement {

    private String workspaceId;
    private Long panelInstanceId;

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public WorkspacesManager getWorkspacesManager() {
        return UIServices.lookup().getWorkspacesManager();
    }

    public Long getPanelInstanceId() {
        return panelInstanceId;
    }

    public void setPanelInstanceId(Long panelInstanceId) {
        this.panelInstanceId = panelInstanceId;
    }

    public PanelInstance getInstance() throws Exception {
        WorkspaceImpl workspace = (WorkspaceImpl) getWorkspacesManager().getWorkspace(getWorkspaceId());
        return workspace.getPanelInstance(getPanelInstanceId());
    }
}
