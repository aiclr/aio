package org.bougainvilleas.ilj.designpattern.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 守护者,保存多个备忘录对象。
 *
 * 多种备忘可以用HashMap集合保存
 */
public class Caretaker {
  private List<Memento> mementoList = new ArrayList<>();

  public void add(Memento memento) {
    mementoList.add(memento);
  }

  /**
   * 获取指定备忘录
   *
   * @param index
   * @return
   */
  public Memento get(int index) {
    return mementoList.get(index);
  }
}
