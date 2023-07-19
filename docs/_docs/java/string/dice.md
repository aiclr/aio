<div style="text-align: center;font-size: 40px;">Sorensen Dice</div>

> Sorensen Dice 相似度系数
> 
> **集合交集的 2 倍除以两个集合相加（并不是并集）**
>
> **顺序无关**

## java code

```jshelllanguage
/**
 * Sorensen Dice 相似度系数
 * 计算简单集合之间相似度
 * 集合交集的 2 倍除以两个集合相加（并不是并集）
 *
 *             2| A ∩ B |
 * f(A,B)=   ---------------
 *              |A| + |B|
 *
 * @param a 样本a
 * @param b 样本b
 * @return 相似度
 */
float dice(String a,String b)
{
    // 都为空相似度为 1
    if (a == null && b == null)
        return 1f;
    if (a == null || b == null)
        return 0f;
    Set<Integer> aChar = a.chars().boxed().collect(Collectors.toSet());
    Set<Integer> bChar = b.chars().boxed().collect(Collectors.toSet());
    int all=aChar.size()+ bChar.size();
    // 交集
    aChar.retainAll(bChar);
    if(aChar.size()==0)
        return 0;
    return ( 2 * (float) aChar.size() ) / ( float ) all;
}
```


## jshell test

```shell
jshell> /open d://jshell/dice.txt

jshell> dice("abcde","abcde")
$2 ==> 1.0

jshell> dice("abcde","abcdf")
$3 ==> 0.8

jshell> dice("abcde","abfde")
$4 ==> 0.8

jshell> dice("abcde","abcif")
$5 ==> 0.6

jshell> dice("abcde","abjif")
$6 ==> 0.4

jshell> dice("abcde","akjif")
$7 ==> 0.2

jshell> dice("abcde","lkjif")
$8 ==> 0.0
```