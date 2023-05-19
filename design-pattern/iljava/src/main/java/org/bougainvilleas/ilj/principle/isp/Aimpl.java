package org.bougainvilleas.ilj.principle.isp;

import java.util.logging.Logger;

public class Aimpl implements ISP1,ISP23{
  private static final Logger log = Logger.getLogger(Aimpl.class.getSimpleName());
  @Override
  public void operate1() {
    log.info("A#operate1");
  }

  @Override
  public void operate2() {
    log.info("A#operate2");
  }

  @Override
  public void operate3() {
    log.info("A#operate3");
  }
}
