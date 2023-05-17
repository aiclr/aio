package org.bougainvilleas.ilj.designpattern.behavior;

import org.bougainvilleas.ilj.designpattern.behavior.memento.Caretaker;
import org.bougainvilleas.ilj.designpattern.behavior.memento.Originator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

/**
 * 备忘录模式  memento pattern
 * 不破坏封装性的前提下,捕获一个对象的内部状态,
 * 并在该对象之外保存这个状态,
 * 后续可以恢复到原先的保存的状态
 * <p>
 * 实现信息封装,不需要关心状态的保存细节
 * <p>
 * 数据库事务管理
 * 备忘录加原型模式配合节约内存
 */
class MementoTest {
  private static final Logger log = Logger.getLogger(MementoTest.class.getSimpleName());

  @DisplayName("备忘录模式- memento pattern")
  @Test
  void mementoTest() {
    Originator originator = new Originator();
    Caretaker caretaker = new Caretaker();

    originator.setState("first");
    caretaker.add(originator.saveStateMemento());
    originator.setState("second");
    caretaker.add(originator.saveStateMemento());
    originator.setState("third");
    caretaker.add(originator.saveStateMemento());
    log.info("当前状态"+originator.getState());
    Assertions.assertEquals("third", originator.getState());
    originator.getStateMemento(caretaker.get(1));
    log.info("当前状态"+originator.getState());
    Assertions.assertEquals("second", originator.getState());

  }
}
