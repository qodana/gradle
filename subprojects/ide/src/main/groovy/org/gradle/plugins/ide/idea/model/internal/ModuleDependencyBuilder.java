/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.plugins.ide.idea.model.internal;

import org.gradle.api.Project;
import org.gradle.plugins.ide.idea.IdeaPlugin;
import org.gradle.plugins.ide.idea.model.IdeaModel;
import org.gradle.plugins.ide.idea.model.ModuleDependency;
import org.gradle.plugins.ide.internal.resolver.model.IdeProjectDependency;

class ModuleDependencyBuilder {
    public ModuleDependency create(IdeProjectDependency dependency, String scope) {
        return new ModuleDependency(determineProjectName(dependency), scope);
    }

    private String determineProjectName(IdeProjectDependency dependency) {
        Project project = dependency.getProject();
        if (project == null) {
            return "/" + dependency.getProjectPath();
        } else if (project.getPlugins().hasPlugin(IdeaPlugin.class)) {
            return ((IdeaModel) project.getExtensions().getByName("idea")).getModule().getName();
        } else {
            return project.getName();
        }
    }
}
