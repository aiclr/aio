package org.bougainvilleas.ilg.study.chapter19

class DateUtil2{
    static int getDays(Integer self){self}
    static Calendar getAgo(Integer self){
        def date = Calendar.instance
        date.add(Calendar.DAY_OF_MONTH,-self)
        date
    }
    static Date at(Calendar self,Map time){
        def hour=0
        def minute=0
        time.each {key,value->
            hour=key.toInteger()
            minute=value.toInteger()
        }
        self.set(Calendar.HOUR_OF_DAY,hour)
        self.set(Calendar.MINUTE,minute)
        self.set(Calendar.SECOND,0)
        self.time
    }
}
use(DateUtil2){
    println 2.days.ago.at(4:30)//用 冒号 代替点 4.30
}