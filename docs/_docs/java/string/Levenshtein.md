<div style="text-align: center;font-size: 40px;">Levenshtein</div>

> 莱文斯坦距离，又称 Levenshtein 距离，是编辑距离的一种 \
> 指两个字串之间，由一个转成另一个所需的最少编辑操作次数 \
> 包括将一个字符替换成另一个字符，插入一个字符，删除一个字符 \
> 编辑距离表示字符串相似度, 编辑距离越小，字符串越相似

## 推导过程

|     |  a  |       |
|:---:|:---:|:-----:|
|  b  |  0  |   1   |
|     |  1  | **1** |

|     |  a  |  b  |       |
|:---:|:---:|:---:|:-----:|
|  b  |  0  |  1  |   2   |
|     |  1  |  1  | **1** |

|     |  a  |  b  |  c  |       |
|:---:|:---:|:---:|:---:|:-----:|
|  b  |  0  |  1  |  2  |   3   |
|     |  1  |  1  |  1  | **1** |

|     |  a  |  b  |  c  |       |
|:---:|:---:|:---:|:---:|:-----:|
|  b  |  0  |  1  |  2  |   3   |
|  a  |  1  |  1  |  1  |   2   |
|  b  |  2  |  1  |  2  |   2   |
|  c  |  3  |  2  |  1  |   2   |
|     |  4  |  3  |  2  | **1** |

## java code

```jshelllanguage
public double levenshtein(final String a, final String b)
{
    int[][] ret=new int[a.length()+1][b.length()+1];

    for (int i = 0; i <= a.length(); i++)
    {
        for (int j = 0; j <= b.length(); j++)
        {
            if(i==0)
            {
                ret[i][j]=j;
            }else if(j==0)
            {
                ret[i][j]=i;
            }else if(a.charAt(i-1)==b.charAt(j-1))
            {
                ret[i][j]=ret[i-1][j-1];
            }else
                ret[i][j]=1+Math.min(ret[i-1][j-1],Math.min(ret[i][j-1],ret[i-1][j]));
        }
    }
    return ret[a.length()][b.length()];
}
/**
 * 由 levenshtein 优化而成的写法更省空间
 */
public double levenshteinPro(final String a, final String b)
{
    int[] v0 =new int[b.length()+1];
    int[] v1 =new int[b.length()+1];
    int[] temp;

    for (int i = 0; i <= b.length(); i++)
        v0[i]=i;

    for (int i = 0; i < a.length(); i++)
    {
        v1[0]=i+1;
        for (int j = 0; j < b.length(); j++)
        {
            int cost=1;
            if(a.charAt(i)==b.charAt(j))
            {
                //相同不需要替换 不需+1
                cost=0;
            }
            v1[j+1]=cost + Math.min(v1[j],Math.min(v0[j],v0[j+1]));
        }
        System.err.println(Arrays.toString(v1));
        temp=v1;
        v1=v0;
        v0=temp;
    }
    return v0[b.length()];
}
```