package org.bougainvilleas.ilg.study.chapter05

/**
 * -= 重载操作符
 * 将左侧字符串中与右侧字符串相匹配的部分去掉
 */
str="It's a rainy day in Seattle"
println str
str-="rainy "
println str

/**
 * 迭代
 * held hele helf helg helh heli helj helk hell helm
 */
for(str in 'held'..'helm')
print "$str "
println ""
