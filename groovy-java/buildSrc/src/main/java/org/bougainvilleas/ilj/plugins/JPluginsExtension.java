package org.bougainvilleas.ilj.plugins;

import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.MapProperty;
import org.gradle.api.provider.Property;

public interface JPluginsExtension {
  MapProperty<String,String> getKv();
  ListProperty<String> getSkip();
  Property<String> getMessage();
  Property<String> getGreeter();
}
