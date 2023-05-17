package org.bougainvilleas.ilj.designpattern.behavior;

import org.bougainvilleas.ilj.designpattern.behavior.visitor.ElementMan;
import org.bougainvilleas.ilj.designpattern.behavior.visitor.ElementWoman;
import org.bougainvilleas.ilj.designpattern.behavior.visitor.ObjectStructure;
import org.bougainvilleas.ilj.designpattern.behavior.visitor.VisitorFail;
import org.bougainvilleas.ilj.designpattern.behavior.visitor.VisitorSuccess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("访问者模式")
class VisitorTest {
  @Test
  void visitorTest(){
    ObjectStructure structure = new ObjectStructure();
    structure.attach(new ElementMan());
    structure.attach(new ElementWoman());
    VisitorSuccess success = new VisitorSuccess();
    structure.display(success);
    VisitorFail fail = new VisitorFail();
    structure.display(fail);
  }
}
