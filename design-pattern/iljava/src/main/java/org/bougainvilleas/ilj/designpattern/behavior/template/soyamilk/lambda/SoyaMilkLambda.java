package org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk.lambda;

public class SoyaMilkLambda {
  private Handle select;
  private boolean hook;
  private Handle addCondiments;
  private Handle soak;
  private Handle beat;

  public SoyaMilkLambda(Handle select, boolean hook, Handle addCondiments, Handle soak, Handle beat) {
    this.select = select;
    this.addCondiments = addCondiments;
    this.soak = soak;
    this.beat = beat;
    this.hook = hook;
  }

  public final void make() {
    select.handle();
    if (hook)
      addCondiments.handle();
    soak.handle();
    beat.handle();
  }
}
