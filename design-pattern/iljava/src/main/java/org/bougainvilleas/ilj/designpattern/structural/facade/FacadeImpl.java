package org.bougainvilleas.ilj.designpattern.structural.facade;

public class FacadeImpl implements Facade {

  private Model1 model1;
  private Model2 model2;

  public FacadeImpl() {
    this.model1 = Model1.getInstance();
    this.model2 = Model2.getInstance();
  }

  @Override
  public void start() {
    model1.on();
    model2.start();
  }

  @Override
  public void end() {
    model2.stop();
    model1.off();
  }
}
