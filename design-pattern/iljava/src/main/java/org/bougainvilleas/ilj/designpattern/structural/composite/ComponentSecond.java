package org.bougainvilleas.ilj.designpattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理{@link ComponentLast}
 */
public class ComponentSecond extends Component {

  /**
   * components 内部存放的是{@link ComponentLast}
   */
  List<Component> components = new ArrayList<>();

  public ComponentSecond(String name, String desc) {
    super(name, desc);
  }

  @Override
  public void add(Component component) {
    components.add(component);
  }

  @Override
  public void remove(Component component) {
    components.remove(component);
  }

  @Override
  public void print() {
    log.info("----" + getName() + "-----" + getDesc());
    for (Component component : components) {
      component.print();
    }
  }
}
