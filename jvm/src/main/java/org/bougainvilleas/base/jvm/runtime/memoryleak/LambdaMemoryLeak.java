package org.bougainvilleas.base.jvm.runtime.memoryleak;

import org.bougainvilleas.base.jvm.runtime.memoryleak.lambda.AgeEnum;
import org.bougainvilleas.base.jvm.runtime.memoryleak.lambda.POJO;
import org.bougainvilleas.base.jvm.runtime.memoryleak.lambda.VOJO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * java version "1.8.0_191"
 * Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
 * Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
 *
 * @author renqiankun
 * @date 2021-07-19 15:13:00 星期一
 */
public class LambdaMemoryLeak
{
    public static void main(String[] args) throws InterruptedException
    {
        LambdaMemoryLeak lambdaMemoryLeak=new LambdaMemoryLeak();
        lambdaMemoryLeak.test1();
        System.err.println("------");
        Thread.sleep(10*60*1000);
    }

    public void test(){
        List<POJO> pojos=getData();
        List<VOJO> vojos=new ArrayList<>();
        //此写法会有一个 LambdaMemoryLeak$$Lambda$1/303563356 对象不会被GC
        pojos.stream()
            .filter(p-> AgeEnum.contain(p.getAge()))
            .forEach(p->vojos.add(createVO(p)));
        System.err.println(vojos.size());
    }
    public void test1(){
        List<POJO> pojos=getData();
        List<VOJO> vojos=new ArrayList<>();
        Predicate<Integer> contain=(a)->{
            for (AgeEnum type:AgeEnum.values())
            {
                if(type.getAge()==a){
                    return true;
                }
            }
            return false;
        };
        //此写法会有一个 LambdaMemoryLeak$$Lambda$1/303563356 对象不会被GC
        pojos.stream()
                .filter(p-> contain.test(p.getAge()))
                .forEach(p->vojos.add(createVO(p)));
        System.err.println(vojos.size());
    }

    //正常GC
    public void test2(){
        List<POJO> pojos=getData();
        List<VOJO> vojos=new ArrayList<>();
        pojos.forEach(p->{
                    if(AgeEnum.contain(p.getAge())){
                        vojos.add(createVO(p));
                    }
                });
        System.err.println(vojos.size());
    }

    private static List<POJO> getData(){
        List<POJO> pojos=new ArrayList<>();
        pojos.add(POJO.builder().age(10).name("jack").build());
        pojos.add(POJO.builder().age(11).name("caddy").build());
        pojos.add(POJO.builder().age(12).name("tom").build());
        return pojos;
    }

    private static VOJO createVO(POJO pojo){
        return VOJO.builder()
                .name(pojo.getName())
                .type(AgeEnum.getByAge(pojo.getAge()))
                .build();
    }


}