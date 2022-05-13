package org.bougainvilleas.base.dsaa;

/**
 * 含有多个空格的ASCII串
 * 求最长非空格字符串的长度，尽可能最优
 *
 * aa bc aaaa aaa==>4
 */
public class Test {
    public static void main(String[] args) {
        String a="aa bc aaaa aaa";
        String a1=" aa bc aaaa aaa";
        String a2="aa bc aaaa aaa aaa    aaa aa   aaaaa";
        String a3=" ";
        String a33="   ";
        String a4="";
        String a5="aa bc  aaaa aaa  ";
        String a6="aa bc   aaaa aaa";
        System.out.println(getStrLengthPlus(a));
        System.out.println(getStrLengthPlus(a1));
        System.out.println(getStrLengthPlus(a2));
        System.out.println(getStrLengthPlus(a3));
        System.out.println(getStrLengthPlus(a33));
        System.out.println(getStrLengthPlus(a4));
        System.out.println(getStrLengthPlus(a5));
        System.out.println(getStrLengthPlus(a6));
    }

    public static int getStrLength(String str){
        int result=0;
        int temp=0;
        str.trim();
        char[] chars = str.toCharArray();
        for (char c:chars){
          if(!Character.isSpaceChar(c)){
              temp+=1;
              if(result<temp){
                  result=temp;
              }
          }else {
              temp=0;
          }
        }
        return result;
    }
    //减少循环次数
    public static int getStrLengthPlus(String str){
        int result=0;
        int temp=0;
        str.trim();
        char[] chars = str.toCharArray();
        //减少循环次数
        for (int i=0;i<chars.length;i++){
            if(!Character.isSpaceChar(chars[i])){
                temp+=1;
                if(result<temp){
                    result=temp;
                }
            }else {
                //最长字符串==剩余字符串可直接退出循环
                if(result>=chars.length-1-i){
                    break;
                }
                temp=0;
            }
        }
        return result;
    }
}
