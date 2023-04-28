package org.bougainvilleas.base.designpattern.pattern.behavior.template;


/**
 * 模板方法模式
 *
 */
public class Client {

    public static void main(String[] args) {
        Soyamilk redBeanSoyamilk = new RedBeanSoyamilk();
        redBeanSoyamilk.make();

        Soyamilk peanutSoyamilk = new PeanutSoyamilk();
        peanutSoyamilk.make();

        Soyamilk pureSoyamilk = new PureSoyamilk();
        pureSoyamilk.make();
        System.err.println("Lambda...");
        Condiments condiment=new Condiments();
        SoyamilkLambda soyamilkLambda = new SoyamilkLambda(
                condiment::select,false,null,condiment::soak,condiment::beat
        );
        soyamilkLambda.make();
        SoyamilkLambda soyamilkLambda2 = new SoyamilkLambda(
                condiment::select,true,condiment::redBean,condiment::soak,condiment::beat
        );
        soyamilkLambda2.make();

        System.err.println("Lambda pro...");
        SoyamilkLambdaPro soyamilkLambdaPro = new SoyamilkLambdaPro(
                condiment::select,condiment::redBean,condiment::peanut,condiment::soak,condiment::beat
        );
        soyamilkLambdaPro.make();
    }
}
