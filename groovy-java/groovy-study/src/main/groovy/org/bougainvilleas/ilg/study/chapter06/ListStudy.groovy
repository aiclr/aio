package org.bougainvilleas.ilg.study.chapter06

lst = [1, 3, 2, 1, 8, 9, 2, 6]
println lst
println lst.getClass().name

println lst[0]
println lst[lst.size() - 1]

/**
 * 负值 从右往左遍历
 */
println lst[-1]
println lst[-2]
/**
 * 使用 Range 对象获得集合中的几个连续的值
 * Range 可以使用负的索引值
 */
println lst[2..5]
println lst[-6..-3]

/**
 * 使用 2..5 这样的Range 作为索引 java.util.ArrayList 会返回一个新 ArrayList 实例
 * 修改 subLst 不会影响lst
 *
 *
 * 注意 教旧的groovy版本 修改 subLst 会影响lst
 */
subLst = lst[2..5]
println lst.dump()
println subLst.dump()
subLst[0] = 55
println "After subLst[0]=55 lst=$lst"

println("遍历")

lst.each { println it }

println("求和")
total = 0
lst.each { total += it }
println "Total is $total"

/**
 * 创建存放结果的集合 doubled
 * 对原有集合 lst 迭代时将元素加倍
 * 使用 << 操作符(映射到 leftShift()方法)将所得的结果 存放到 doubled 中
 */
println '每个元素变为原来的2倍'
doubled = []
lst.each { doubled << it * 2 }
println doubled

/**
 * collect 在每个元素上执行操作并返回一个结果集合
 * collect() 方法 和 each() 一样
 * 会在集合中的每个元素上调用传入的闭包
 * collect() 会把闭包的返回值收集到一个集合中，最后返回这个生成的结果集合
 */
println '使用collect() 每个元素变为原来的2倍'
println lst.collect { it * 2 }

/**
 * find() 会找到第一次出现的匹配对象
 * find() 会对集合进行迭代，但是只迭代到 闭包返回true为止
 * 得到true 就会停止遍历 并将当前元素返回
 * 如果遍历结束也没得到true 则返回null
 */
lst = [1, 3, 2, 1, 8, 9, 2, 6]
println '使用find() 查找'
println lst.find { it == 2 }
println lst.find { it > 4 }
/**
 * 查找所有匹配的对象
 */
println '使用findAll() 查找全部'
println lst.findAll { it == 2 }
println lst.findAll() { it > 4 }
/**
 * 查找第一个匹配对象的位置
 */
println 'findIndexOf() 查找索引'
println lst.findIndexOf { it == 2 }
println lst.findIndexOf { it > 4 }


/**
 * 计算字符串总字符数
 */
println '计算字符串总字符数'
lst = ['Programming', 'In', 'Groovy']
count = 0
lst.each { count += it.size() }
println count

println lst.collect{it.size()}.sum()
/**
 * inject 会对集合中的每一个元素调用闭包
 * inject 会把将要注入的一个初始值当作一个参数，通过carryOver 参数把它放到第一次对闭包的调用中
 * 之后会把从闭包获得的结果 注入到随后对闭包的调用中
 *
 * 如果 想在集合中的每个元素上应用某个计算，获得一个累积的结果
 * 与collect() 方法相比 会首选 inject()
 */
println lst.inject(1) {carryOver,element-> carryOver+element.size()}

println 'join()'
/**
 * join 会迭代每个元素 将每个元素作为输入参数给定的字符连接起来
 */
println lst.join('_')

println '通过索引 替换 List 中的元素'
lst[0] = ['Be','Productive']
println lst

println '通过flatten 将 List 拉平'
lst=lst.flatten()
println lst
/**
 * -操作符
 * 右操作数中元素 会被从左侧的集合中移除
 * 如果元素不存在，直接忽略
 * 可以为右操作数提供一个列表 或是单个值
 */
println '-操作符(minus()方法)'
println lst-['Productive','In','2333']
println lst-'In'


println 'reverse 反转集合'
lst=[0,1,2,3,4,5,6,7,8,9]
lstBak=lst.reverse()
println lst
println lstBak
println lst.dump()
println lstBak.dump()

/**
 * 第一次调用 size 是在列表上 返回 3 集合元素个数
 * 第二次调用 size 因为 * 影响 作用于列表中的每个元素的展开操作符的影响，返回一个 List
 * 其中每个元素分别保存原始集合中相应元素的大小 lst*size()作用与 lst.collect{it.size()} 相同
 */
println '在每个元素上执行操作，不用显示使用迭代'
lst = ['Programming', 'In', 'Groovy']
println lst.size()
println lst.collect{it.size()}
println lst*.size()

/**
 * ArrayList 元素个数必须与方法期望的参数个数相同
 */
println '方法调用中使用ArrayList 传多个参数'
def words(a,b,c,d){
    println "$a $b $c $d"
}
lst = ['Be','Programming', 'In', 'Groovy']
words(*lst)//使用 * 操作符将集合拆成单个对象
