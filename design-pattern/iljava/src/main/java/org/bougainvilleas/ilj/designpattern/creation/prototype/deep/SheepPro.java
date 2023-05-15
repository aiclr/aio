package org.bougainvilleas.ilj.designpattern.creation.prototype.deep;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

public class SheepPro implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private static final Logger log = Logger.getLogger(SheepPro.class.getSimpleName());

  private String name;
  private String color;
  private int age;
  private Dog dog;

  /**
   * 每个类型都重写 clone()，实现深拷贝
   *
   * @return
   * @throws CloneNotSupportedException
   */
  @Override
  public Object clone() throws CloneNotSupportedException {
    Object obj;
    obj = super.clone();
    SheepPro sheep = (SheepPro) obj;
    sheep.dog = (Dog) dog.clone();
    return obj;
  }


  /**
   * 通过对象序列化实现深拷贝 推荐使用
   */
  public Object deepClone() {
    SheepPro object = null;
    //创建流对象
    ByteArrayInputStream bis = null;
    ObjectInputStream ois = null;
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(bos)
    ) {
      //当前这个对象以对象流的方式输出
      oos.writeObject(this);
      //反序列化
      bis = new ByteArrayInputStream(bos.toByteArray());
      ois = new ObjectInputStream(bis);
      object = (SheepPro) ois.readObject();
    } catch (Exception e) {
      log.severe(e.getMessage());
    } finally {
      if (bis != null) {
        try {
          bis.close();
        } catch (IOException e) {
          log.severe(e.getMessage());
        }
      }
      if (ois != null) {
        try {
          ois.close();
        } catch (IOException e) {
          log.severe(e.getMessage());
        }
      }
    }
    return object;
  }


  @Override
  public String toString() {
    return hashCode() + "--Sheep{" +
            "name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", age=" + age +
            ", dog hashCode=" + dog.hashCode() +
            '}';
  }

  public SheepPro(String name, String color, int age) {
    this.name = name;
    this.color = color;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Dog getDog() {
    return dog;
  }

  public void setDog(Dog dog) {
    this.dog = dog;
  }
}
