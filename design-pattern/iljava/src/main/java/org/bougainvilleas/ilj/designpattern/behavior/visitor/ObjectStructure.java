package org.bougainvilleas.ilj.designpattern.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举{@link Element}的元素,提供一个高层接口{@link ObjectStructure}允许访问者{@link Visitor}访问元素{@link Element}
 */
public class ObjectStructure {
  List<Element> elements=new ArrayList<>();

  public void attach(Element element){
    elements.add(element);
  }

  public void detach(Element element){
    elements.remove(element);
  }

  public void display(Visitor visitor){
    for(Element element:elements){
      element.accept(visitor);
    }
  }
}
