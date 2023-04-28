package org.bougainvilleas.base.designpattern.pattern.structural.proxy.static1;

public class Client {

    public static void main(String[] args) {

        ITeacherDao teacherDao=new TeacherProxy(new Teacher());
        teacherDao.teach();


    }

}
