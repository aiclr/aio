package org.bougainvilleas.ilg.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class GreetingTask extends DefaultTask{

    @Input
    String message = 'Hello'

    @Input
    String greeter = 'Gradle'

    @TaskAction
    def greet(){
        println message +' from '+greeter
    }
}
