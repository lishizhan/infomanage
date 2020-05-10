package com.lishizhan.service;

import com.lishizhan.bean.Teachers;
import com.lishizhan.view.PrimaryMenu;

import java.util.List;
import java.util.Scanner;

/**
 * @author: 黎仕展
 * @data: 2020/5/5/0005
 */
public class UpdataTeaInfo {

    private static final Scanner SYS_SCANNER = new Scanner(System.in);
    //441521199108081132,李四,444,444,女,24,工商管理
    public static void updataStudentInfo(List<Teachers> TEACHERS_ARRAY_LIST, Teachers teachers) {
        while (true) {
            System.out.println("\t\t\t   ----------修改教师字段信息----------");
            System.out.println("\t\t\t                1.工号");
            System.out.println("\t\t\t                2.姓名");
            System.out.println("\t\t\t                3.年龄");
            System.out.println("\t\t\t                4.教学专业");
            System.out.println("\t\t\t                0.返回");
            System.out.println("\t\t\t   ----------------------------------");
            System.out.println("请输入需要修改字段的选择(0~5)：");
            String select = SYS_SCANNER.nextLine();
            switch (select) {
                case "1":
                    updataNum(TEACHERS_ARRAY_LIST, teachers);
                    if (PrimaryMenu.isExit()) {
                        return;
                    }
                    break;
                case "2":
                    updataName(teachers);
                    if (PrimaryMenu.isExit()) {
                        return;
                    }
                    break;
                case "3":
                    updataAge(teachers);
                    if (PrimaryMenu.isExit()) {
                        return;
                    }
                    break;
                case "4":
                    updataprofession(teachers);
                    if (PrimaryMenu.isExit()) {
                        return;
                    }
                    break;
                case "0":
                    return;
                default:
                    System.err.println("您的命令输入有误，请重新确认！\n");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
        }
    }

    /**
     * 修改专业
     * @param teachers :教师
     */
    private static void updataprofession(Teachers teachers) {
        while (true) {
            System.out.println("请修改教学专业为:");
            String profession = SYS_SCANNER.nextLine();
            if (PrimaryMenu.putIsEmpty(profession)) {
                System.err.println("不能为空,请重新输入");
            } else {
                teachers.setProfession(profession);
                System.out.println("修改成功!!");
                break;
            }
        }
    }


    /**
     * 修改教师年龄
     * @param teachers:教师
     */
    private static void updataAge(Teachers teachers) {
        while (true) {
            System.out.println("请修改年龄为:");
            String age = SYS_SCANNER.nextLine();
            if (PrimaryMenu.isAge(age)) {
                teachers.setAge(Integer.parseInt(age));
                System.out.println("修改成功!!");
                break;

            } else {
                System.err.println("年龄不合法,请重新输入");
            }
        }
    }

    /**
     * 修改教师姓名
     * @param teachers :教师
     */
    private static void updataName(Teachers teachers) {
       PrimaryMenu.updataName(teachers);
    }


    /**
     * 修改教师工号
     * @param teachers_array_list :教师集合
     * @param teachers :教师对象
     */
    private static void updataNum(List<Teachers> teachers_array_list, Teachers teachers) {
        while (true) {
            System.out.println("请修改教师工号为:");
            String number = SYS_SCANNER.nextLine();
            if (PrimaryMenu.isNumRepetition(teachers_array_list, number)) {
                System.err.println("工号重复,请重新输入");
            } else if (PrimaryMenu.putIsEmpty(number) || PrimaryMenu.isNumber(number)) {
                System.err.println("数据不合法,请重新输入");
            } else {
                teachers.setTeachId(number);
                System.out.println("修改成功!!");
                return;
            }
        }
    }
}
