package org.bougainvilleas.spring.aop.xml;

/**
 * 基于xml aop
 * @author renqiankun
 */
public class UserDao {

    public void add() {
        System.out.println("AOP xml");
    }

    public String update(String id) {

//        int a=10/0;
        System.out.println("AOP xml");
        return id;
    }
}
