package org.bougainvilleas.ilg.study.chapter07.weather

import groovy.json.JsonSlurper


/**
 {
 "weatherinfo": {
 "city": "北京",
 "cityid": "101010100",
 "temp": "27.9",
 "WD": "南风",
 "WS": "小于3级",
 "SD": "28%",
 "AP": "1002hPa",
 "njd": "暂无实况",
 "WSE": "<3",
 "time": "17:55",
 "sm": "2.1",
 "isRadar": "1",
 "Radar": "JC_RADAR_AZ9010_JB"
 }
 }
 */
self=101010100//城市代码
def url="http://www.weather.com.cn/data/sk/${self}.html".toURL()
def data = new JsonSlurper().parseText(url.readLines("UTF-8")[0])
println data.weatherinfo.temp as double