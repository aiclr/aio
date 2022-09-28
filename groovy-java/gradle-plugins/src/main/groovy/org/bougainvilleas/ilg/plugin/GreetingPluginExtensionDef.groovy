package org.bougainvilleas.ilg.plugin

import org.gradle.api.provider.Property


/**
 * default  配置
 */
abstract class GreetingPluginExtensionDef {
    abstract Property<String> getMessage()

    GreetingPluginExtensionDef() {
        message.convention('Hello from GreetingPlugin')
    }
}
