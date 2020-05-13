package com.lishizhan.service;

/**
 * @author: 黎仕展
 * @data: 2020/5/2/0002
 */

import com.lishizhan.bean.Students;
import com.lishizhan.view.PrimaryMenu;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 对学生信息进行排序
 *
 */
public class SortStuInfo {

    private static final Scanner SYS_SCANNER = new Scanner(System.in);

    public static void sortStudentInfo(List<Students> STUDENTS_ARRAY_LIST){
        if (STUDENTS_ARRAY_LIST.size()==0){
            System.out.println("学生信息管理系统空空如也!!!");
            PrimaryMenu.buttonEnter();
            return;

        }
        if(STUDENTS_ARRAY_LIST.size()==1){
            System.out.println("学生管理系统中只有一条记录:");
            Students students = STUDENTS_ARRAY_LIST.get(0);
            System.out.println("\t"+students);
            PrimaryMenu.buttonEnter();
            return;
        }

        while (true) {
            System.out.println("\t\t\t   -------------排序学生信息-------------");
            System.out.println("\t\t\t              1.按身份证号排序");
            System.out.println("\t\t\t              2.按学号排序");
            System.out.println("\t\t\t              3.按平均成绩排序");
            System.out.println("\t\t\t              0.返回");
            System.out.println("\t\t\t   -------------------------------------");
            System.out.println("请输入您的选择(0~6)：");
            String select = SYS_SCANNER.nextLine();
            switch (select) {
                case "1":
                    sortId(STUDENTS_ARRAY_LIST);
                    break;
                case "2":
                    sortStuNum(STUDENTS_ARRAY_LIST);
                    break;
                case "3":
                    sortAVGScore(STUDENTS_ARRAY_LIST);
                    break;
                case "0":
                    return;
                default:
                    System.err.println("您的命令输入有误，请重新确认！\n");
                    PrimaryMenu.await();
            }

        }
    }

    /**
     * 按平均成绩的对学生进行排序
     * @param STUDENTS_ARRAY_LIST :学生集合
     */
    private static void sortAVGScore(List<Students> STUDENTS_ARRAY_LIST) {
        Collections.sort(STUDENTS_ARRAY_LIST, new Comparator<Students>() {
            @Override
            public int compare(Students o1, Students o2) {
                //(int) Math.signum(o1.weight-o2.weight);
                return (int)Math.signum(o2.scoreAvg()-o1.scoreAvg());
            }
        });
        //打印排序后的学生信息
        System.out.println("***************************学生成绩表************************");
        System.out.println("学号\t\t姓名\t\t数学\t\t\t英语\t\t\tJava\t\t平均成绩");
        System.out.println("************************************************************");
        for (Students students1 : STUDENTS_ARRAY_LIST) {
            System.out.println(students1.printStuScort()+"\t\t`"+students1.scoreAvg());
        }

        PrimaryMenu.buttonEnter();


    }

    /**
     * 使用匿名内部类对学生学号进行排序
     * @param STUDENTS_ARRAY_LIST :学生集合
     */
    private static void sortStuNum(List<Students> STUDENTS_ARRAY_LIST) {
        //定义比较器比较规则
        Collections.sort(STUDENTS_ARRAY_LIST, new Comparator<Students>() {
            @Override
            public int compare(Students o1, Students o2) {
                return Integer.parseInt(o1.getStuNum())-Integer.parseInt(o2.getStuNum());
            }
        });
        sortOK(STUDENTS_ARRAY_LIST);
    }

    /**
     * 使用匿名内部类对学生身份证号进行排序
     * @param STUDENTS_ARRAY_LIST:学生集合
     */
    private static void sortId(List<Students> STUDENTS_ARRAY_LIST) {
        Collections.sort(STUDENTS_ARRAY_LIST, new Comparator<Students>() {
            @Override
            public int compare(Students o1, Students o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        sortOK(STUDENTS_ARRAY_LIST);

    }

    /**
     * 打印排好序的学生信息
     */
    private static void sortOK(List<Students> STUDENTS_ARRAY_LIST){
        PrimaryMenu.showTitle();
        //打印排序后的学生信息
        for (Students students1 : STUDENTS_ARRAY_LIST) {
            System.out.println(students1.printStuScort());
        }

        PrimaryMenu.buttonEnter();
    }
}
