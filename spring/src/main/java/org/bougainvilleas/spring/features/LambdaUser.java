package org.bougainvilleas.spring.features;

/**
 * 函数式风格创建对象
 * @author renqiankun
 */
public class LambdaUser
{

    private String name;

    public LambdaUser(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println(name+":函数式风格创建对象");
    }
}
