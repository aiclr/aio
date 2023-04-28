package org.bougainvilleas.base.dsaa.algorithm;

import java.util.*;

/**
 * 贪心算法
 *
 * 在对问题求解时，每一步的选择中都采取最好或者最优的选择，
 * 从而希望能够导致结果是最好或是最优的算法
 *
 * 结果有时候是最优解
 *
 * 解决集合覆盖问题
 *
 * eg 求能覆盖所有城市的电台组合，要求是电台最少
 * 电台k1{北京，上海，天津}
 * 电台k2{广州，上海，深圳}
 * 电台k3{成都，上海，杭州}
 * 电台k4{上海，天津}
 * 电台k5{杭州，大连}
 *
 * 结果[k1, k2, k3, k5]
 *
 *
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {

        //广播电台，覆盖的城市
        Map<String, Set<String>> broadCasts = new HashMap<>();

        Set<String> country1 = new HashSet<>();
        country1.add("北京");
        country1.add("上海");
        country1.add("天津");
        Set<String> country2 = new HashSet<>();
        country2.add("广州");
        country2.add("上海");
        country2.add("深圳");
        Set<String> country3 = new HashSet<>();
        country3.add("成都");
        country3.add("上海");
        country3.add("杭州");
        Set<String> country4 = new HashSet<>();
        country4.add("上海");
        country4.add("天津");
        Set<String> country5 = new HashSet<>();
        country5.add("杭州");
        country5.add("大连");
        broadCasts.put("k1",country1);
        broadCasts.put("k2",country2);
        broadCasts.put("k3",country3);
        broadCasts.put("k4",country4);
        broadCasts.put("k5",country5);

        //所有要覆盖的地区
        HashSet<String> allCountry = new HashSet<>();
        for(String s:broadCasts.keySet()){
            allCountry.addAll(broadCasts.get(s));
        }
        System.err.println(allCountry);

        //选择的电台集合
        List<String> selects=new ArrayList<>();

        //临时集合，电台覆盖地区和所有地区的交集
        Set<String> tempSet=new HashSet<>();

        //maxKey 一次遍历中，能覆盖最大未覆盖地区对于的电台的key
        //如果maxKey不为null,则加入到selects
        String maxKey;

        while (allCountry.size()>0){
            maxKey=null;
            for (String key:broadCasts.keySet()){
                //清空
                tempSet.clear();
                Set<String> set = broadCasts.get(key);
                tempSet.addAll(set);
                //求tempSet和allCountry交集，并赋给tempSet
                tempSet.retainAll(allCountry);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //重新指定maxKey
                if(
                    tempSet.size()>0
                    &&
                    (maxKey==null||tempSet.size()>broadCasts.get(maxKey).size())){
                    //当未覆盖地区数量更大时，将maxKey指向较大的
                    maxKey=key;
                }
            }
            if(maxKey!=null){
                selects.add(maxKey);
                allCountry.removeAll(broadCasts.get(maxKey));

            }
        }
        System.err.println(selects);
    }
}
