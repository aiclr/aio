package org.bougainvilleas.ilg.study.chapter19
/**
 * 将想要的 方法 添加到了 Integer 类和 Calendar 类的 ExpandoMetaClass 中
 * 调用这些流畅的方法，会被路由到添加的方法
 */
Integer.metaClass{
    getDays={->
        delegate
    }
    getAgo={->
        def date =Calendar.instance
        date.add(Calendar.DAY_OF_MONTH,-delegate)
        date
    }
}

Calendar.metaClass.at={Map time->
    def hour=0
    def minute=0
    time.each {key,value->
        hour=key.toInteger()
        minute=value.toInteger()
    }
    delegate.set(Calendar.HOUR_OF_DAY,hour)
    delegate.set(Calendar.MINUTE,minute)
    delegate.set(Calendar.SECOND,0)
    delegate.time
}

println 2.days.ago.at(4:30)