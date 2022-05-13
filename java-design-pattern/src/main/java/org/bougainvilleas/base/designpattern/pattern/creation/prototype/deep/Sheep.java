package org.bougainvilleas.base.designpattern.pattern.creation.prototype.deep;

import java.io.*;

/**
 * //反序列化 * @version 1.0
 *
 * @Description: 深拷贝
 * @Author caddy
 * @date 2020-02-11 14:07:57
 */
public class Sheep implements Cloneable, Serializable {
    private static final long SERAILVERSIONUID = 1L;
    String name;
    String color;
    int age;
    Dog dog;

    //深拷贝2 通过对象序列化 推荐使用
    public Object deepClone() {
        Sheep object = null;
        //创建流对象

        ByteArrayInputStream bis=null;
        ObjectInputStream ois=null;

        try (
            //序列化
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //序列化
            ObjectOutputStream oos = new ObjectOutputStream(bos)
        ) {
            //当前这个对象以对象流的方式输出
            oos.writeObject(this);
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            object = (Sheep) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return object;
    }


    //深拷贝 重写clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object object;
        //浅拷贝
        object = super.clone();
        Sheep sheep = (Sheep) object;
        sheep.dog = (Dog) dog.clone();
        return object;
    }

    public Sheep(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", sheep hashcode= " + dog.hashCode() +
                '}';
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
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


}
