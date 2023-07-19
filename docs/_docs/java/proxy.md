<div style="text-align: center;font-size: 40px;">代理</div>

## 代理模式

> Proxy是一种设计模式，提供对目标对象另外的访问方式;即通过代理对象访问目标对象 \
> 可以在目标对象实现的基础上，增强额外的功能操作，即扩展目标对象的功能 \
> 编程思想：不要随意修改别人已经写好的代码或者方法，如果需要修改，可以通过代理的方式来扩展该方法 \
> 代理模式的关键点：代理对象与目标对象，代理对象是对目标对象的扩展，并会调用目标对象

## 静态代理

```java
// 不修改目标对象，对目标的功能进行扩展
// 代理对象需要与目标对象实现一样的接口，所以会有很多代理类，类太多。一旦接口增加，目标对象和代理对象都要维护

/** 公共接口 */
public interface IUserServ
{
    void save();
}

/** 目标业务类 */
public class UserServiceImpl implements IUserServ
{

    @Override
    public void save()
    {
        System.err.println("userServiceImpl");
    }
}

/** 代理类 */
public class UserServiceProxy implements IUserServ
{

    private IUserServ target;

    public UserServiceProxy(IUserServ iUserServ)
    {
        this.target = iUserServ;
    }

    @Override
    public void save()
    {
        System.err.println("start...");
        target.save();
        System.err.println("end...");

    }
}

/** 测试 */
public class DemoApplicationTests
{
    @Test
    public void testStaticProxy()
    {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);
        userServiceProxy.save();
    }
}
```

## 动态代理

> 不需要手动编写一个代理对象，不需要编写与目标对象相同的方法，这个过程在运行时的内存中动态生成代理对象 \
> 字节码对象级别的代理对象 \
> jdk的API中存在一个Proxy，其中存在一个生成动态代理的方法newProxyInstance
>
> static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
> > 返回值Object就是代理对象
>
> > 参数
> > > loader:代表与目标对象相同的类加载器--目标对象.getClass().getClassLoader() \
> > > interfaces:代表与目标对象实现的所有的接口字节码对象数组 \
> > > h：具体的代理的操作，InvocationHandler接口

## JDK 动态代理

```java
//JDK的Proxy方式实现的动态代理，目标对象必须有接口，没有接口不能实现jdk动态代理
public interface IUserServ
{
    void save();
}

//目标类
public class UserServiceImpl implements IUserServ
{

    @Override
    public void save()
    {
        System.err.println("userServiceImpl");
    }
}

//测试
public class DemoApplicationTests
{

    UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void testDynamicProxy()
    {
        IUserServ proxy = (IUserServ) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler()
                {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                    {
                        Object invoke = method.invoke(userService, args);
                        return null;
                    }
                }
        );
        proxy.save();
    }
}
```

## cglib

```java
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 被代理类
 */
public class Teacher
{

    public String teach()
    {
        System.err.println("Teacher is running");
        return "嘿嘿";
    }

    /**
     * 无法使用对象调用static方法
     */
    // public static String teach2()
    // {
    //     System.err.println(" static Teacher is running");
    //     return "static";
    // }

    /**
     * final 无法被代理
     * @return
     */
    // public final String teach3()
    // {
    //     System.err.println(" final Teacher is running");
    //     return "final";
    // }
}

/**
 * cglib 代理
 */
class ProxyFactory implements MethodInterceptor
{

    //维护一个目标对象
    Object target;

    //构造器传入目标对象
    public ProxyFactory(Object target)
    {
        this.target = target;
    }

    //返回一个代理对象 target对象的代理对象
    public Object getProxyInstance()
    {
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
     *
     * @param o 被代理类
     * @param method 被代理的方法，
     * @param objects 被代理的方法的参数
     * @param methodProxy
     * @return 返回的是代理模式修饰后的被代理方法返回值
     * @throws Throwable
     * 重写intercept方法，会调用目标对象的方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable
    {
        System.err.println("cglib代理模式--前");
        Object invoke = method.invoke(target, objects);
        System.err.println("cglib代理模式--后");
        return invoke;
    }
}

/**
 * proxy 代理模式
 */
public class Client
{


    public static void main(String[] args)
    {
        Teacher teacher = new Teacher();
        ProxyFactory proxyFactory = new ProxyFactory(teacher);
        Teacher proxyInstance = (Teacher) proxyFactory.getProxyInstance();

        // System.err.println(proxyInstance);
        System.err.println(proxyInstance.getClass());
        System.err.println(proxyInstance.teach());
        // System.err.println(proxyInstance.teach2());
        // System.err.println(proxyInstance.teach3());
    }
}
```