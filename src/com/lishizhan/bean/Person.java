package com.lishizhan.bean;

/**
 * @author: 黎仕展
 * @data: 2020/4/30/0030
 */

/**
    人员类，它至少有身份证号、姓名、性别、年龄、专业等域，以及各个域值的设置和获取方法，
 还可以有基本信息（身份证号、姓名）的输出方法p。
 */
public class Person {
    private String id;      //身份证号
    private String name;    //姓名
    private GenderEnum sex; //性别
    private int age;        //年龄
    private String profession;//专业

    /**
     * 打印人员信息
     * @return ："身份证号:"+id+"\t姓名"+name;
     */
    public String p(){
        return "身份证号:"+id+"\t姓名"+name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderEnum getSex() {
        return sex;
    }

    public void setSex(GenderEnum sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
