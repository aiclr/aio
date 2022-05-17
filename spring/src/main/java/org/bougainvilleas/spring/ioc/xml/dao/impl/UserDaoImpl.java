package org.bougainvilleas.spring.ioc.xml.dao.impl;


import org.bougainvilleas.spring.ioc.xml.dao.UserDao;

/**
 * 外部bean注入
 * @author renqiankun
 */
public class UserDaoImpl implements UserDao
{
    @Override
    public void update() {

        System.out.println("UserDaoImpl is update");
    }
}
