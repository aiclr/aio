package org.bougainvilleas.ilj.designpattern.structural.proxy.proxycglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * cglib 动态代理
 * 使用代理对象继承被代理对象实现
 */
public class ProxtCglibFactory implements MethodInterceptor {
  private static final Logger log = Logger.getLogger(ProxtCglibFactory.class.getSimpleName());
  //维护一个目标对象
  Object target;

  //构造器传入目标对象
  public ProxtCglibFactory(Object target) {
    this.target = target;
  }

  /**
   * @return 返回一个代理对象。 target对象的代理对象
   */
  public Object getProxyInstance() {
    //1. 创建一个工具类
    Enhancer enhancer = new Enhancer();
    //2. 设置父类
    enhancer.setSuperclass(target.getClass());
    //3. 设置回调函数
    enhancer.setCallback(this);
    //4. 创建子类对象，即代理对象
    return enhancer.create();
  }

  /**
   * 重写intercept方法，会调用目标对象的方法
   *
   * @param obj           被代理类
   * @param method      被代理的方法，
   * @param args     被代理的方法的参数
   * @param proxy
   * @return 返回的是代理模式修饰后的被代理方法返回值
   * @throws Throwable
   */
  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
    log.info("cglib proxy before");
    Object invoke = method.invoke(target, args);
    log.info("cglib proxy after");
    return invoke;
  }
}
