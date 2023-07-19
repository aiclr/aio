<div style="text-align: center;font-size: 40px;">Jaccard index</div>

> Jaccard index 杰卡德系数，Jaccard 相似系数（Jaccard similarity coefficient）
> [参考文献](https://nph.onlinelibrary.wiley.com/doi/epdf/10.1111/j.1469-8137.1912.tb05611.x)
> 
> **样本集合的交集 与集合并集的比例**
> 
> **顺序无关**

## java code

```jshelllanguage
/**
 * Jaccard similarity coefficient Jaccard相似系数
 * 比较有限样本集之间的相似性与差异性。
 * Jaccard系数值越大，样本相似度越高
 *
 * J(A,B)=|A∩B| ÷ |A∪B| = |A∩B| ÷ (|A| + |B| - |A∩B|)
 *
 *             |A∩B|                    |A∩B|
 * J(A,B)=   ------------   =   ----------------------
 *             |A∪B|              |A| + |B| - |A∩B|
 *
 * Jshell 默认导入了 import java.util.*
 * @param a 样本a
 * @param b 样本b
 * @return 相似度
 */
float jaccard(String a,String b)
{
    // 都为空相似度为 1
    if(a==null&&b==null)
        return 1f;
    if(a==null||b==null)
        return 0f;
    
    Set<Integer> aChar=a.chars().boxed().collect(Collectors.toSet());
    Set<Integer> bChar=b.chars().boxed().collect(Collectors.toSet());
    // 交集
    Set<Integer> ns=new HashSet<>(aChar);
    ns.retainAll(bChar);
    if(ns.size()==0)
        return 0;
    //并集
    Set<Integer> us=new HashSet<>(aChar);
    us.addAll(bChar);
    return((float)ns.size())/(float)us.size();
}
```

## jshell test

```shell
jshell> /open jshell/jaccard.txt

jshell> jaccard("abcde","abcde")
$3 ==> 1.0

jshell> jaccard("abcde","abcdf")
$4 ==> 0.6666667

jshell> jaccard("abcde","abfde")
$9 ==> 0.6666667

jshell> jaccard("abcde","abcgf")
$5 ==> 0.42857143

jshell> jaccard("abcde","abhgf")
$6 ==> 0.25

jshell> jaccard("abcde","aihgf")
$7 ==> 0.11111111

jshell> jaccard("abcde","jihgf")
$8 ==> 0.0
```