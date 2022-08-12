package org.bougainvilleas.ilg.study.chapter19

/**
 * Category 分类唯一的限制是，只能在 use() 块内使用该 DSL
 * 也算是优势，方法注入可控
 * 一旦离开代码块，注入的方法就会从上下文 Context 中丢弃，比较理想
 */
class DateUtil{
    static int getDays(Integer self){self}
    static Calendar getAgo(Integer self){
        def date = Calendar.instance
        date.add(Calendar.DAY_OF_MONTH,-self)
        date
    }
    static Date at(Calendar self,Double time){
        def hour=(int)(time.doubleValue())
        def minute=(int)(Math.round((time.doubleValue()-hour)*100))
        self.set(Calendar.HOUR_OF_DAY,hour)
        self.set(Calendar.MINUTE,minute)
        self.set(Calendar.SECOND,0)
        self.time
    }
}
/**
 * DSLUsingCategory2.groovy 优化为
 * println 2.days.ago.at(4:30)
 * 更符合人类阅读方式
 */
use(DateUtil){
    println 2.days.ago.at(4.30)
}