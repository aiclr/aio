package org.bougainvilleas.ilj.designpattern.creation.prototype.shallow;

import org.bougainvilleas.ilj.designpattern.creation.prototype.deep.Dog;
import org.bougainvilleas.ilj.designpattern.creation.prototype.deep.SheepPro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class SheepTest {

  private static final Logger log= Logger.getLogger(SheepTest.class.getSimpleName());

  @Test
  @DisplayName("浅拷贝")
  void shallowCopyTest(){
    Sheep root =new Sheep("root","yellow",1);
    Sheep leaf =new Sheep("leaf","black",2);
    root.setSheep(leaf);
    log.info(root.toString());
    try {
      Sheep rootClone= (Sheep) root.clone();
      log.info(rootClone.toString());
      assertSame(root.getSheep(),rootClone.getSheep(),"浅拷贝不会拷贝引用类型");

      /**
       * 因为 StringTable（heap area）
       * 所以  assertSame “==” 测试通过
       */
      rootClone.getSheep().setColor("write");
      assertSame("write",root.getSheep().getColor(),"修改rootClone的sheep会影响原类root");
      assertEquals("write",root.getSheep().getColor(),"修改rootClone的sheep会影响原类root");

    } catch (CloneNotSupportedException e) {
      log.warning(e.getMessage());
    }
  }

  @Test
  @DisplayName("深拷贝--引用类型实现 Cloneable.clone()方法")
  void deepCopyTest(){
    SheepPro root =new SheepPro("root","yellow",1);
    Dog dog =new Dog("修狗");
    root.setDog(dog);
    log.info(root.toString());
    try {
      SheepPro rootClone= (SheepPro) root.clone();
      log.info(rootClone.toString());
      assertNotSame(root.getDog(),rootClone.getDog(),"深拷贝会拷贝引用类型，");

      rootClone.getDog().setName("旺财");
      assertSame("修狗",root.getDog().getName(),"修改rootClone的dog不会影响原类");
      assertEquals("修狗",root.getDog().getName(),"修改rootClone的dog不会影响原类");
    } catch (CloneNotSupportedException e) {
      log.warning(e.getMessage());
    }
  }

  @Test
  @DisplayName("深拷贝--对象序列化实现深拷贝")
  void deepCopyBySerializeTest(){
    SheepPro root =new SheepPro("root","yellow",1);
    Dog dog =new Dog("修狗");
    root.setDog(dog);
    log.info(root.toString());
    SheepPro rootClone= (SheepPro) root.deepClone();
    log.info(rootClone.toString());
    assertNotSame(root.getDog(),rootClone.getDog(),"深拷贝会拷贝引用类型，");

    rootClone.getDog().setName("旺财");
    assertSame("修狗",root.getDog().getName(),"修改rootClone的dog不会影响原类");
    assertEquals("修狗",root.getDog().getName(),"修改rootClone的dog不会影响原类");
  }


}