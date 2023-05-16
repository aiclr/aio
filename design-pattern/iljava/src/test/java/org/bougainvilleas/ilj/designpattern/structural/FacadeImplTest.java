package org.bougainvilleas.ilj.designpattern.structural;

import org.bougainvilleas.ilj.designpattern.structural.facade.Facade;
import org.bougainvilleas.ilj.designpattern.structural.facade.FacadeImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("外观模式")
class FacadeImplTest {

  @Test
  void facadeTest() {
    Facade facade = new FacadeImpl();
    facade.start();
    facade.end();
  }
}