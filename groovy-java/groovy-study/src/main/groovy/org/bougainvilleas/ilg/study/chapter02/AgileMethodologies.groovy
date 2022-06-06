package org.bougainvilleas.ilg.study.chapter02

/**
 * 枚举值可以打印 ${this} 可以序列化
 */
enum Methodologies{
    Evo(5),
    XP(21),
    Scrum(30);
    final int daysInIteration
    Methodologies(days){ daysInIteration=days}

    def iterationDetails(){
        println "${this} recommends $daysInIteration days for iteration"
    }
}
for(methodology in Methodologies.values()){
    methodology.iterationDetails()
}
