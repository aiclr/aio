package org.bougainvilleas.ilg.task

import org.gradle.api.Project
import org.gradle.internal.impldep.org.junit.Assert
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

class GreetingTaskTest {
    @Test
    void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('greeting', type: GreetingTask)
        Assert.assertTrue(task instanceof GreetingTask)
    }
}
