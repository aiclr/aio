package org.bougainvilleas.ilj.study.chapter10;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.util.Optional;

public class CallingGroovy {
  public static void main(String[] args) {
    simple();
    param("jack", 8);
    param("leo", 6);
    param("nex", 19);
    System.out.println("11+19="+add(11, 19));
    System.out.println("15+15="+add(15, 15));
  }

  public static void simple() {

    String filepath = Optional.ofNullable(CallingGroovy.class.getClassLoader().getResource("groovyScript.groovy"))
            .map(URL::getPath)
            .orElseThrow(() -> new RuntimeException("无法获取groovyScript.groovy"));
    File file = new File(filepath);
    try (Reader fileReader = new FileReader(file);
         Reader fileReader2 = new FileReader(file)) {
      ScriptEngineManager manager = new ScriptEngineManager();
      ScriptEngine engine = manager.getEngineByName("groovy");
      //在脚本内部声明的方法 不能在java内 使用engine.eval("groovy 脚本 调用方法")  直接调用
      engine.eval(fileReader);
      System.out.println("---");

      Invocable inv = (Invocable) engine;
      inv.invokeFunction("move", "forward");
      System.out.println("---");

      // 据说可以优化脚本执行效率
      CompiledScript compile = ((Compilable) engine).compile(fileReader2);
      compile.eval();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 往groovy 传递参数
   */
  public static void param(String name, int age) {

    String filepath = Optional.ofNullable(CallingGroovy.class.getClassLoader().getResource("groovyScriptParam.groovy"))
            .map(URL::getPath)
            .orElseThrow(() -> new RuntimeException("无法获取groovyScript.groovy"));
    File file = new File(filepath);
    try (Reader fileReader = new FileReader(file)) {
      ScriptEngineManager manager = new ScriptEngineManager();
      ScriptEngine engine = manager.getEngineByName("groovy");
      engine.put("who", name);
      engine.put("age", age);
      engine.eval(fileReader);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static int add(int a,int b){
    String filepath = Optional.ofNullable(CallingGroovy.class.getClassLoader().getResource("groovyScriptReturn.groovy"))
            .map(URL::getPath)
            .orElseThrow(() -> new RuntimeException("无法获取groovyScript.groovy"));
    File file = new File(filepath);
    try (Reader fileReader = new FileReader(file)) {
      ScriptEngineManager manager = new ScriptEngineManager();
      ScriptEngine engine = manager.getEngineByName("groovy");
      engine.put("a", a);
      engine.put("b", b);
      return (int)engine.eval(fileReader);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
