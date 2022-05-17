package org.bougainvilleas.spring.aop.proxy.jdk.dao.impl;

import org.bougainvilleas.spring.aop.proxy.jdk.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * jdk动态代理
 * @author renqiankun
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public String update(String id) {

        System.out.println("jdk动态代理");
        return id;
    }

    @Override
    public int add(int a, int b) {
        System.out.println("jdk动态代理");
        return a+b;
    }
}
