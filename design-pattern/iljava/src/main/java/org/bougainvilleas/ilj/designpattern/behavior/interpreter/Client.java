package org.bougainvilleas.ilj.designpattern.behavior.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Logger;

public class Client {


  private static final Logger log = Logger.getLogger(Client.class.getSimpleName());

  public static void main(String[] args) throws IOException {
    String expStr = getExpstr();

    HashMap<String, Integer> var = getValue(expStr);
    Calculator calculator = new Calculator(expStr);
    log.info(expStr + "=" + calculator.run(var));
  }

  private static String getExpstr() throws IOException {
    log.info("输入表达式: 如a+b+c");
    return (new BufferedReader(new InputStreamReader(System.in))).readLine();

  }

  private static HashMap<String, Integer> getValue(String expStr) throws IOException {

    HashMap<String, Integer> map = new HashMap<>();
    for (char ch : expStr.toCharArray()) {
      if (ch != '+' && ch != '-') {
        if (!map.containsKey(String.valueOf(ch))) {
          log.info("请输入" + ch + "的值:");
          String in = (new BufferedReader(new InputStreamReader(System.in))).readLine();
          map.put(String.valueOf(ch), Integer.valueOf(in));
        }
      }
    }
    return map;

  }
}
