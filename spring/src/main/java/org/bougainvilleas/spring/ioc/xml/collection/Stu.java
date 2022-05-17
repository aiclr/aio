package org.bougainvilleas.spring.ioc.xml.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 注入集合
 * @author renqiankun
 */
public class Stu {

    private String[] courses;
    private List<Course> courseList;
    private List<String> list;
    private Map<String,String> map;

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void show(){
        System.out.println(Arrays.toString(courses));
        System.out.println(list);
        System.out.println(courseList);
        System.out.println(map);
    }
}
