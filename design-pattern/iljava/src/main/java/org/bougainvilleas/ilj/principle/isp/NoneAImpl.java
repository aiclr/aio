package org.bougainvilleas.ilj.principle.isp;

public class NoneAImpl implements NoneISP {
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

  @Override
  public void operate4() {
    log.info("A#operate4");
  }

  @Override
  public void operate5() {
    log.info("A#operate5");
  }
}
