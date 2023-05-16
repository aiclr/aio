package org.bougainvilleas.ilj.designpattern.structural;

import org.bougainvilleas.ilj.designpattern.structural.adapter.AdapterClass;
import org.bougainvilleas.ilj.designpattern.structural.adapter.AdapterObject;
import org.bougainvilleas.ilj.designpattern.structural.adapter.Volatage220v;
import org.bougainvilleas.ilj.designpattern.structural.adapter.Volatage5V;
import org.bougainvilleas.ilj.designpattern.structural.adapter.springmvc.DispatchServlet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class AdapterTest {

  @DisplayName("类适配器")
  @Test
  void classAdapter(){
    AdapterClass adapter = new AdapterClass();
    Assertions.assertEquals(5,adapter.outPut5V());
  }

  @DisplayName("对象适配器")
  @Test
  void objectAdapter(){
    AdapterObject adapter = new AdapterObject(new Volatage220v());
    Assertions.assertEquals(5,adapter.outPut5V());
  }

  @DisplayName("接口适配器")
  @Test
  void interfaceAdapter(){

    /**
     * 内部类实现适配器
     * 接口拥有多个方法
     * 只关注使用到的方法即可,使用时实现具体方法
     */
    Volatage5V adapter=new Volatage5V(){
      @Override
      public int outPut5V() {
        return new Volatage220v().output220v()/44;
      }
    };
    Assertions.assertEquals(5,adapter.outPut5V());
    /**
     * Lambda
     * 接口只有一个方法
     */
    Volatage5V adapterLambda= () -> new Volatage220v().output220v()/44;
    Assertions.assertEquals(5,adapterLambda.outPut5V());

    int result= new Volatage220v().output220v()/44;
    Assertions.assertEquals(5,result);
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
  @DisplayName("模拟测试 SpringMvc HandlerAdapter")
  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"HttpController","SimpleController","AnnotationController"})
  void springMVCTest(String type){
    new DispatchServlet().doDispatch(type);
  }
}
