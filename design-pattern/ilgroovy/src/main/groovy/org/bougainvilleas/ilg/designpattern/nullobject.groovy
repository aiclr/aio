package org.bougainvilleas.ilg.designpattern

/**
 * Null Object Pattern
 * 空对象模式
 * 使用一个特殊的对象来放置表示空的标记对象
 * null对象模式使用表示null的特殊对象，而不是使用实际的null。
 * 允许在null特殊对象上调用字段和方法引用。
 * 使用null特殊对象的结果在语义上应该等同于什么都不做。
 */
class Job {
    def salary
}

class Person3 {
    def name
    Job job
}

def people = [
        new Person3(name: 'Tom', job: new Job(salary: 1000)),
        new Person3(name: 'Dick', job: new Job(salary: 1200)),
]

def biggestSalary = people.collect { p -> p.job.salary }.max()
println biggestSalary

class NullJob extends Job {
    def salary = 0
}


people << new Person3(name: 'Harry', job: new NullJob())
biggestSalary = people.collect { p -> p.job?.salary }.max()
println biggestSalary

people << new Person3(name: 'Harry')

biggestSalary = people.collect { p -> p.job?.salary }.max()
println biggestSalary

/**
 * Tree example
 */

class NullHandlingTree {
    def left, right, value

    def size() {
        1 + (left ? left.size() : 0) + (right ? right.size() : 0)
    }

    def sum() {
        value + (left ? left.sum() : 0) + (right ? right.sum() : 0)
    }

    def product() {
        value * (left ? left.product() : 1) * (right ? right.product() : 1)
    }
}

def root = new NullHandlingTree(
        value: 2,
        left: new NullHandlingTree(
                value: 3,
                right: new NullHandlingTree(value: 4),
                left: new NullHandlingTree(value: 5)
        )
)
println root.size()//1+1+1+1
println root.sum()//2+3+4+5
println root.product()//2*3*4*5

/**
 * NullTree
 */
class Tree2 {
    def left = new NullTree(), right = new NullTree(), value

    def size() {
        1 + left.size() + right.size()
    }

    def sum() {
        value + left.sum() + right.sum()
    }

    def product() {
        value * left.product() * right.product()
    }
}

class NullTree {
    def size() { 0 }

    def sum() { 0 }

    def product() { 1 }
}

root = new Tree2(
        value: 2,
        left: new Tree2(
                value: 3,
                right: new Tree2(value: 4),
                left: new Tree2(value: 5)
        )
)
println root.size()
println root.sum()
println root.product()