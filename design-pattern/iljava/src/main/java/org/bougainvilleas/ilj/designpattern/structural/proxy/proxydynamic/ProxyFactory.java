package org.bougainvilleas.ilj.designpattern.structural.proxy.proxydynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

public class ProxyFactory {

  private static final Logger log = Logger.getLogger(ProxyFactory.class.getSimpleName());

  private Object target;

  public ProxyFactory(Object target) {
    this.target = target;
  }

  /**
   * 给目标对象生成一个代理对象
   * public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
   * 1. ClassLoader loader：指定当前目标对象使用的类加载器，获取加载器的方法固定
   * 2. Class<?>[] interfaces：目标对象实现的接口类型使用泛型方法确认类型
   * 3. InvocationHandler h:事件处理，执行目标对象的方法,会触发事情处理器方法,会把当前执行的目标对象方法作为参数传入
   */
  public Object getProxyInstane() {
    return Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new InvocationHandler() {
              @Override
              public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                log.info("JDK 动态代理");
                /**
                 * 通过反射机制调用目标对象方法
                 * returnval为方法的返回值，没有返回值的为null
                 */
                return method.invoke(target, objects);
              }
            }
    );
  }
}
