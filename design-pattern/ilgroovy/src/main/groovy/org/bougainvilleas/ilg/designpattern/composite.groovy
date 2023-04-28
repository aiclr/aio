package org.bougainvilleas.ilg.designpattern

/**
 * 组合模式
 */

abstract class Component {
    def name

    def toString(indent) { ("-" * indent) + name }
}

println(("-" * 9))

class Composite extends Component {
    private children = []

    def toString(indent) {
        def s = super.toString(indent)
        children.each { child ->
            s += "\n" + child.toString(indent + 1)
        }
        s
    }

    /**
     * << 操作符 指代 leftShift
     */
    def leftShift(component) {
        children << component
    }
}

class Leaf extends Component {}

def root = new Composite(name: "root")

//<< 操作符相当于 root.leftShift(new Leaf(name: "leaf A"))
root << new Leaf(name: "leaf A")

def comp = new Composite(name: "comp B")
root << comp
root << new Leaf(name: "leaf C")
comp << new Leaf(name: "leaf B1")
comp << new Leaf(name: "leaf B2")
println root.toString(0)