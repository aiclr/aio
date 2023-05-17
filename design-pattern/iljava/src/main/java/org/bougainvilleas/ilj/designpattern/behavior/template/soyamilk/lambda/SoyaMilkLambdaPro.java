package org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk.lambda;

import java.util.Arrays;

public class SoyaMilkLambdaPro {
  private Handle[] handles;

  /**
   * 保证{@link Handle}调用顺序
   */
  public SoyaMilkLambdaPro(Handle ... handles) {
    this.handles=handles;
  }


  public void make() {
    Arrays.stream(handles).forEach(Handle::handle);
  }
}
