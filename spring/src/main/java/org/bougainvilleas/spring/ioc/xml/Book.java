package org.bougainvilleas.spring.ioc.xml;

/**
 * DI 注入属性~依赖注入
 *
 * @author renqiankun
 */
public class Book {

    private String bname;
    private String bauthor;

    public Book(String bname, String bauthor) {
        this.bname = bname;
        this.bauthor = bauthor;
    }
     public void show(){
         System.out.println(bauthor+"编写了"+bname);
     }
}
