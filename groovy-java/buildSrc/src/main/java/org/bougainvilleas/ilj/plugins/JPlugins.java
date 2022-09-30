package org.bougainvilleas.ilj.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class JPlugins implements Plugin<Project> {

  @Override
  public void apply(Project project) {
    JPluginsExtension extension=project.getExtensions().create("buildSrcGreeting",JPluginsExtension.class);
    project.getTasks().register("buildSrcPluginsTasks",JPluginsTask.class,task->{
      task.kv=extension.getKv().get();
      task.skip=extension.getSkip().get();
      task.message=extension.getMessage().get();
      task.greeter=extension.getGreeter().get();
    });
  }
}
