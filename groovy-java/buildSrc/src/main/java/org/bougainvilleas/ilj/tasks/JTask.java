package org.bougainvilleas.ilj.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.MapProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public abstract class JTask extends DefaultTask {

  @Input
  public abstract ListProperty<String> getSkip();

  @Input
  public abstract MapProperty<String,String> getMap();

  @Input
  public abstract Property<String> getMessage();

  @Input
  public abstract Property<String> getGreeter();

  @TaskAction
  void javaGreet(){
    System.out.println(this.getMap().get());
    System.out.println(this.getSkip().get());
    System.out.println(getMessage().get() +" from "+getGreeter().get());
  }
}
