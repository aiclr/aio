package org.bougainvilleas.spring.aop.annotation.mya;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class TestMyAnnotation {

    @Test
    @DisplayName("自定义注解")
    void test(){
        User user=new User("caddy","yddac");
        User user1=new User("root","toor");
        System.out.println(getQuery(user));
        System.out.println(getQuery(user1));
    }

    /**
     * 通过反射
     * @param user
     * @return
     */
    private static String getQuery(User user){
        StringBuilder sb=new StringBuilder() ;
        sb.append("select id from ");
        Class<? extends User> u=user.getClass();
        {
            boolean isExist = u.isAnnotationPresent(MyTable.class);
            String tableName="";
            if(isExist){
                MyTable myTable = u.getAnnotation(MyTable.class);
                tableName=myTable.value();
                sb.append(tableName).append(" where 1=1");
            }else {
                return null;
            }
        }
        Field[] fields = u.getDeclaredFields();
        for (Field field:fields){
            if(field.isAnnotationPresent(MyCloume.class)){
                MyCloume annotation = field.getAnnotation(MyCloume.class);
                String columnValue=annotation.value();
                String fieldName=field.getName();
                String getter="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                String fieldValue=null;
                try{
                    Method get=u.getMethod(getter);
                    fieldValue =(String)get.invoke(user);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(fieldValue!=null){
                    sb.append(" and ").append(columnValue).append("='").append(fieldValue).append("'");
                }
            }else continue;
        }
        return sb.toString();
    }


}
