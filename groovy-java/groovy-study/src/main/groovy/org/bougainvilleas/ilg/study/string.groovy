package org.bougainvilleas.ilg.study


/**
 * triple-double-quoted string
 *
 * 反斜杠在脚本中用于脚本折行 wrap
 * """\
 * aaa
 * 相当于
 * """aaa
 *
 * 三双引号中 单引号 双引号无需转义
 * 使用反斜杠作为转义字符
 */
def str1="""
a\\aa\
 b'/b\'b\\
  c"c"c\\\\
"""

println str1


/**
 * slash string
 * 正斜杠字符串
 * 主要用作正则表达式
 *
 * 行内反斜杠不需要转义
 * 作为字符流时行首行末的反斜杠需要使用反斜杠转义
 *
 * 正斜杠需要用反斜杠转义
 *
 * 单行不能以正反斜杠结尾 -- 编译失败
 *
 * /aaa// ----> 末尾双正斜杠=代码注释
 * /aaa{'/'}/
 *
 * /aaa\/ ---> 末尾反斜杠被当作转移正斜杠
 * /aaa{'\\'}/
 */

//def errStr1=/aaa//
//def errStr2=/aaa\/

def str2= /
\a\/aa\
b'b\'b\\
b'b\'b
\\b'b\'b
b'b\'b
/

println str2

/**
 * dollar slash string
 * $/ GStrings /$
 * 使用$和反斜杠 作为转义字符
 * $不能用来转义反斜杠
 *
 * $空格 和 正斜杠不需要转义
 * 反斜杠都需要使用反斜杠转义
 */
def str3= $/
/a/aa/
bbb
\a\a末尾反斜杠无效a\
bbb
$$a$$aa$$
bbb
$ a$ aa$
bbb
aa末尾反斜杠转义无效a\\
bbb
aa末尾dollar转义反斜杠无效a$\
bbb
aa尾dollar转义无效a$\\
bbb
/$

println str3