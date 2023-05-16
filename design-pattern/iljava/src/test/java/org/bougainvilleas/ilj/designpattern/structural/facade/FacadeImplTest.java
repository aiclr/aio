package org.bougainvilleas.ilj.designpattern.structural.facade;

import org.junit.jupiter.api.Test;

class FacadeImplTest {

  @Test
  void facadeTest(){
    Facade facade=new FacadeImpl();
    facade.start();
    facade.end();
  }
}