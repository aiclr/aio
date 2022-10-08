package org.bougainvilleas.ilj.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

import java.util.List;
import java.util.logging.Logger;

public class POJOTask extends DefaultTask {

  public static final Logger log=Logger.getLogger(POJOTask.class.getName());

  private List<String> skipFiles;
  private String password;

  @Option(option = "skipFiles", description = "Configures the skipFiles.")
  public void setSkipFiles(List<String> skipFiles) {
    this.skipFiles = skipFiles;
  }

  @Option(option = "password", description = "Configures the password.")
  public void setPassword(String password) {
    this.password = password;
  }

  @Input
  public List<String> getSkipFiles() {
    return skipFiles;
  }
  @Input
  public String getPassword() {
    return password;
  }


  @TaskAction
  public void encrypt(){
    log.warning(skipFiles.toString());
    log.warning(password);
  }

}
