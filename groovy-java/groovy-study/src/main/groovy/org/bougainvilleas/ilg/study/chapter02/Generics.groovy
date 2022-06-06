package org.bougainvilleas.ilg.study.chapter02

/**
 * groovy 支持动态行为的同时支持泛型  元编程
 * groovy 编译器不会像java编译器那样执行类型检查
 * groovy 的动态类型特性将与泛型类型相互作用,使代码运行
 * groovy更大程度上将类型信息看作一个建议,
 * 当对集合进行循环时,groovy会尝试将其中的元素强制转换成int
 * 如果无法转换,则会导致运行错误
 *
 */
class Generics {

    static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>()
        list.add(1)
        list.add(2.0)
        list.add('hello')
        println "List populated"
        for(int element : list){println element}
    }
}

