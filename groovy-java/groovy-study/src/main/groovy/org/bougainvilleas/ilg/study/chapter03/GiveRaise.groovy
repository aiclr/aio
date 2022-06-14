package org.bougainvilleas.ilg.study.chapter03

import org.bougainvilleas.ilj.study.chapter03.Employee
import org.bougainvilleas.ilj.study.chapter03.Executive

void giveRaise(Employee employee)
{
    employee.raise(new BigDecimal(10000.00))
    //和下面语句效果相同
//    employee.raise(10000.00)
}

giveRaise new Employee()

//groovy 先找 Employee 的 raise(Number amount)
//发现没有 raise(BigDecimal amount)
//再找 Executive 的 raise(BigDecimal amount) 匹配
// 调用就会被路由到 Executive.raise(BigDecimal amount)
giveRaise new Executive()


/**
 * 举一反三
 */

void giveRaise1(employee)
{
    employee.raise(new BigDecimal(10000.00))
    //和下面语句效果相同
//    employee.raise(10000.00)
}

giveRaise new Employee()

//groovy 先找 Employee 的 raise(Number amount)
//发现没有 raise(BigDecimal amount)
//再找 Executive 的 raise(BigDecimal amount) 匹配
// 调用就会被路由到 Executive.raise(BigDecimal amount)
giveRaise new Executive()