package org.bougainvilleas.ilg.designpattern

/**
 * 方形桩
 */
class SquarePeg {
    def width
}

/**
 * 圆形桩
 */
class RoundPeg {
    def radius
}

/**
 * 圆孔
 */
class RoundHole {
    //半径
    def radius

    def pegFits(peg) {
        peg.radius <= radius
    }

    String toString() { "RoundHole with radius $radius" }
}

class SquarePegAdapter {
    def peg

    def getRadius() {
        Math.sqrt(((peg.width / 2)**2) * 2)
    }

    String toString() {
        "SquarePegAdapter with peg width $peg.width (and notional radius $radius)"
    }
}

def hole = new RoundHole(radius: 4.0)
(4..7).each { w ->
    //具体适配器
    def peg = new SquarePegAdapter(peg: new SquarePeg(width: w))
    if (hole.pegFits(peg)) {
        println "peg $peg fits in hole $hole"
    } else {
        println "peg $peg does not fit in hole $hole"
    }
}

println 'inheritance example'

/**
 * inheritance example
 * 继承示例
 */
class SquarePegAdapter2 extends SquarePeg {
    def getRadius() {
        Math.sqrt(((width / 2)**2) * 2)
    }

    String toString() {
        "SquarePegAdapter2 with peg width $width (and notional radius $radius)"
    }
}

(4..7).each { w ->
    //具体适配器
    def peg = new SquarePegAdapter2(width: w)
    if (hole.pegFits(peg)) {
        println "peg $peg fits in hole $hole"
    } else {
        println "peg $peg does not fit in hole $hole"
    }
}

println 'adapting using closures'

interface RoundThing {
    def getRadius()
}

def adapter = {
    p -> [getRadius: { Math.sqrt(((p.width / 2)**2) * 2) }] as RoundThing
}

(4..7).each { w ->
    def peg = new SquarePeg(width: w)
    if (hole.pegFits(adapter(peg))) {
        println "peg $peg fits in hole $hole"
    } else {
        println "peg $peg does not fit in hole $hole"
    }
}

println 'adapting using the ExpandoMetaClass'


(4..7).each { w ->
    def peg = new SquarePeg(width:w)
    // 添加 属性 radius
    peg.metaClass.radius = Math.sqrt(((peg.width / 2)**2) * 2)

    if (hole.pegFits(peg)) {
        println "peg $peg fits in hole $hole"
    } else {
        println "peg $peg does not fit in hole $hole"
    }
}