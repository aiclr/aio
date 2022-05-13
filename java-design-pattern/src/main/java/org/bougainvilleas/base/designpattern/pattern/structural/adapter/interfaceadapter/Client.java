package org.bougainvilleas.base.designpattern.pattern.structural.adapter.interfaceadapter;

/**
 * @Description: 接口适配器模式只关注使用到的方法即可,用的时候实现方法
 * @Author caddy
 * @date 2020-02-11 16:51:36
 * @version 1.0
 */
public class Client {
    public static void main(String[] args) {

        new InterImpl(){
            @Override
            public void m1() {
                System.err.println("只用m1方法");
            }
        }.m1();

        Integer integer = ((ILambdaTest) () -> 1 + 3).m1();

        ((ILambdaTest2) () -> System.err.println(123)).m1();
    }

    /**
     * SpringMvc HandlerAdapter
     * DispatcherServlet
     * doDispatch(...)方法下
     * 通过HandlerMapping来映射Controller
     * mappedHandler=getHandler(processedRequest);
     * 获取适配器
     * HandlerAdapter ha=getHandlerAdapter(mappedHand;ler.getHandler())
     * 通过适配器调用controller的方法并返回ModelAndView
     * mv=ha.handle(...);
     */
}
interface ILambdaTest{
    Integer m1();
}
interface ILambdaTest2{
    void m1();
}
