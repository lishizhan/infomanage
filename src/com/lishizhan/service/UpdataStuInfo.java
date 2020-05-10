package com.lishizhan.service;

import com.lishizhan.bean.Students;
import com.lishizhan.view.PrimaryMenu;

import java.util.List;
import java.util.Scanner;

/**
 * @author: 黎仕展
 * @data: 2020/5/3/0003
 */
public class UpdataStuInfo {

    private static final Scanner SYS_SCANNER = new Scanner(System.in);

    public static void updataStudentInfo(List<Students> STUDENTS_ARRAY_LIST, Students students) {
        while (true) {
            System.out.println("\t\t\t   ----------修改学生字段信息----------");
            System.out.println("\t\t\t                1.学号");
            System.out.println("\t\t\t                2.姓名");
            System.out.println("\t\t\t                3.英语成绩");
            System.out.println("\t\t\t                4.数学成绩");
            System.out.println("\t\t\t                5.java成绩");
            System.out.println("\t\t\t                0.返回");
            System.out.println("\t\t\t   ----------------------------------");
            System.out.println("请输入需要修改字段的选择(0~5)：");
            String select = SYS_SCANNER.nextLine();
            switch (select) {
                case "1":
                    updataNum(STUDENTS_ARRAY_LIST, students);
                    if (PrimaryMenu.isExit()) {
                        return;
                    }
                    break;
                case "2":
                    updataName(students);
                    if (PrimaryMenu.isExit()) {
                        return;
                    }
                    break;
                case "3":
                    updataEnglish(students);
                    if (PrimaryMenu.isExit()) {
                        return;
                    }
                    break;
                case "4":
                    updataMath(students);
                    if (PrimaryMenu.isExit()) {
                        return;
                    }
                    break;
                case "5":
                    updataJava(students);
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
     * 修改java成绩
     *
     * @param students:修改的学生对象
     */
    private static void updataJava(Students students) {
        while (true) {
            System.out.println("请修改java成绩为:");
            String scort = SYS_SCANNER.nextLine();
            if (PrimaryMenu.isNumberDouble(scort)) {
                System.out.println("修改的成绩必须是百分制");
            } else if (PrimaryMenu.putIsEmpty(scort)) {
                System.err.println("成绩不能为空,请重新输入");
            } else {
                students.setJava(Double.parseDouble(scort));
                System.out.println("修改成功!!");
                return;
            }
        }
    }

    /**
     * 修改数学成绩
     *
     * @param students:修改的学生对象
     */
    private static void updataMath(Students students) {
        while (true) {
            System.out.println("请修改数学成绩为:");
            String scort = SYS_SCANNER.nextLine();
            if (PrimaryMenu.isNumberDouble(scort)) {
                System.out.println("修改的成绩必须是百分制");
            } else if (PrimaryMenu.putIsEmpty(scort)) {
                System.err.println("成绩不能为空,请重新输入");
            } else {
                students.setMath(Double.parseDouble(scort));
                System.out.println("修改成功!!");
                return;
            }
        }
    }

    /**
     * 修改英语成绩
     *
     * @param students:修改的学生对象
     */
    private static void updataEnglish(Students students) {
        while (true) {
            System.out.println("请修改英语成绩为:");
            String scort = SYS_SCANNER.nextLine();
            if (PrimaryMenu.isNumberDouble(scort)) {
                System.out.println("修改的成绩必须是百分制");
            } else if (PrimaryMenu.putIsEmpty(scort)) {
                System.err.println("成绩不能为空,请重新输入");
            } else {
                students.setEnglish(Double.parseDouble(scort));
                System.out.println("修改成功!!");
                return;
            }
        }
    }

    /**
     * 修改姓名
     *
     * @param students:修改的学生对象
     */

    private static void updataName(Students students) {
        PrimaryMenu.updataName(students);
    }

    /**
     * 修改学号
     *
     * @param students_array_list :学生集合
     * @param students:修改的学生对象
     */
    private static void updataNum(List<Students> students_array_list, Students students) {
        while (true) {
            System.out.println("请修改学号为:");
            String number = SYS_SCANNER.nextLine();
            if (PrimaryMenu.isNumRepetition(students_array_list, number)) {
                System.err.println("学号重复,请重新输入");
            } else if (PrimaryMenu.putIsEmpty(number) || PrimaryMenu.isNumber(number)) {
                System.err.println("数据不合法,请重新输入");
            } else {
                students.setStuNum(number);
                System.out.println("修改成功!!");
                return;
            }
        }
    }
}
