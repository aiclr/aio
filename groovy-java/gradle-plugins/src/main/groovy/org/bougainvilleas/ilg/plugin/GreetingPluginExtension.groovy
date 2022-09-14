package org.bougainvilleas.ilg.plugin

import org.gradle.api.provider.Property

interface GreetingPluginExtension {
    Property<String> getMessage()
    Property<String> getGreeter()
}