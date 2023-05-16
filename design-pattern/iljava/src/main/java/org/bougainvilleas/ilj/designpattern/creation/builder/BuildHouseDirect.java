package org.bougainvilleas.ilj.designpattern.creation.builder;

/**
 * 指挥者
 * 控制方法调用顺序
 */
public class BuildHouseDirect {
  BuilderHouse builder;

  public BuildHouseDirect(BuilderHouse builder) {
    this.builder = builder;
  }

  public void setBuilder(BuilderHouse builder) {
    this.builder = builder;
  }

  public BuilderHouse getBuilder() {
    return builder;
  }

  public House build(){
    builder.buildBasic();
    builder.buildWalls();
    builder.roofed();
    return builder.build();
  }
}
