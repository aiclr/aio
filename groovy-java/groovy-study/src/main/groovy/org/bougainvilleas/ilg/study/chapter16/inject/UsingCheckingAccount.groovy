package org.bougainvilleas.ilg.study.chapter16.inject


class CheckingAccount {
    //新添加 audit 当其他方法被调用时，希望该方法也适时调用
    def audit(amount) { if (amount > 10000) print "auditing..." }

    def deposit(amount) { println "depositing $amount..." }

    def withdraw(amount) { println "withdrawing $amount..." }
}

def account = new CheckingAccount()
account.deposit(1000)
account.deposit(12000)
account.withdraw(11000)
