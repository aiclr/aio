package org.bougainvilleas.spring.ioc.xml;

/**
 * create Plain Ordinary Java Object
 *
 * @author renqiankun
 */
public class User {

    public String name;
    public String hand;
    public String book;



    public void add(){
        System.out.println(name+"add......");
    }

    public void show(){
        System.out.println("::"+hand);
    }

    public void show1(){
        System.out.println(book);
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public void setName(String name) {
        this.name = name;
    }
}
