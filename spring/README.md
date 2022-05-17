[toc]
# spring framework 

spring framework 5+ study

# 需要的jar包

- commons-logging-1.1.1.jar
- hamcrest-core-1.3.jar
- junit-4.13.jar
- spring-beans-5.2.6.RELEASE.jar
- spring-context-5.2.6.RELEASE.jar
- spring-core-5.2.6.RELEASE.jar
- spring-expression-5.2.6.RELEASE.jar
- druid-1.1.20.jar
- spring-aop-5.2.6.RELEASE.jar
- spring-aspects-5.2.6.RELEASE.jar
- aopalliance-1.0.jar
- aspectjweaver-1.6.8.jar
- cglib-2.2.2.jar
- spring-jdbc-5.2.6.RELEASE.jar
- spring-orm-5.2.6.RELEASE.jar
- spring-tx-5.2.6.RELEASE.jar
- mysql-connector-java-8.0.21.jar
- log4j-api-2.12.1.jar
- log4j-core-2.12.1.jar
- log4j-slf4j-impl-2.12.1.jar
- slf4j-api-1.7.30.jar
- spring-test-5.2.6.RELEASE.jar

## IOC

```groovy
implementation("commons-logging:commons-logging:${loggingVersion}")
implementation("org.hamcrest:hamcrest-core:${hamcrestVersion}")
implementation("com.alibaba:druid:${druidVersion}")

implementation("org.springframework:spring-beans:${springVersion}")
implementation("org.springframework:spring-context:${springVersion}")
implementation("org.springframework:spring-core:${springVersion}")
implementation("org.springframework:spring-expression:${springVersion}")
```

1. 控制反转-把对象创建和对象之间的调用过程，交给spring管理
2. 目的是降低耦合度
3. 引入jar
    - commons-logging-1.1.1.jar
    - hamcrest-core-1.3.jar
    - junit-4.13.jar
    - spring-beans-5.2.6.RELEASE.jar
    - spring-context-5.2.6.RELEASE.jar
    - spring-core-5.2.6.RELEASE.jar
    - spring-expression-5.2.6.RELEASE.jar
    - druid-1.1.20.jar

### 底层原理
    
1. xml解析、工厂模式、反射

### BeanFactory接口

1. IOC思想基于IOC容器完成，IOC容器底层就是对象工厂
2. Spring提供IOC容器实现两种方式
    1. BeanFactory: IOC容器基本实现，spring内部使用接口，不提供给开发人员使用
        - 加载配置文件时不会创建对象，获取bean时才创建对象
    2. ApplicationContext: BeanFactory的子接口，提供更多更强大的功能，一般使用此接口
        - 加载配置文件时就创建对象
        - 主要实现类
            - FileSystemXmlApplicationContext 绝对路径
            - ClassPathXmlApplicationContext  项目路径
    3. 实际使用时，将创建对象这种耗时耗资源的工作，在项目启动时处理更合理

### 管理Bean
    
1. 创建对象
2. 注入属性

#### 基于annotation 引入spring-aop-5.2.6.RELEASE.jar

- 注解
    1. 注解是代码特殊标记。格式：@注解名(属性名=属性值,属性名=属性值)
    2. 可以作用在类，方法，属性上面  @Service、@Test、@Value、@Override
    3. 注解简化xml配置===>SpringBoot
- Spring提供的创建对象的注解，注意：四个注解功能一样，都能创建对象，只是为了web应用分层更清晰，约定大于规范
    1. @Component 普通
    2. @Service 业务逻辑层
    3. @Controller web层
    4. @Repository dao层
- 使用
    1. 引入context名称空间开启组件扫描，设置扫描范围```<context:component-scan base-package="org.bougainvilleas.spring.ioc.annotation"/>``
    2. 使用注解

##### 注解注入属性

- POJO属性注入
    -  @Autowired 跟据属性类型自动装配
    -  @Qualifier 根据属性名注入
    -  @Resource  可以根据属性名称注入，可以根据属性类型注入
        - 不是Spring包中注解 import javax.annotation.Resource;
        - 建议使用Autowired，Qualifier
- 注入普通类型
    - @Value

#### 基于XML
    
1. 创建对象
    - 默认找无参构造器创建对象
    ```xml
    <bean id="user" class="org.bougainvilleas.spring.ioc.xml.User"></bean>
   ```
    - id属性：唯一标识 不能重复 不能有特殊符合
    - class属性：包类全路径
    - name属性：早期为status1框架提供的属性，能传特殊字符，使用较少
2. 注入属性
    - DI：依赖注入~注入属性，是IOC的一种具体实现
    - setter注入
        - 字面量
            - 注入特殊字符 <![CDATA[<<西游记>>]]>
            - 注入空值 <null/>
        - 注入bean ref注入，内部bean注入，需要有get方法的内部bean注入写法
    - 有参构造器注入

##### POJOBean（普通Bean） & FactoryBean（工厂bean）

- 普通bean：在配置文件中定义bean类型=返回的类型
- 工厂bean：在配置文件中定义bean类型≠返回的类型
    - 创建类作为工厂Bean 实现FactoryBean
    - 实现接口方法，在实现的方法中定义返回的bean类型

##### bean作用域 scope
    
- 单例（Spring默认值）singleton
```xml
<bean id="book" class="org.bougainvilleas.spring.ioc.xml.Book" scope="singleton"/>
```
- 多例 prototype
```xml
<bean id="book" class="org.bougainvilleas.spring.ioc.xml.Book" scope="prototype"/>
```
- 区别
    - singleton单例,prototype多例
    - singleton加载配置文件时就会创建一个单例对象
    - prototype在获取bean ```context.getBean("book", Book.class)``` 时才创建对象
- 不常用值
    - request
    - session

##### bean生命周期
    
1. 通过构造器创建bean实例（有参构造器，无参构造器，工厂bean）
2. DI 注入属性（set方法，ref引用）
   3.1 把bean实例传递给bean后置处理器的方法
   3.2 调用bean初始化方法（需要配置）
   3.3 把bean实例传递给bean后置处理器的方法
4. bean可以使用（获取对象）
5. 当容器关闭时，调用bean的销毁方法（需要进行配置销毁的方法）
- 后置处理器
    1. 创建类 实现BeanPostProcessor接口
    2. 默认会将当前配置文件内的所有bean都配置上后置处理器

##### 自动装配
    
1. 根据指定装配规则（属性名称或者属性类型），Spring自动将匹配的属性值注入，一种简化写法

## AOP

```groovy
//    AOP
implementation("org.springframework:spring-aspects:${springVersion}")

implementation("cglib:cglib:${cglibVersion}")
implementation("aopalliance:aopalliance:${aopallianceVersion}")
implementation("org.aspectj:aspectjweaver:${aspectjweaverVersion}")
```

1. 面向切面编程，aop是oop的延续，利用aop可以对业务逻辑的各个部分进行隔离，从而使业务逻辑各部分之间耦合度降低，提高程序的可重复性，提高开发效率
2. 场景：日志，性能统计，安全控制，事务处理，异常处理等从业务代码中分出来，可以在不修改源代码的情况下，增加主业务功能
3. 引入
    - spring-aspects-5.2.6.RELEASE.jar
    - aopalliance-1.0.jar
    - aspectjweaver-1.6.8.jar
    - cglib-2.2.2.jar

### 底层原理

#### 动态代理

##### 有接口jdk动态代理

- java.lang.reflect.Proxy
    - 方法：newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h);返回指定接口的代理类的实例，该接口将方法调用分派给指定的调用处理程序
    - 参数
        - ClassLoader loader: 类加载器
        - Class<?>[] interfaces: 需要增强的方法所在的类所实现的接口，支持多个接口
        - InvocationHandler h: 实现这个接口InvocationHandler，创建代理对象，写增强方法

##### 无接口cgLib代理

### 术语

- 连接点：类中可以被增强的方法
- 切入点：类中具体实现了增强功能的方法
- 通知 增强
    - @Before 前置通知
    - @AfterReturning  后置通知，有异常不会执行 日志
    - @After 最终通知 类似finally 无论是否异常都会执行增强 常用于日志
    - @Around 环绕通知 控制事务 权限控制
    - @AfterThrowing 异常通知 异常处理 控制事务
- 切面：动作 把增强应用到切入点的过程

### AspectJ框架

- AspectJ独立的AOP框架，不是Spring的组成部分
- 把AspectJ框架和Spring框架一起使用进行AOP操作

### 切入点表达式：execution

- execution([权限修饰符][返回类型][类全路径][方法名称]([参数列表]))
- execution(* org.bougainvilleas.spring.aop.Bean.add(..))对Bean下的add方法增强
- execution(* org.bougainvilleas.spring.aop.Bean.*(..))对Bean下的所有方法增强

## JdbcTemplate

```groovy
//  jdbc
implementation("org.springframework:spring-jdbc:${springVersion}")
implementation("org.springframework:spring-tx:${springVersion}")
implementation("org.springframework:spring-orm:${springVersion}")
implementation("mysql:mysql-connector-java:${mysqlVersion}")
```

1. Spring对JDBC进行封装，使用JdbcTemplate对数据库操作
3. 引入jar
    - mysql-connector-java-8.0.21.jar
    - spring-jdbc-5.2.6.RELEASE.jar
    - spring-tx-5.2.6.RELEASE.jar
    - spring-orm-5.2.6.RELEASE.jar

## Transaction

- ACID
    - 原子性：不可分割，一组操作要么都成功，一个失败全部回滚
    - 一致性：操作前操作后总量不变，类似会计学有借必有贷，借贷必相等
    - 隔离性：多事务操作时互不影响
    - 持久性：数据变更后要提交，进行持久化
- 数据库操作的基本单元，逻辑上一组操作要么都成功，一个失败全部回滚
- 编程式事务管理
- 声明式事务管理（推荐），底层是AOP
    - 事务管理器接口PlatformTransactionManager
        - 抽象子类AbstractPlatformTransactionManager
            - 子类DataSourceTransactionManager（Mybatis，JdbcTemplate使用此实现类）
            - 子类HibernateTransactionManager（Hibernate使用此实现类）
- @Transactional
    - 加到类上：所有方法都添加事务
    - 加到方法上：该方法添加事务
    - 参数（查看Transactional源码）
        - 类与类之间的事务传播行为：Propagation propagation() default Propagation.REQUIRED;
        - 事务隔离级别：Isolation isolation() default Isolation.DEFAULT;
        - 超时时间(秒)：int timeout() default -1;事务需要在一定时间内进行提交,如果不提交就进行回滚
        - 是否只读：boolean readOnly() default false;是否只查询,默认false,设true时,不能修改操作
        - 回滚：Class<? extends Throwable>[] rollbackFor() default {};设置哪些异常进行事务回滚
        - 不回滚：Class<? extends Throwable>[] noRollbackFor() default {};设置哪些异常不进行事务回滚
- Spring7种事务传播行为，查看Propagation枚举类
    - 类与类之间 的多事务方法之间进行调用，这个过程中事务是如何进行管理的
    - 事务方法：对数据表数据进行变化的操作
    - 有添加事务的方法调用未添加事务的方法
    - 有添加事务方法调用添加事务的方法
    - 默认值:REQUIRED(0)如果有事务在进行，当前方法就在这个事务内进行，否则当前方法开启新事务，在新开的事务内进行
    - SUPPORTS(1)如果有事务在进行，当前方法就在这个事务内进行，否则可以不在事务内运行
    - MANDATORY(2)当前方法必须运行在事务内，如果当前没有事务，抛出异常，不会自动开启事务
    - REQUIRES_NEW(3)当前方法必会启动新的事务，并在它自己的事务内运行，如果当前有事务正在运行，则将它挂起
    - NOT_SUPPORTED(4)当前方法不应该运行在事务中，如果有运行的事务，将它挂起
    - NEVER(5)当前方法不应该运行在事务中，如果有运行的事务，抛出异常
    - NESTED(6)如果有事务在运行，当前的方法就应该在这个事务的嵌套事务内运行，否则就启动一个新的事务，并在它自己的事务内运行
- 事务隔离级别,查看Isolation枚举类
    - DEFAULT(-1),默认（数据库默认隔离级别，Mysql默认是REPEATABLE_READ）
    - READ_UNCOMMITTED(1),读未提交(不可重复读，脏读，幻读):可读取到未提交事务
    - READ_COMMITTED(2),读已提交(不可重复读,幻读):可读取到已提交事务
    - REPEATABLE_READ(4),可重复读(幻读):只会读取,开始读取时的数据,期间别人新提交的事务,不会去读取
    - SERIALIZABLE(8);串行化(写锁),当前事务未提交,其他事务挂起等待当前事务
    - 问题
        - 脏读：读取到已修改，未提交的数据，当回滚时，则读取数据就是无效数据
        - 不可重复读：多次读取结果不一致
        - 幻读：读取到未提交的insert事务，insert事务回滚后发现读取到的结果并不存在

### xml配置声明式事务管理
        
1. 配置事务管理器
2. 配置增强
3. 配置切入点和切面

## 新特性

### 基于Java8，运行时兼容JDK9

### Spring5整合Log4j2 [OrderTest](src/test/java/org/bougainvilleas/spring/ioc/xml/lifecycle/OrderTest.java)

```groovy
//log4j2
implementation("org.apache.logging.log4j:log4j-api:${log4jVersion}")
implementation("org.apache.logging.log4j:log4j-core:${log4jVersion}")
implementation("org.apache.logging.log4j:log4j-slf4j-impl:${log4jVersion}")
implementation("org.slf4j:slf4j-api:${slf4jVersion}")
```

- 移除Log4jConfigListener,官方推荐使用
- 整合Log4j2：
    1. 引入jar
        - log4j-api-2.12.1.jar
        - log4j-core-2.12.1.jar
        - log4j-slf4j-impl-2.12.1.jar
        - slf4j-api-1.7.30.jar
    2. 创建log4j2.xml配置文件（src/log4j2.xml）

### Spring5 核心容器支持@Nullable注解

- 使用在方法上，表示方法返回值可以为空
- 使用在属性值上面，表示属性值可以为空
- 使用在参数上面，表示参数可以为空
  ```java
  
    import org.springframework.lang.Nullable;
    class User {
        @Nullable
        String name;
  
        @Nullable
        public void show(@Nullable int mark){
            System.out.print("111");
        }
    }
  ```
  
### Spring5支持函数式风格GenericApplicationContext

### Spring5支持整合JUnit5

#### 整合JUnit4

```groovy
dependencies {
 //  junit4
    testImplementation("junit:junit:${junit4Version}")
    testRuntimeOnly("junit:junit:${junit4Version}")
}
```

- 引入jar
    - hamcrest-core-1.3.jar
    - junit-4.13.jar

#### 整合JUnit5 

> 测试类和方法 不需要声明为 public

```groovy
dependencies {
//  junit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:${junit5Version}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junit5Version}")
}
test {
    //MARk 使用junit4 时 注释掉 useJUnitPlatform()
    useJUnitPlatform()
}
```

- 引入jar使用idea自动引入JUnit5
    1. 删除import org.junit.Test;
    2. 在@Test注解上import JUnit5
    3. jar
        - apiguardian-api-1.0.0.jar
        - junit-jupiter-5.4.2.jar
        - junit-jupiter-api-5.4.2.jar
        - junit-jupiter-engine-5.4.2.jar
        - junit-jupiter-params-5.4.2.jar
        - junit-platform-commons-1.4.2.jar
        - junit-platform-engine-1.4.2.jar
        - opentest4j-1.1.1.jar