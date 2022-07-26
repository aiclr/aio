package org.bougainvilleas.ilg.study.chapter13

class User{
    User(int sex, int salary, int reward) {
        this.sex = sex
        this.salary = salary
        this.reward = reward
    }
    int sex
    int salary
    int reward
    /**
     * 当某个不存在的方法被调用时，该方法回介入
     */
    def methodMissing(String name,args){
        println "You called $name with ${args.join(', ')}."
        args.size()
    }
}
User.metaClass {

    all = { ->
        println delegate
        delegate.salary+delegate.reward
    }

    'static' {
        /**
         * 1-奇数=男 0-偶数=女
         */
        isFemale = { val -> (val.sex & 1) == 0 }
    }
    constructor = { int reward ->
        //此处使用了Integer中现有的接受一个int的构造器，务必确认没有递归调用，否则会 stackoverflowError
        println 'Intercepting constructor call Calendar'
        new User(sex:1,salary: 1000, reward:reward)
    }
}