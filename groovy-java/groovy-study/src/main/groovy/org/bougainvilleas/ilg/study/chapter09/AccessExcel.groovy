package org.bougainvilleas.ilg.study.chapter09

/**
 * MARK 不好使啊不好使
 * Microsoft excel 2003
 * https://www.connectionstrings.com/microsoft-excel-odbc-driver/
 *
 * 下载驱动
 * https://www.microsoft.com/zh-CN/download/details.aspx?id=13255
 *
 * Driver={Microsoft Excel Driver (*.xls)};DriverId=790;Dbq=C:\MyExcel.xls;DefaultDir=c:\mypath;
 * Driver={Microsoft Excel Driver (*.xls)};Dbq=C:\MyExcel.xls;ReadOnly=0;
 */

def sql=groovy.sql.Sql.newInstance("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};Dbq=\\tmp\\device.xls;ReadOnly=0;","","")
println "id\t\tname"
sql.eachRow('SELECT * FROM [device]'){
    println "${it.id}\t\t${it.name}"
}

sql.close()