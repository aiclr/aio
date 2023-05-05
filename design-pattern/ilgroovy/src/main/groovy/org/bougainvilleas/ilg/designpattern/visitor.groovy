package org.bougainvilleas.ilg.designpattern

abstract class Shape {}

class Rectangle extends Shape {
    def x, y, width, height

    Rectangle(x, y, width, height) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    def union(rect) {
        if (!rect) return this
        def minx = [rect.x, x].min()
        def maxx = [rect.x + width, x + width].max()
        def miny = [rect.y, y].min()
        def maxy = [rect.y + height, y + height].max()
        new Rectangle(minx, miny, maxx - minx, maxy - miny)
    }

    def accept(visitor) {
        visitor.visit_rectangle(this)
    }
}

class Line extends Shape {
    def x1, y1, x2, y2

    Line(x1, y1, x2, y2) {
        this.x1 = x1
        this.y1 = y1
        this.x2 = x2
        this.y2 = y2
    }

    def accept(visitor) {
        visitor.visit_line(this)
    }
}

class Group extends Shape {
    def shapes = []

    def add(shape) { shapes += shape }

    def remove(shape) { shapes -= shape }

    def accept(visitor) {
        visitor.visit_group(this)
    }
}

class BoundingRectangleVisitor {
    def bounds

    def visit_rectangle(rectangle) {
        if (bounds)
            bounds = bounds.union(rectangle)
        else
            bounds = rectangle
    }

    def visit_line(line) {
        def line_bounds = new Rectangle(line.x1, line.y1, line.x2 - line.y1, line.x2 - line.y2)
        if (bounds)
            bounds = bounds.union(line_bounds)
        else
            bounds = line_bounds
    }

    def visit_group(group) {
        group.shapes.each { shape ->
            shape.accept(this)
        }
    }
}

def group = new Group()
group.add(new Rectangle(100, 40, 10, 5))
group.add(new Rectangle(100, 70, 10, 5))
group.add(new Line(90, 30, 60, 5))
def visitor = new BoundingRectangleVisitor()
group.accept(visitor)
bounding_box = visitor.bounds
println bounding_box.dump()

/**
 * 使用Groovy闭包来提高代码的清晰度（并使其大小减半）
 */
abstract class Shape2 {
    def accept(Closure yield) { yield(this) }
}

class Rectangle2 extends Shape2 {
    def x, y, w, h

    def bounds() { this }

    def union(rect) {
        if (!rect) return this
        def minx = [rect.x, x].min()
        def maxx = [rect.x + w, x + w].max()
        def miny = [rect.y, y].min()
        def maxy = [rect.y + h, y + h].max()
        new Rectangle2(x: minx, y: miny, w: maxx - minx, h: maxy - miny)
    }
}

class Line2 extends Shape2 {
    def x1, y1, x2, y2

    def bounds() {
        new Rectangle2(x: [x1, x2].min(), y: [y1, y2].min(), w: (x2 - x1).abs(), h: (y2 - y1).abs())
    }
}

class Group2 {
    def shapes = []

    def leftShift(shape) { shapes += shape }

    def accept(Closure yield) { shapes.each { it.accept(yield) } }
}

group = new Group2()
group << new Rectangle2(x: 100, y: 40, w: 10, h: 5)
group << new Rectangle2(x: 100, y: 70, w: 10, h: 5)
group << new Line2(x1: 90, y1: 30, x2: 60, y2: 5)
def bounds
group.accept { bounds = it.bounds().union(bounds) }
println bounds.dump()

/**
 * Advanced Example
 */
interface Visitor {
    void visit(NodeType1 n1)

    void visit(NodeType2 n2)
}

interface Visitable {
    void accept(Visitor visitor)
}

class DefaultVisitor implements Visitor {
    void visit(NodeType1 n1) {
        for (int i = 0; i < n1.children.length; ++i) {
            n1.children[i].accept(this)
        }
    }

    void visit(NodeType2 n2) {
        for (int i = 0; i < n2.children.length; ++i) {
            n2.children[i].accept(this)
        }
    }
}

class NodeType1 implements Visitable {
    Visitable[] children = new Visitable[0]

    void accept(Visitor visitor) {
        visitor.visit(this)
    }
}

class NodeType2 implements Visitable {
    Visitable[] children = new Visitable[0]

    void accept(Visitor visitor) {
        visitor.visit(this)
    }
}

class NodeType1Counter extends DefaultVisitor {
    int count = 0

    void visit(NodeType1 n1) {
        count++
        super.visit(n1)
    }
}

def root = new NodeType1()
root.children = new Visitable[2]
root.children[0] = new NodeType1()
root.children[1] = new NodeType2()

def counter=new NodeType1Counter()
counter.visit(root)
println counter.count
/**
 *
 */
class DefaultVisitor2 {
    void visit(NodeType3 n3) {
        n3.children.each { visit(it) }
    }

    void visit(NodeType4 n4) {
        n4.children.each { visit(it) }
    }

    void visit(Visitable2 v) {}
}

interface Visitable2 {}

class NodeType3 implements Visitable2 {
    Visitable2[] children = []
}

class NodeType4 implements Visitable2 {
    Visitable2[] children = []
}

class NodeType3Counter extends DefaultVisitor2 {
    int count = 0

    void visit(NodeType3 n3) {
        count++
        super.visit(n3)
    }
}
root = new NodeType3()
root.children = new Visitable2[2]
root.children[0] = new NodeType3()
root.children[1] = new NodeType4()

counter=new NodeType3Counter()
counter.visit(root)
println counter.count
/**
 *
 */
class DefaultVisitor3 {
    void visit(Visitable3 v) {
        doIteraton(v)
    }

    void doIteraton(Visitable3 v) {
        v.children.each {
            visit(it)
        }
    }
}

interface Visitable3 {
    Visitable3[] getChildren()
}

class NodeType5 implements Visitable3 {
    Visitable3[] children = []
}

class NodeType6 implements Visitable3 {
    Visitable3[] children = []
}

class NodeType5Counter extends DefaultVisitor3 {
    int count = 0

    void visit(NodeType5 n5) {
        count++
        super.visit(n5)
    }
}
root = new NodeType5()
root.children = new Visitable3[2]
root.children[0] = new NodeType5()
root.children[1] = new NodeType6()

counter=new NodeType5Counter()
counter.visit(root)
println counter.count