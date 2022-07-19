package org.bougainvilleas.ilg.study.chapter09

import groovy.sql.DataSet

/**
 * Groovy SQL GSQL
 * GSQL 包装 JDBC
 *
 * 添加依赖： runtimeOnly 'mysql:mysql-connector-java:8.0.29'
 *
 * 连接数据库 调用 static newInstance() 方法创建一个groovy.sql.Sql类实例
 * 如果已经有了一个 java.sql.Connection 实例或 java.sql.DataSource 实例
 * 可以使用Sql类的相应构造器创建 groovy.sql.Sql实例
 *
 *  处理结束后 调用 close() 方法关闭数据库连接
 *
 */
def sql = groovy.sql.Sql.newInstance('jdbc:mysql://192.168.1.131:3306/ejsc',
        'park', 'park123', 'com.mysql.cj.jdbc.Driver')
println sql.connection.catalog


/**
 * 执行查询sql， eachRow 迭代
 * 使用 eachRow() 提供 GroovyResultSet对象来访问表中的列，可以直接使用列名，也可以使用索引（从0开始）
 */
println 'ID                  NAME'//输出的头部（列名）是数据返回的
sql.eachRow('select * from device') {
    printf "%-20s%s\n", it[0], it.name
}

/**
 * 从数据库获取列名
 * eachRow() 重载版本 接收两个闭包，一个用于元数据（列名） 一个用于数据
 * 元数据闭包仅调用一次（在sql语句执行之后）并以一个ResultSetMetaData 实例为参数
 * 另一个闭包会对结果中的每一行调用一次
 */
processMeta = { resultSetMetaData ->
    resultSetMetaData.columnCount.times { i ->
        if (i == 0 || i == 4) {
            printf "%-21s", resultSetMetaData.getColumnLabel(i + 1)
        }
    }
    println ""
}

sql.eachRow('select * from device', processMeta) {
    printf "%-20s %s\n", it[0], it.name
}

/**
 * 处理所有行 不适用迭代器
 * rows方法 返回一个结果数据的 ArrayList 实例
 */
rows = sql.rows('select * from device')
println "device rows ${rows.size()}"
/**
 * firstRow 获取结果的第一行
 */
firstRow = sql.firstRow('select * from device')
println "device firstRow ${firstRow}"


/**
 * 数据转为xml
 */
bldr = new groovy.xml.MarkupBuilder()
bldr.devices {
    sql.eachRow('SELECT * FROM device') {
        device(id: it[0], name: it.name)
    }
}
println ""

/**
 * MARK DataSet findAll 方法拼接where 不好使啊不好使
 * DataSet 将结果接受为 groovy.sql.DataSet 依次过滤数据
 * Sql 类的dataSet() 方法接收一个表名，并返回一个虚拟代理——直到迭代时，它才去取实际的行
 */

def devices = sql.dataSet('device')
devices.each { println it }

class Device{
    int id;
    String did;
    String name;
    String model;
}

def one=new DataSet(sql,Device)
//        .findAll {it.id > 9}//where 不好使
one.each {println it}


println sql.rows('SELECT * from device').size()
one.add(id:1,did:'123',name:'456',model:'789')
println sql.rows('SELECT * from device').size()
sql.execute('delete from device where id=1')


sql.executeInsert("""INSERT INTO device(id,did,name,model)
values(2,'123','456','789')
""")
println sql.firstRow('SELECT * from device where id=2')
sql.execute('delete from device where id=2')
sql.close()