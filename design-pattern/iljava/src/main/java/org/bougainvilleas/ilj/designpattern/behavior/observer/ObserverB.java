package org.bougainvilleas.ilj.designpattern.behavior.observer;

/**
 * 观察者-B
 */
public class ObserverB implements Observer {

  private String data;

  @Override
  public void update(String data) {
    this.data = data;
    display();
  }

  public void display() {
    log.info("B=====" +data);
  }
}
