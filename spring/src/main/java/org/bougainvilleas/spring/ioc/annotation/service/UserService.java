package org.bougainvilleas.spring.ioc.annotation.service;

import org.bougainvilleas.spring.ioc.annotation.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 注入外部bean
 * @author renqiankun
 */
@Service
public class UserService {

//    @Autowired//根据类型注入，不能有有两个相同类型的UserDao bean实例，否则需要与Qualifier一起指定具体注入哪个bean
//    @Qualifier(value = "userDao2Impl")//需要与Autowired一起使用
//    UserDao userDao;

//    @Autowired
//    @Qualifier(value = "userDaoImpl")//需要与Autowired一起使用
//    UserDao userDao;

//    @Resource//此写法为根据类型注入 等价于@Autowired，不能有有两个相同类型的 UserDao bean实例
//    UserDao userDao;

    @Resource(name = "userDao2Impl")//此写法为根据名称注入 等价于@Autowired+@Qualifier(value = "userDao2Impl")
    UserDao userDao;



    public void add(){
        System.out.println(" annotation service add ....");
        userDao.update();
    }
}
