package org.bougainvilleas.spring.aop.annotation.mya;

@MyTable("sys_user")
public class User {

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @MyCloume("real_name")
    private String name;
    @MyCloume("psd")
    private String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
