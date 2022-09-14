package org.bougainvilleas.ilg.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class GreetingTask extends DefaultTask{
    @Input
    String greeting = 'hello from GreetingTask'

    @TaskAction
    def greet(){
        println greeting
    }
}
