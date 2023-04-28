package org.bougainvilleas.base.dsaa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式转后缀表达式
 * 算法：
 * 从左到右扫描中缀表达式
 * 当遇到操作数时压入num栈
 * 当遇到左括号时 （ 直接压入oper栈
 * 当遇到运算符时
 * 当oper栈为空或者栈顶元素为左括号（ 直接压入oper栈
 * 当当前运算符优先级高于oper栈顶元素 直接压入oper栈
 * 否则  递归 运算符优先级不高于oper栈顶元素弹出，并压入到num栈（这样处理，oper栈不会出现两个平级运算符相邻，只会是低级高级）
 * 当遇到右括号时
 * 依次弹出oper栈顶元素，并压入num栈，知道遇到左括号，并将左括号弹出，这样即可将这一对括号丢弃
 * 最后将oper剩余元素弹出并压入num栈，将num出栈逆序就是后缀表达式
 */
public class CalNotation {

    public static void main(String[] args) {
        //中缀表达式
//        String source = "10+((20+3)*4)-50";
        String source = "(2+3)*4+3+(2+3*4)+5";
        //10 20 3 + 4 * + 50 -
        List<String> list = getSource(source);

        Stack<String> oper = new Stack<>();

        Stack<String> num = new Stack<>();
        List<String> opers = Arrays.asList("+", "-", "*", "/");

//        for (String str : list) {
//            if (opers.contains(str)) {
//                //递归
//                digui(oper ,num,str);
//            } else if (str.equals("(")) {
//                oper.push(str);
//            } else if (str.equals(")")) {
//                while (true) {
//                    if (oper.peek().equals("(")) {
//                        oper.pop();//消除小括号
//                        break;
//                    }
//                    num.push(oper.pop());
//                }
//            } else {
//                num.push(str);
//            }
//
//        }

        for (String str : list) {
            //正则表达式 匹配数字
            if(str.matches("\\d+")){
                num.push(str);
            }else if (str.equals("(")) {
                oper.push(str);
            } else if (str.equals(")")) {
                while (!oper.peek().equals("(")) {
                    num.push(oper.pop());
                }
                oper.pop();//弹出左括号
            }else {
                //2+3*4+3+(2+3*4)+5
                while (!oper.empty()&&operClass(str) <= operClass(oper.peek())){
                    num.push(oper.pop());
                }
                oper.push(str);
            }
        }

        while (!oper.isEmpty()) {
            num.push(oper.pop());
        }

        Stack<String> temp=new Stack<>();
        while (!num.empty()){
            temp.push(num.pop());
        }

        //后缀计算 36
        System.err.println(cal(temp));

        System.err.println("10 20 3 + 4 * + 50 -");
    }

    /**
     * 后缀运算
     * @param stack
     * @return
     */
    public static  int cal(Stack<String> stack){
        List<String> opers = Arrays.asList("+", "-", "*", "/");
        Stack<Integer> num = new Stack<>();
        Integer num1;
        Integer num2;
        while (!stack.empty()){
           switch (stack.peek()) {
               case "+":
                   num1=Integer.valueOf(num.pop());
                   num2=Integer.valueOf(num.pop());
                   num.push(num2+num1);
                   break;
               case "-":
                   num1=Integer.valueOf(num.pop());
                   num2=Integer.valueOf(num.pop());
                   num.push(num2-num1);
                   break;
               case "*":
                   num1=Integer.valueOf(num.pop());
                   num2=Integer.valueOf(num.pop());
                   num.push(num2*num1);
                   break;
               case "/":
                   num1=Integer.valueOf(num.pop());
                   num2=Integer.valueOf(num.pop());
                   num.push(num2/num1);
                   break;
               default:
                   num.push(Integer.valueOf(stack.peek()));
                   break;

           }
            stack.pop();
        }
        return num.pop();

    }


    public static void digui(Stack<String> oper ,Stack<String> num,String str){
        //循环一下
        if (oper.empty() || oper.peek().equals("(")) {
            oper.push(str);
        } else if (operClass(str) > operClass(oper.peek())) {
            oper.push(str);
        } else {
            num.push(oper.pop());
            digui(oper ,num,str);
        }

    }

    /**
     * 将字符串存放到列表
     */
    public static List<String> getSource(String source) {
        List<String> sourceList = new ArrayList<>();
        int index = 0;
        String keepNum = "";
        while (true) {
            char ch = source.substring(index, index + 1).charAt(0);
            if (!isOper(ch)) {
                keepNum += ch;
                if (index == source.length() - 1) {
                    sourceList.add(keepNum);
                } else {
                    char ch2 = source.substring(index + 1, index + 2).charAt(0);
                    if (isOper(ch2)) {
                        sourceList.add(keepNum);
                        keepNum = "";
                    }
                }
            } else {
                sourceList.add(""+ch);
            }

            if (index == source.length() - 1) {
                break;
            }
            index++;
        }
        return sourceList;
    }

    /**
     * 是否是运算符
     */
    public static boolean isOper(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
    }

    /**
     * 运算符优先级
     */
    public static boolean isUp(String oper, String peek) {
        if (operClass(oper) > operClass(peek)) {
            return true;
        } else return false;

    }

    public static int operClass(String oper) {
        List<String> one = Arrays.asList("+", "-");
        List<String> two = Arrays.asList("*", "/");
        if (one.contains(oper)) return 1;
        if (two.contains(oper)) return 2;
        if(oper.equals("(")) return 0;
        throw new RuntimeException("不支持的运算");
    }


}
