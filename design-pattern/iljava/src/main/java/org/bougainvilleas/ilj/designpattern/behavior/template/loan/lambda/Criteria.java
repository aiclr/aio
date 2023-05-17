package org.bougainvilleas.ilj.designpattern.behavior.template.loan.lambda;

import org.bougainvilleas.ilj.designpattern.behavior.template.loan.ApplicationDenied;

public interface Criteria {
  void check() throws ApplicationDenied;
}
