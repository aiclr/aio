package org.bougainvilleas.ilj.designpattern.creation.builder;

public class HighBuilder extends BuilderHouse{

  @Override
  public void buildBasic() {
    super.house.setFloors(1);
  }

  @Override
  public void buildWalls() {
    super.house.setHeight(5);

  }

  @Override
  public void roofed() {
    super.house.setName(super.house.getFloors()+"层高楼");
  }
}
