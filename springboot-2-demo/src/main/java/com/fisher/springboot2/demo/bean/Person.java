package com.fisher.springboot2.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component
@PropertySource(value = {"classpath:bean.properties"})
// 可以支持松散绑定。多属性批量绑定
@ConfigurationProperties(prefix = "person")
//导入spring的配置文件
//@ImportResource(value = {"classpath:bean.xml"})
@Validated
public class Person {

//    @Value() 在复杂数据封装的时候是不支持的，比如map是无法读取到
    // ConfigurationProperties 是支持复杂数据读取的

    private String name;
    private Integer age;

    private Map<String, Object> maps;

    private Object dogs;

    private List list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public Object getDogs() {
        return dogs;
    }

    public void setDogs(Object dogs) {
        this.dogs = dogs;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", maps=" + maps +
                ", dogs=" + dogs +
                ", list=" + list +
                '}';
    }
}
