package org.bougainvilleas.base.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {

  public static void main(String[] args) {
    System.err.println(getName());
  }
  /**
   * 输入交互方法
   */
  public static String getName(){
    BufferedReader string=new BufferedReader(new InputStreamReader(System.in));
    System.err.println("wait input... cheese or greek");
    String name= null;
    try {
      name = string.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return name;
  }

}
