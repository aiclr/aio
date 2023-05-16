package org.bougainvilleas.ilj.designpattern.structural;

import org.bougainvilleas.ilj.designpattern.structural.composite.ComponentFirst;
import org.bougainvilleas.ilj.designpattern.structural.composite.ComponentLast;
import org.bougainvilleas.ilj.designpattern.structural.composite.ComponentSecond;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("组合模式")
class CompositeTest {

  @Test
  void compositeTest() {
    ComponentFirst first = new ComponentFirst("first", "顶级组织");
    ComponentSecond second1 = new ComponentSecond("second1", "二级1");
    ComponentSecond second2 = new ComponentSecond("second2", "二级2");
    ComponentLast last1 = new ComponentLast("last1", "叶子1");
    ComponentLast last2 = new ComponentLast("last2", "叶子2");

    first.add(second1);
    first.add(second2);

    second1.add(last1);
    second1.add(last2);

    last1.print();
    last2.print();

    second2.print();

    second1.print();

    first.print();
  }
}