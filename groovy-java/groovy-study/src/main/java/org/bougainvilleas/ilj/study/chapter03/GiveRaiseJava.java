package org.bougainvilleas.ilj.study.chapter03;

import java.math.BigDecimal;

/**
 * java 基于目标对象的类型简单分配方法 (通过 字节码可以看出来)
 */
public class GiveRaiseJava
{
    public static void main(String[] args)
    {
        giveRaise(new Employee());
        giveRaise(new Executive());
    }

    //目标对象 Employee
    public static void giveRaise(Employee employee)
    {
        employee.raise(new BigDecimal(10000.00));
    }

}

class Employee
{
    public void raise(Number amount)
    {
        System.err.println("Employee got raise");
    }
}

class Executive extends Employee
{
    public void raise(Number amount)
    {
        System.err.println("Executive got raise");
    }

    public void raise(BigDecimal amount)
    {
        System.err.println("Executive got outlandish raise");
    }
}