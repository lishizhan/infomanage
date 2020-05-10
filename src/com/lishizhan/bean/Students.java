package com.lishizhan.bean;

/**
 * @author: 黎仕展
 * @data: 2020/4/30/0030
 */

/**
 * 3）	学生类，在继承人员类的基础上再增加学号、三门课成绩（数学、英语、Java）等域，
 * 并且设置这些域值的设置和获取方法，再添加学生信息基本信息（学号、姓名）输出f方法p、计算平均分等方法。
 */

public class Students extends Person {
    private String stuNum;  //学号
    private double math;    //数学成绩
    private double english; //英语成绩
    private double Java;    //java成绩

    /**
     * 输出学生的基本信息
     *
     * @return ："学号： "+stuNum+"\t姓名："+super.getId();
     */
    @Override
    public String p() {
        return super.getId()+ "\t" + stuNum + "\t  "+super.getName()+"\t"+super.getSex()+"\t"+getAge();
    }

    /**
     * 打印成绩信息
     * @return :
     */
    public String printStuScort(){
        return stuNum + "\t\t" + super.getName() + "\t\t`" + math + "\t\t`" + english + "\t\t`" + Java;
    }

    /**
     * 计算平均分
     */
    public double scoreAvg() {
        double result = (english + math + Java) / 3;
        String format = String.format("%.2f", result);
        return Double.parseDouble(format);

    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    public double getJava() {
        return Java;
    }

    public void setJava(double java) {
        Java = java;
    }
}
