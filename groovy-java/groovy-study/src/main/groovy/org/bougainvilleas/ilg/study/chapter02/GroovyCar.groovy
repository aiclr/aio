package org.bougainvilleas.ilg.study.chapter02

class Car {
    //def 在这个上下文中声明了一个属性,groovy 会自动创建 getter/setter方法
    def miles = 0
    //final 只读,只有getter 没有setter,任何尝试修改year的操作都会导致异常
    final year

    Car(theYear) { year = theYear }
}

Car car = new Car(2021)
println "Year: $car.year"
//实际调用的是 getter
println "Miles: $car.miles"
println 'Setting miles'
car.miles=25;
println "Miles: $car.miles"

/**
 * 可以根据需要向声明中加入类型信息
 * groovy 不区分 public private protected
 *      实现拒绝任何修改的setter 可以实现私有属性
 */
class CarPro {
    //def 在这个上下文中声明了一个属性,groovy 会自动创建 getter/setter方法
    //groovy 不区分 public private protected
    private miles = 0
    //final 只读,只有getter 没有setter,任何尝试修改year的操作都会导致异常
    final year

    CarPro(theYear) { year = theYear }

    def getMiles() {
        println 'getMiles called'
        miles
    }

    /**
     * 拒绝任何修改的更改器
     */
    private void setMiles(miles) {
        throw new IllegalAccessException("You're not allowed to change miles")
    }

    /**
     * 通过此方法修改私有变量 miles
     * @param dist
     * @return
     */
    def drive(dist) {
        if (dist > 0)
            miles += dist
    }
}




CarPro carPro = new CarPro(2022)
println "Year: $carPro.year"
//实际调用的是 getter
println "Miles: $carPro.miles"
println 'Driving'
carPro.drive(10);
println "Miles: $carPro.miles"

try {
    print 'Can I set the year? '
    carPro.year = 1994
} catch (groovy.lang.ReadOnlyPropertyException ex) {
    println ex.message
}
try {
    print 'Can I set the miles? '
    carPro.miles = 25
} catch (IllegalAccessException ex) {
    println ex.message
}


//优雅使用 访问器和更改器

Calendar.instance//Calendar.getInstance()
def str
str = 'hello'
/**
 * str.getClass().getName()
 * 不能用于Map Builder等类型
 */
str.class.name
//推荐使用
str.getClass().name