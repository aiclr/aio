package org.bougainvilleas.spring.ioc.xml.service;


import org.bougainvilleas.spring.ioc.xml.dao.UserDao;

/**
 * 注入外部bean
 * @author renqiankun
 */
public class UserService
{

    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        System.out.println("service add ....");
        userDao.update();
    }
}
