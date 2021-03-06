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
package org.jboss.dashboard.ui.formatters;

import org.jboss.dashboard.ui.taglib.formatter.Formatter;
import org.jboss.dashboard.ui.taglib.formatter.FormatterException;
import org.jboss.dashboard.ui.NavigationManager;
import org.jboss.dashboard.workspace.Section;
import org.jboss.dashboard.users.UserStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegularLayoutFormatter extends Formatter {

    private NavigationManager navigationManager;

    public NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public void setNavigationManager(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }

    public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FormatterException {
        renderFragment("outputStart");
        boolean userIsAdminInCurrentWorkspace = getNavigationManager().userIsAdminInCurrentWorkspace();
        if (userIsAdminInCurrentWorkspace) {
            renderFragment("administrationBar");
        }
        Section page = getNavigationManager().getCurrentSection();
        if (page == null) {
            renderFragment("noPage");
        } else {
            renderFragment("page");
        }
        renderFragment("outputEnd");
        if (userIsAdminInCurrentWorkspace) {
            renderFragment("unassignedPanels");
        }
    }
}
