package org.bougainvilleas.ilg.study.chapter03

/**
 * 业务场景: 需要搬一些重物,需要找一个愿意帮忙,而且有能力的帮手
 *
 * takeHelp() 接收一个helper,但是没有指定类型,类型默认为 Object
 * 调用了 helper的 helpMoveThings()方法
 *
 * 上述就是 能力式设计 Design By Capability (与 契约式设计 (Design By Contract) 相对应)
 *
 * 不同于 让 helper 遵守某些显式的接口,利用对象的能力---依赖一个隐式的接口
 * 被称作鸭子类型: 如果它走路像鸭子,叫起来也像鸭子,那他就是一只鸭子🦆
 *
 * 想要这种能力的类只需要实现该方法,而不需要扩展(extends)或实现(implements)任何东西
 * 更易于扩展
 *
 * @param helper
 * @return
 */
def takeHelp(helper) {
    helper.helpMoveThings()
}

class Man {
    void helpMoveThings() {
        println '\tMan\'s helping'
    }
}


class Woman {
    void helpMoveThings() {
        println '\tWoman\'s helping'
    }
}


class Elephant {
    void helpMoveThings() {
        println '\tElephant\'s helping'
    }
    void eatSugarcane() {
        println '\t\tI love sugarcane..'
    }
}

takeHelp(new Man())
takeHelp(new Woman())
takeHelp(new Elephant())



def takeHelpAndReward(helper){
    helper.helpMoveThings()
    if(helper.metaClass.respondsTo(helper,'eatSugarcane'))
    {
        helper.eatSugarcane()
    }
}
takeHelpAndReward(new Man())
takeHelpAndReward(new Woman())
takeHelpAndReward(new Elephant())
