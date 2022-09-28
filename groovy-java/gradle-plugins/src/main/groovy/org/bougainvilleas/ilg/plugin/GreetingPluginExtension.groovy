package org.bougainvilleas.ilg.plugin

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

interface GreetingPluginExtension {
    MapProperty<String,String> getKv()
    ListProperty<String> getSkip()
    Property<String> getMessage()
    Property<String> getGreeter()
}