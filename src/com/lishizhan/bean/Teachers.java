package com.lishizhan.bean;

/**
 * @author: 黎仕展
 * @data: 2020/4/30/0030
 */
/**
 * 教师类，在继承人员类的基础上再增加教师工号、课时数等域，并且设置这些域值的设置和获取方法，
 * 再添加教师基本信息（教师工号、姓名）输出方法p等。
 */
public class Teachers extends Person {
    private String teachId;//教职工号
    private String classHour = "0";//课时

    @Override
    public String p() {
        return teachId + "\t  " + super.getName()+"\t " + classHour;
    }


    @Override
    public String toString() {
        return super.getId()+"\t" + teachId + "\t  " + super.getName() +
                "\t "+super.getSex()+
                "\t "+super.getAge()+"\t "+super.getProfession();
    }

    public Teachers() {
    }

    public Teachers(String teachId, String classHour) {
        this.teachId = teachId;
        this.classHour = classHour;
    }

    public String getTeachId() {
        return teachId;
    }

    public void setTeachId(String teachId) {
        this.teachId = teachId;
    }

    public String getClassHour() {
        return classHour;
    }

    public void setClassHour(String classHour) {
        this.classHour = classHour;
    }

}
