package org.bougainvilleas.ilg.plugin

import javafx.beans.property.Property

/**
 * default  配置
 */
abstract class GreetingPluginExtensionDef {
    abstract Property<String> getMessage()

    GreetingPluginExtensionDef() {
        message.convention('Hello from GreetingPlugin')
    }
}
