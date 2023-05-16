package org.bougainvilleas.ilj.designpattern.creation.builder;

public class House {

  int floors;
  String name;
  int height;

  @Override
  public String toString() {
    return "House{" +
            "floors=" + floors +
            ", name='" + name + '\'' +
            ", height=" + height +
            '}';
  }

  public int getFloors() {
    return floors;
  }

  public void setFloors(int floors) {
    this.floors += floors;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}
