package org.bougainvilleas.spring.aop.proxy;

import org.bougainvilleas.spring.aop.proxy.jdk.MyInvocationHandler;
import org.bougainvilleas.spring.aop.proxy.jdk.dao.UserDao;
import org.bougainvilleas.spring.aop.proxy.jdk.dao.impl.UserDaoImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 *
 * @author renqiankun
 */
class JDKProxyTest
{
    @Test
    @DisplayName("jdk动态代理")
    void test()
    {
        //待增强类实现的接口
        Class<?>[] interfaces={UserDao.class};
        //使用Proxy类下的newProxyInstance方法创建UserDao接口实现类的代理对象，增强 UserDao接口实现类 被代理方法
        UserDao userdao =(UserDao) Proxy.newProxyInstance(JDKProxyTest.class.getClassLoader(), interfaces, new MyInvocationHandler(new UserDaoImpl()));
        int add = userdao.add(1, 2);
        System.out.println(add);
        String update = userdao.update("给我加上翅膀");
        System.out.println(update);
    }
}
