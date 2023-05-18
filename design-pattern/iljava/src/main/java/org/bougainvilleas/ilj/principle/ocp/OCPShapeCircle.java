package org.bougainvilleas.ilj.principle.ocp;

public class OCPShapeCircle extends OCPShape {

  public OCPShapeCircle() {
    super(1);
  }

  @Override
  public void draw() {
    log.info("圆形");
  }
}
