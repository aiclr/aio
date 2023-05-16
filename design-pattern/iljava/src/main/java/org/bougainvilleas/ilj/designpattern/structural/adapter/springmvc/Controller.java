package org.bougainvilleas.ilj.designpattern.structural.adapter.springmvc;

import java.util.logging.Logger;

public interface Controller {
  Logger log = Logger.getLogger(Controller.class.getSimpleName());
}

class HttpController implements Controller {
  public void doHttpHandler() {
    log.info("doHttpHandler");
  }
}

class SimpleController implements Controller {
  public void doSimpleHandler() {
    log.info("doSimpleHandler");
  }
}

class AnnotationController implements Controller {
  public void doAnnotationHandler() {
    log.info("doAnnotationHandler");
  }
}
