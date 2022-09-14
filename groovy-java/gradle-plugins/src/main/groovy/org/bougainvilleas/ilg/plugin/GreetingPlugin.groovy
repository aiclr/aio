package org.bougainvilleas.ilg.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class GreetingPlugin implements Plugin<Project> {

    //默认值
//    void apply(Project project) {
//
//        def extension = project.extensions.create('greeting', GreetingPluginExtensionDef)
//
//        project.getTasks().register('hello', GreetingTask.class, task -> {
//            message = "${extension.message.get()}"
//            greeter = "Default"
//        })
//    }

    void apply(Project project) {

        def extension = project.extensions.create('greeting', GreetingPluginExtension)

        project.getTasks().register('hello', GreetingTask.class, task -> {
            message = "${extension.message.get()}"
            greeter = "${extension.greeter.get()}"
        })
    }
}