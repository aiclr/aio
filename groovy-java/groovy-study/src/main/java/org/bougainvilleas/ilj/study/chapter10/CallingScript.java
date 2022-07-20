package org.bougainvilleas.ilj.study.chapter10;

import javax.script.*;

/**
 * 利用JSR 223（ Java Specification Request java规范请求）使用groovy脚本
 *
 * JSR 223 为java和一些实现了JSR 223脚本引擎API 的脚本语言提供了一种标准的交互方式
 * java6开始默认包含 JSR223
 * 要使用 groovy脚本 需要确保 jsr223-engines/groovy/build/groovy-engine.jar 在classpath下
 *
 * 与groovy相比 其实JSR 223更适合JVM上的其他语言，Groovy 对java和Groovy联合编译功能降低了对类似工具的需求
 *
 * 本例将groovy脚本嵌在字符串中传递给 eval() 方法
 * 实际使用时脚本可能在文本中，输入流中，对话框中等，此时需要
 * 使用 eval() 其他使用Reader的重载版本
 *
 * 使用 eval() 方法的Object返回值来接受 脚本返回结果
 *
 */
public class CallingScript
{
    public static void main(String[] args)
    {
        simple();
        testBindings();
        testScriptContext();
        testScriptContext1();
    }

    public static void simple()
    {
        ScriptEngineManager manager=new ScriptEngineManager();
        ScriptEngine engine=manager.getEngineByName("groovy");
        //传参
        String name="Venkat";
        engine.put("name",name);
        System.err.println("Calling script from java");
        try{
            engine.eval("println \"Hello $name from Groovy\"; name+='!!!'");
            //获取新值
            String nameBack=(String)engine.get("name");
            System.err.println("Back in Java:"+nameBack);
            System.err.println("in Java:"+name);
        }catch (javax.script.ScriptException ex)
        {
            System.err.println(ex);
        }
    }



    public static void testBindings()
    {
        String name="VenkatVenkat";
        Bindings bindings=new SimpleBindings();
        bindings.put("name",name);

        Bindings bindings1=new SimpleBindings();
        bindings1.put("name",name);

        ScriptEngineManager manager=new ScriptEngineManager();
        ScriptEngine engine=manager.getEngineByName("groovy");
        try
        {
            engine.eval("println \"Hello $name from Groovy\"; name+='???'",bindings);
            engine.eval("println \"Hello $name from Groovy\"; name+='!!!'",bindings1);
            String nameBack=(String) bindings.get("name");
            String nameBack1=(String) bindings1.get("name");
            System.err.println("Back in Java:"+nameBack);
            System.err.println("Back in Java:"+nameBack1);
            System.err.println("in Java:"+name);
        } catch (ScriptException ex)
        {
            System.err.println(ex);
        }
    }

    public static void testScriptContext()
    {
        ScriptEngineManager manager=new ScriptEngineManager();
        ScriptEngine engine=manager.getEngineByName("groovy");

        String name="VenkatVenkatVenkat";
        Bindings bindings=new SimpleBindings();
        bindings.put("name",name);

        ScriptContext scriptContext=new SimpleScriptContext();
        scriptContext.setBindings(bindings,ScriptContext.ENGINE_SCOPE);

        ScriptContext scriptContext1=new SimpleScriptContext();
        scriptContext1.setBindings(bindings,ScriptContext.ENGINE_SCOPE);
        try
        {
            engine.eval("println \"Hello $name from Groovy\"; name+='???'",scriptContext);
            engine.eval("println \"Hello $name from Groovy\"; name+='!!!'",scriptContext1);
            String nameBack=(String) scriptContext.getBindings(ScriptContext.ENGINE_SCOPE).get("name");
            String nameBack1=(String) scriptContext1.getBindings(ScriptContext.ENGINE_SCOPE).get("name");
            System.err.println("Back in Java:"+nameBack);
            System.err.println("Back in Java:"+nameBack1);
            System.err.println("in Java:"+name);
        } catch (ScriptException ex)
        {
            System.err.println(ex);
        }
    }

    public static void testScriptContext1()
    {
        ScriptEngineManager manager=new ScriptEngineManager();
        ScriptEngine engine=manager.getEngineByName("groovy");

        String name="VenkatVenkatVenkat";
        Bindings bindings=new SimpleBindings();
        bindings.put("name",name);

        ScriptContext scriptContext=new SimpleScriptContext();
        scriptContext.setBindings(bindings,ScriptContext.GLOBAL_SCOPE);

        ScriptContext scriptContext1=new SimpleScriptContext();
        scriptContext1.setBindings(bindings,ScriptContext.ENGINE_SCOPE);
        try
        {
            engine.eval("println \"Hello $name from Groovy\"; name+='???'",scriptContext);
            engine.eval("println \"Hello $name from Groovy\"; name+='!!!'",scriptContext1);
            String nameBack=(String) scriptContext.getBindings(ScriptContext.GLOBAL_SCOPE).get("name");
            String nameBack1=(String) scriptContext1.getBindings(ScriptContext.ENGINE_SCOPE).get("name");
            System.err.println("Back in Java:"+nameBack);
            System.err.println("Back in Java:"+nameBack1);
            System.err.println("in Java:"+name);
        } catch (ScriptException ex)
        {
            System.err.println(ex);
        }
    }


}
