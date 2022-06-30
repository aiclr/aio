package org.bougainvilleas.ilg.study.chapter04


/**
 * 记忆化 memoization 改进性能
 * dynamic programming 动态规划问题
 * 执行期间将子问题的结果保存，当调用重复的计算时，只需要使用保存下来的结果，就避免重复运行，极大地减少了计算时间
 * 记忆化可以将一些算法的计算时间复杂度从输入规模（n）的指数级(O(k^n)) 降低到只有线性级（O(n)）
 *
 * 记忆化技术时以空间换取速度，消耗空间取决于使用不重复的参数取值调用递归方法的次数
 * 对于较大的问题规模，内存需求回急剧增长
 * 简单调用memoize() 会使用一个没有限制的缓存，可以使用memoizeAtMost() 方法代替它
 * 该方法会限制缓存发大小而且当达到该限制时，最近最少使用（Least Recently Used,LRU）的值会从缓存中移除，以容纳新的值
 *
 * 还可以使用诸如memoizeAtLeast()和memoizeAtLeastBetween()之类的变种，前者设置缓存下线，后者可以同时设置下限和上限
 * 除了管理缓存 memoize()函数的还提供了线程安全性，可以安全地从多个线程访问缓存
 */


//案例 卖杆业务，不同长度的杆零售价格不同，批发某一特定长度的杆，
// 比如27英寸（1英寸=2.54厘米），然后将其分割成长度不一的杆销售，以实现收入最大化


/**
 * timeIt 报告给定闭包运行所消耗的时间，
 * 并报告对于给定长度的杆，可以期望的最大收入，最大收入时闭包输出
 */
def timeIt(length, closure) {
    long start = System.nanoTime()
    println "Max revenue for $length is ${closure(length)}"
    long end = System.nanoTime();
    println "Time taken ${(end - start) / 1.0e9} seconds"
}
/**
 * 价格数组
 * 从0-30英寸（不包含）定义一组零售价格，
 * 之所以要包含长度为0的杆，是为了弥合基于0的数组索引所带来的问题
 * 加入 0 可以以杆长度作为索引值来获取其零售价格，
 * 比如rodPrices[1]表示的就是长度为1的杆的价格为1
 */
def rodPrices = [0, 1, 3, 4, 5, 8, 9, 11, 12, 14,
                 15, 15, 16, 18, 19, 15, 20, 21, 22, 24,
                 25, 24, 26, 28, 29, 35, 37, 38, 39, 40]

//原材料杆的长度，分割该杆售卖以实现收入最大化
def desiredLength=27

//该类 保存总价格和分割后各段的长度
@groovy.transform.Immutable
class RevenueDetails{
    int revenue
    ArrayList splits
}

/**
 * length=0 递归结束
 * 给定一个长度，尝试尽可能多的分割组合
 * 1和length-1，2和length-2，3和length-3 ...
 * 依次类推 同时求出每种组合的最大收入，
 * 对于每对长度，递归地调用cutRod（）方法
 * 1和 (length-1)-1,2和 (length-1)-2 ...
 *
 * 会有大批重复计算的组合，耗时很长 212秒
 */
def cutRod(prices,length){
    if(length==0)
        new RevenueDetails(0,[])
    else{
        def maxRevenueDetails=new RevenueDetails(Integer.MIN_VALUE,[])
        for(rodSize in 1..length){
            def revenueFromSecondHalf=cutRod(prices,length-rodSize)
            def potentialRevenue=new RevenueDetails(
                    prices[rodSize]+revenueFromSecondHalf.revenue,
                    revenueFromSecondHalf.splits+rodSize)
            if(potentialRevenue.revenue>maxRevenueDetails.revenue)
                maxRevenueDetails=potentialRevenue
        }
        maxRevenueDetails
    }
}
//timeIt desiredLength,{length->cutRod(rodPrices,length)}

/**
 * 将函数转换为闭包，并在其上调用memoize() 方法之后，将结果保存到cutRod2 变量中通过这些步骤，
 * 创建Memoize类的一个专用实例，该实例中有一个指向所提供闭包的引用，还有一个结果的缓存
 * 当调用该闭包时，该实例会在返回结果之前将响应缓存下来在随后的调用中，如果对应某个参数已经有了相应的缓存，则返回缓存的结果
 * 节省大量时间 耗时0.17秒
 */
def cutRod2
cutRod2={ prices,length->
    if(length==0)
        new RevenueDetails(0,[])
    else{
        def maxRevenueDetails=new RevenueDetails(Integer.MIN_VALUE,[])
        for(rodSize in 1..length){
            def revenueFromSecondHalf=cutRod2(prices,length-rodSize)
            def potentialRevenue=new RevenueDetails(
                    prices[rodSize]+revenueFromSecondHalf.revenue,
                    revenueFromSecondHalf.splits+rodSize)
            if(potentialRevenue.revenue>maxRevenueDetails.revenue)
                maxRevenueDetails=potentialRevenue
        }
        maxRevenueDetails
    }
}.memoize()

timeIt desiredLength,{length->cutRod2(rodPrices,length)}