package org.bougainvilleas.ilj.designpattern.creation;

import org.bougainvilleas.ilj.designpattern.creation.builder.BuildHouseDirect;
import org.bougainvilleas.ilj.designpattern.creation.builder.CommonBuilder;
import org.bougainvilleas.ilj.designpattern.creation.builder.HighBuilder;
import org.bougainvilleas.ilj.designpattern.creation.builder.House;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("建造者模式")
class BuilderTest {
  private static final Logger log = Logger.getLogger(BuilderTest.class.getSimpleName());

  @Test
  void build() {
    CommonBuilder commonBuilder = new CommonBuilder();
    HighBuilder highBuilder = new HighBuilder();

    BuildHouseDirect direct = new BuildHouseDirect(commonBuilder);
    House house = direct.build();
    log.info(house.toString());
    assertSame(1, house.getFloors());

    direct.setBuilder(highBuilder);
    House house2 = direct.build();
    log.info(house2.toString());
    assertSame(1, house2.getFloors());


    highBuilder.buildBasic();
    highBuilder.buildWalls();

    highBuilder.buildBasic();
    highBuilder.buildWalls();

    highBuilder.buildBasic();
    highBuilder.buildWalls();
    highBuilder.roofed();
    log.info(house2.toString());
    assertSame(4, house2.getFloors());
  }
}
