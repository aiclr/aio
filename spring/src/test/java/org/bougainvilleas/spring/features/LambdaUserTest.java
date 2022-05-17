package org.bougainvilleas.spring.features;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}
