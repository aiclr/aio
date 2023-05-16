package org.bougainvilleas.ilj.designpattern.creation.builder;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertSame;

class BuilderTest {
  private static final Logger log = Logger.getLogger(BuilderTest.class.getSimpleName());

  @Test
  void build() {
    CommonBuilder commonBuilder = new CommonBuilder();
    HighBuilder highBuilder = new HighBuilder();

    BuildHouseDirect direct = new BuildHouseDirect(commonBuilder);
    House house = direct.build();
    log.info(house.toString());
    assertSame(1, house.floors);

    direct.setBuilder(highBuilder);
    House house2 = direct.build();
    log.info(house2.toString());
    assertSame(1, house2.floors);


    highBuilder.buildBasic();
    highBuilder.buildWalls();

    highBuilder.buildBasic();
    highBuilder.buildWalls();

    highBuilder.buildBasic();
    highBuilder.buildWalls();
    highBuilder.roofed();
    log.info(house2.toString());
    assertSame(4, house2.floors);
  }
}
