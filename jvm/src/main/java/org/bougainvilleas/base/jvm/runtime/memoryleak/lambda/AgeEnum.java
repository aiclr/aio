package org.bougainvilleas.base.jvm.runtime.memoryleak.lambda;

/**
 * @author renqiankun
 * @date 2021-07-19 15:20:07 星期一
 */
public enum AgeEnum
{
    YOUNG(10),OLD(12);

    AgeEnum(int age)
    {
        this.age = age;
    }

    int age;

    public int getAge()
    {
        return age;
    }

    public static boolean contain(int a){
        for (AgeEnum type:AgeEnum.values())
        {
            if(type.age==a){
                return true;
            }
        }
        return false;
    }

    public static AgeEnum getByAge(int a){
        for (AgeEnum type:AgeEnum.values())
        {
            if(type.age==a){
                return type;
            }
        }
        return null;
    }

}
