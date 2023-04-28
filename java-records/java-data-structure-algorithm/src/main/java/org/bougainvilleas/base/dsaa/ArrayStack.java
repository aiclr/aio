package org.bougainvilleas.base.dsaa;

/**
 * 数组模拟栈
 * 关于栈的运算
 * 先将算术运算转换为对应表达式
 *
 * 前缀表达式（波兰表达式）：运算符在操作数之前
 *
 *  （3+4）X5-6对应的前缀表达式是-X+3456（顺序不能变）
 *  运算过程 从右到左扫描前缀表达式 遇到数字压入堆栈
 *  遇到运算符弹出栈顶两个数，将运算结果再压入堆栈
 *
 *  从右到左扫描前缀表达式
 *  将6543压入栈，遇到+号，弹出3，4运算3+4=7,7再入栈
 *  遇到X弹出7,5运算7X5=35,35入栈
 *  遇到-弹出35,6 运算35-6=29
 *
 * 中缀表达式 =正常运算表达式 
 * （3+4）X5-6对应的中缀表达式是（3+4）X5-6
 *  计算机一般将中缀表达式转换为后缀表达式运算
 *
 * 后缀表达式（逆波兰表达式）
 *
 * （3+4）X5-6对应的后缀表达式是34+5X6-
 *  a+b         ab+         ab   ab+
 *  a+(b-c)     abc-+       bc-  a(bc-)+
 *  a+(b-c)*d   abc-d*+     bc-  (bc-)d*  a((bc-)d*)+
 *  a+d*(b-c)   adbc-*+     bc-  d(bc-)*  a(d(bc-)*)+
 *  a=1+3       a13+=       13+  a(13+)=
 *  运算过程 从左到右扫描后缀表达式 遇到数字压入堆栈
 *  遇到运算符弹出栈顶两个数，将运算结果再压入堆栈
 *
 */
public class ArrayStack {
    public static void main(String[] args) {
        ArrayStackDemo stackDemo = new ArrayStackDemo(10);
        stackDemo.show();
        stackDemo.push(1);
        stackDemo.push(2);
        stackDemo.push(3);
        stackDemo.show();
        stackDemo.pop();
        stackDemo.show();

        //表达式运算 不带括号中缀表达式
        String expression="5+2*6-2+20";
        //数栈
        ArrayStackDemo numStack = new ArrayStackDemo(10);
        //运算符栈
        ArrayStackDemo operStack = new ArrayStackDemo(10);

        int index=0;
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';
        //处理多位数
        String keepNum="";
        while (true){
            //取字符 ASCI码表 0对应48,1对应49相差48
            ch=expression.substring(index,index+1).charAt(0);
            if(operStack.isOper(ch)){
                //运算符空直接入栈
                if(operStack.isEmpty()){
                    operStack.push(ch);
                }else{
                    //判断运算符优先级，如果当前准备入栈的运算符优先级低于栈顶元算符，先把栈顶运算符运算
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        //存放运算
                        numStack.push(res);
                        //存放当前运算符
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }
            }else {
                keepNum+=ch;
                //处理多位数，利用index判断当前字符是否位最后一位
                if(index==expression.length()-1){
                    numStack.push(Integer.valueOf(keepNum));
                    //最后一次使用keepNum.不需要重置keepNum
                }else {
                    //判断下一个字符是否为运算符
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.valueOf(keepNum));
                        //需要重置keepNum
                        keepNum="";
                    }
                }
            }
            index++;
            if (index>=expression.length()){
                break;
            }
        }

        while (true){
            if(operStack.isEmpty()){
                break;
            }else {
                num1=numStack.pop();
                num2=numStack.pop();
                oper=operStack.pop();
                res=numStack.cal(num1,num2,oper);
                numStack.push(res);
            }
        }
        System.err.println(numStack.pop());

    }
}

class ArrayStackDemo {

    public int maxSize;
    public int[] stack;
    public int top = -1;

    public ArrayStackDemo(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.err.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("栈空");
        }
        int result = stack[top];
        top--;
        return result;
    }

    /**
     * 从栈顶开始
     */
    public void show() {
        if (isEmpty()) {
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("%d\n", stack[i]);
        }
    }

    /**
     * 运算符优先级
     *
     * @param oper
     * @return 注意： java中int和char可以混用
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else return -1;
    }

    /**
     * 判断是否位运算符
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 运算 注意顺序
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }
    public int peek(){
        return stack[top];
    }

}
