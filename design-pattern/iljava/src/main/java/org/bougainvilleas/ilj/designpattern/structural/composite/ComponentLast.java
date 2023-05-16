package org.bougainvilleas.ilj.designpattern.structural.composite;

/**
 * 叶子节点
 */
public class ComponentLast extends Component {
  public ComponentLast(String name, String desc) {
    super(name, desc);
  }

  @Override
  public void print() {
    log.info("----" + getName() + "-----" + getDesc());
  }
}
