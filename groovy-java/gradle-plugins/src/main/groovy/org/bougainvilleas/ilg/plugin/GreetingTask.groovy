package org.bougainvilleas.ilg.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class GreetingTask extends DefaultTask{

    @Input
    List<String> skip

    @Input
    Map<String,String> kv

    @Input
    String message = 'Hello'

    @Input
    String greeter = 'Gradle'

    @TaskAction
    def greet(){
        println(kv)
        println(skip)
        println message +' from '+greeter
    }
}
