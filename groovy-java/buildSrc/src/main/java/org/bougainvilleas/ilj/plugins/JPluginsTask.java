package org.bougainvilleas.ilj.plugins;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.List;
import java.util.Map;

public class JPluginsTask  extends DefaultTask {

  public List<String> skip;

  public Map<String,String> kv;

  public String message = "Hello";

  public String greeter = "Gradle";

  @TaskAction
  void javaGreet(){
    System.out.println(kv);
    System.out.println(skip);
    System.out.println(message +" from "+greeter);
  }
}
