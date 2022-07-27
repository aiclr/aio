package org.bougainvilleas.ilg.study.chapter15

data=new File('car.dat').readLines()
//获取第一行 表头
props=data[0].split(", ")
//删除第一行的 表头
data-=data[0]
//声明闭包 计算没年平均行驶距离
def averageMilesDrivenPerYear={miles.toLong()/(2022-year.toLong())}

cars=data.collect{
    car=new Expando()
    it.split(", ").eachWithIndex{  value,index  ->
        //props 内是表头  给car 动态添加属性 miles year make
        car[props[index]]=value
    }
    //给car 动态添加 方法
    car.ampy=averageMilesDrivenPerYear
    car//返回car
}
//先输出表头
props.each{name->print "$name "}
println " Avg. MPY"
//方法名
ampyMethod='ampy'
cars.each {car->
    for(String property:props)
    {//输出属性值
        print "${car[property]} "
    }
    //调用方法，输出结果
    println car."$ampyMethod"()
}
//获取第一个car
car=cars[0]
//输出属性及调用方法
println "$car.miles $car.year $car.make ${car.ampy()}"