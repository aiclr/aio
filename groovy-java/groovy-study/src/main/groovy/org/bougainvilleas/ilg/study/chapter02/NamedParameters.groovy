package org.bougainvilleas.ilg.study.chapter02
/**
 * Groovy中可以灵活地初始化一个JavaBean 类。在构造对象时，可以简单地以逗号分隔的名值对来给出属性值
 * 要使此类操作正确执行，类中必须有一个无参构造器,没有定义构造器，编译器会提供一个无参的构造器
 * 定义了带参数的构造器，则编译器不会再为我们创建无参构造器,需要手动创建
 */
class Robot {
    def type,height,width
    /**
     * 显式地将第一个形参指定为Map ，可以避免这种混乱
     *
     * 如果实参包含的不是两个对象外加一个任意的名值对，代码就会报错
     * @param location
     * @param weight
     * @param fragile
     * @return
     */
    def access(Map location,weight,fragile)
    {
        println "Received fragile? $fragile,weight: $weight,loc: $location"
    }

}
robot =new Robot(type:'arm',width: 10,height: 40)
println "$robot.type, $robot.height, $robot.width"
/**
 * 可以修改参数顺序
 * 如果发送的实参的个数多于方法的形参的个数，而且多出的实参是名值对，那么Groovy会假设方法的第一个形参是一个Map ，
 * 然后将实参列表中的所有名值对组织到一起，作为第一个形参的值
 *
 * 再将剩下的实参按照给出的顺序赋给其余形参
 *
 */
robot.access(x:30,y:21,z:10,50,true)
robot.access(50,true,x:30,y:20,z:10)