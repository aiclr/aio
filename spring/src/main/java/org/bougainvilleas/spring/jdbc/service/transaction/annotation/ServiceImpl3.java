package org.bougainvilleas.spring.jdbc.service.transaction.annotation;

import org.bougainvilleas.spring.jdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * 事务传播行为
 * 当使用DataSourceTransactionManager时使用的效果
 *
 *****注意*********************不同类 的事务方法 *******************
 *
 * 不添加传播行为：
 *     A不添加 @Transactional 事务注解，A无事务
 *     A调用B：要么只回滚B，要么都不回滚
 *     B使用
 *          不添加 @Transactional AB都无事务，有异常 都不会回滚
 *          REQUIRED A无事务，B有事务，B内有异常，B才回滚，A内有异常B不会回滚
 *          SUPPORTS AB都无事务，有异常 都不会回滚
 *          MANDATORY A无事务，B需要A传递一个事务过来，但是A无事务，未进入B方法执行业务，抛异常No existing transaction found for transaction marked with propagation 'mandatory'
 *          REQUIRES_NEW A无事务，B有事务，B内有异常，B才回滚，A内有异常B不会回滚
 *          NOT_SUPPORTED AB都无事务，有异常 都不会回滚
 *          NEVER AB都无事务，有异常 都不会回滚
 *          NESTED A无事务，B有事务，B内有异常，B才回滚，A内有异常B不会回滚
 *
 *
 * REQUIRED：
 *     A使用 REQUIRED A有事务，会传递给B
 *     A调用B 全部回滚，挂起卡死，
 *     B使用
 *          不添加@Transaction，AB都有事务,且为同一事务，有异常，都会回滚
 *          REQUIRED AB都有事务,且为同一事务，有异常，都会回滚
 *          SUPPORTS AB都有事务,且为同一事务，有异常，都会回滚
 *          MANDATORY AB都有事务,且为同一事务，有异常，都会回滚
 *          REQUIRES_NEW TODO A，B内无论是否有异常，线程都会挂起卡死，
 *          NOT_SUPPORTED TODO A，B内无论是否有异常，线程都会挂起卡死，但是B方法会执行一部分，强制停止测试用例，不会回滚，推测是junit多线程调试的问题
 *          NEVER：A有事务，会回滚，B不能有事务，但是A传递给B一个事务，所以未进入B放方法就抛异常 Existing transaction found for transaction marked with propagation 'never'
 *          NESTED：AB都有事务,B的事务,嵌套在A事务内部，只要有异常，都会回滚
 * SUPPORTS：
 *     A使用 SUPPORTS，A无事务
 *     A调用B
 *     B使用
 *          不添加@Transaction，AB都无事务
 *          REQUIRED A无事务，B有事务，B内有异常，B才回滚，A内有异常B不会回滚
 *          SUPPORTS AB都无事务
 *          MANDATORY A无事务，B可以当成回滚，B需要事务，但是没有可用事务，又不能自动创建，不会去执行B业务逻辑，抛出异常 No existing transaction found for transaction marked with propagation 'mandatory'
 *          REQUIRES_NEW A无事务，B有事务，B内有异常，B才回滚，A内有异常B不会回滚
 *          NOT_SUPPORTED AB都无事务
 *          NEVER：AB都无事务
 *          NESTED：A无事务，B有事务，B内有异常，B才回滚，A内有异常B不会回滚
 * MANDATORY：强制性的
 *     A使用 MANDATORY，A需要事务，但是不会自己开启事务所以进入A方法前 抛异常 No existing transaction found for transaction marked with propagation 'mandatory'
 *     A调用B，无论什么情况业务方法都不会执行，可以加断点验证
 * REQUIRES_NEW：效果与REQUIRED类似
 *     A使用 REQUIRES_NEW，自动开启新的事务
 *     A调用B
 *     B使用
 *          不添加@Transaction，AB都有事务,且为同一事务，有异常，都会回滚
 *          REQUIRED AB都有事务,且为同一事务，有异常，都会回滚
 *          SUPPORTS AB都有事务,且为同一事务，有异常，都会回滚
 *          MANDATORY AB都有事务,且为同一事务，有异常，都会回滚
 *          REQUIRES_NEW TODO A，B内无论是否有异常，线程都会挂起卡死，
 *          NOT_SUPPORTED TODO A，B内无论是否有异常，线程都会挂起卡死，但是B方法会执行一部分，强制停止测试用例，不会回滚，推测是junit多线程调试的问题
 *          NEVER：A有事务，会回滚，B不能有事务，但是A传递给B一个事务，所以未进入B放方法就抛异常 Existing transaction found for transaction marked with propagation 'never'
 *          NESTED：AB都有事务,B的事务,嵌套在A事务内部，只要有异常，都会回滚
 * NOT_SUPPORTED： 效果与SUPPORT一样
 *     A使用 NOT_SUPPORTED，A无事务且不支持事务,
 *     A调用B
 *     B使用
 *          不添加@Transaction，AB都无事务
 *          REQUIRED A无事务，B有事务，B内有异常，B才回滚，A内有异常B不会回滚
 *          SUPPORTS AB都无事务
 *          MANDATORY A无事务，B可以当成回滚，B需要事务，但是没有可用事务，又不能自动创建，不会去执行B业务逻辑，抛出异常 No existing transaction found for transaction marked with propagation 'mandatory'
 *          REQUIRES_NEW A无事务，B有事务，B内有异常，B才回滚，A内有异常B不会回滚
 *          NOT_SUPPORTED AB都无事务
 *          NEVER：AB都无事务
 *          NESTED：A无事务，B有事务，B内有异常，B才回滚，A内有异常B不会回滚
 * NEVER:
 *     A使用 NEVER，A无事务
 *     A调用B
 *     B使用
 *          不添加@Transaction，AB都无事务
 *          REQUIRED A无事务，B有事务
 *          SUPPORTS AB都无事务
 *          MANDATORY A无事务，B可以当成回滚，B需要事务，但是没有可用事务，又不能自动创建，不会去执行B业务逻辑，抛出异常 No existing transaction found for transaction marked with propagation 'mandatory'
 *          REQUIRES_NEW A无事务，B有事务
 *          NOT_SUPPORTED AB都无事务
 *          NEVER：AB都无事务
 *          NESTED：A无事务，B有事务
 * NESTED: 嵌套事务，效果与REQUIRED类似
 *     A使用 NESTED，A无事务
 *     A调用B
 *     B使用
 *          不添加@Transaction，AB都无事务
 *          REQUIRED A无事务，B有事务
 *          SUPPORTS AB都无事务
 *          MANDATORY A无事务，B可以当成回滚，B需要事务，但是没有可用事务，又不能自动创建，不会去执行B业务逻辑，抛出异常 No existing transaction found for transaction marked with propagation 'mandatory'
 *          REQUIRES_NEW A无事务，B有事务
 *          NOT_SUPPORTED AB都无事务
 *          NEVER：AB都无事务
 *          NESTED：AB都有事务,B的事务,嵌套在A事务内部，只要有异常，都会回滚
 */
@Service//(value = "serviceImpl")
public class ServiceImpl3 {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ,readOnly = false,timeout = -1,rollbackFor ={NullPointerException.class, IOException.class})
    public void age(int reduce, int add, int num){
        userDao.reduceAge(reduce,num);
//        int a=10/0;
        userDao.addAge(add,num);
    }

}
