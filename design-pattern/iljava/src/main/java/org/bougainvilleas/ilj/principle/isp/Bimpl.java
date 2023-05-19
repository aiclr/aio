package org.bougainvilleas.ilj.principle.isp;

import java.util.logging.Logger;

public class Bimpl implements ISP1, ISP45 {
  private static final Logger log = Logger.getLogger(Bimpl.class.getSimpleName());

  @Override
  public void operate1() {
    log.info("B#operate1");
  }

  @Override
  public void operate4() {
    log.info("B#operate4");
  }

  @Override
  public void operate5() {
    log.info("B#operate5");
  }
}
