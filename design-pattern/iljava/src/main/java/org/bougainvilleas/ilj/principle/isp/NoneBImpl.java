package org.bougainvilleas.ilj.principle.isp;

public class NoneBImpl implements NoneISP {
  @Override
  public void operate1() {
    log.info("B#operate1");
  }

  @Override
  public void operate2() {
    log.info("B#operate2");
  }

  @Override
  public void operate3() {
    log.info("B#operate3");
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
