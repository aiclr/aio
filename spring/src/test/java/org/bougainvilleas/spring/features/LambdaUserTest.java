package org.bougainvilleas.spring.features;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 函数式风格
 * @author renqiankun
 */
class LambdaUserTest
{
    @Test
    @DisplayName("咯咯哒:函数式风格创建对象")
    void show(){

        GenericApplicationContext context=new GenericApplicationContext();
        context.refresh();
        //指定beanName
//        context.registerBean("user",LambdaUser.class,()->new LambdaUser("咯咯哒"));
//        LambdaUser lambdaUser=context.getBean("user",LambdaUser.class);
        //默认 需要把包类全路径加上
        context.registerBean(LambdaUser.class,()->new LambdaUser("咯咯哒"));
        LambdaUser lambdaUser=(LambdaUser)context.getBean("org.bougainvilleas.spring.features.LambdaUser");
        lambdaUser.show();
    }

    @DisplayName("junit5 单元测试 String 参数1")
    @ParameterizedTest
    @NullSource//null
    @EmptySource//""
    @ValueSource(strings = { " ", "   ", "\t", "\n" ,"2333"})
    void paramStringTest1(String name)
    {
        System.err.println(name);
    }

    @DisplayName("junit5 单元测试 String 参数2")
    @ParameterizedTest
    @NullAndEmptySource// null 和 ""
    @ValueSource(strings = { " ", "   ", "\t", "\n" ,"2333"})
    void paramStringTest2(String name)
    {
        System.err.println(name);
    }
}
