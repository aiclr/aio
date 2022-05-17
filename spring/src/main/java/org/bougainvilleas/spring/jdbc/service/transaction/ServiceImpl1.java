package org.bougainvilleas.spring.jdbc.service.transaction;

import org.bougainvilleas.spring.jdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务传播行为测试
 * @author renqiankun
 */
@Service
public class ServiceImpl1 {

    @Autowired
    UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional(propagation = Propagation.SUPPORTS)
//    @Transactional(propagation = Propagation.MANDATORY)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    @Transactional(propagation = Propagation.NEVER)
//    @Transactional(propagation = Propagation.NESTED)
    public void age(int reduce,int add, int num){
        userDao.reduceAge(reduce,num);
//        int a=10/0;
        userDao.addAge(add,num);
    }
}
