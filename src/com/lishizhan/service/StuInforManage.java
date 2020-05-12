package com.lishizhan.service;

import com.lishizhan.JDBCUtil.JdbcUtil;
import com.lishizhan.bean.GenderEnum;
import com.lishizhan.bean.Students;
import com.lishizhan.view.PrimaryMenu;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 学生信息管理类。该类中要有学生人数域、学生列表域（学生类对象数组），并且对学生信息进行管理，
 * 例如学生信息录入、成绩录入、输出学生列表、输出成绩表等。更多功能自选。
 */
public class StuInforManage {
    /**
     * 定义一个集合用于存储学生信息数据
     */
    private static final List<Students> STUDENTS_ARRAY_LIST = new ArrayList<>();
    //定义全局输入
    private static final Scanner SYS_SCANNER = new Scanner(System.in);

    static {
        //从数据库中读取学生信息
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select *from students";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Students students = null;
            while (resultSet.next()) {
                students = new Students();
                students.setId(resultSet.getString("id"));
                students.setName(resultSet.getString("name"));
                students.setStuNum(resultSet.getString("stuNum"));
                students.setSex(GenderEnum.valueOf(resultSet.getString("sex")));
                students.setAge(resultSet.getInt("age"));
                students.setMath(resultSet.getDouble("math"));
                students.setEnglish(resultSet.getDouble("english"));
                students.setJava(resultSet.getDouble("Java"));
                STUDENTS_ARRAY_LIST.add(students);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, statement, resultSet);
        }


    }


    public static void stuMenu() {
        System.out.println("\t\t\t\t\t  *欢迎使用学生管理系统*\n");
        while (true) {
            System.out.println("\t\t   ***************学生信息管理***************");
            System.out.println("\t\t                 1.录入学生信息");
            System.out.println("\t\t                 2.打印学生信息");
            System.out.println("\t\t                 3.录入学生成绩");
            System.out.println("\t\t                 4.打印学生成绩表");
            System.out.println("\t\t                 5.排序学生信息");
            System.out.println("\t\t                 6.查找学生信息");
            System.out.println("\t\t                 7.删除学生信息");
            System.out.println("\t\t                 8.修改学生信息");
            System.out.println("\t\t                 0.退出学生系统");
            System.out.println("\t\t   *****************************************");
            System.out.println("请输入您的选择(0~5)：");
            String select = SYS_SCANNER.nextLine();
            switch (select) {
                case "1":
                    addStudentInfo();
                    break;
                case "2":
                    printStudentInfo();
                    break;
                case "3":
                    addStudentScore();
                    break;
                case "4":
                    printStudentScort();
                    break;
                case "5":
                    SortStuInfo.sortStudentInfo(STUDENTS_ARRAY_LIST);
                    break;
                case "6":
                    searchStudentInfo();
                    break;
                case "7":
                    deleteStudentInfo();
                    break;
                case "8":
                    updateStudentInfo();
                    break;
                case "0":

                    return;
                default:
                    System.err.println("您的命令输入有误，请重新确认！");
            }
        }
    }

    /**
     * 插入
     * 将学生信息写进数据库
     */
    private static int writeToDatabase(Students students) {
        if (students == null) return 0;
        Connection connection = null;
        PreparedStatement preStat = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "INSERT INTO students VALUES(?,?,?,?,?,?,?,?)";
            preStat = connection.prepareStatement(sql);
            preStat.setString(1, students.getId());
            preStat.setString(2, students.getName());
            preStat.setString(3, students.getStuNum());
            preStat.setString(4, students.getSex().toString());
            preStat.setInt(5, students.getAge());
            preStat.setDouble(6, students.getMath());
            preStat.setDouble(7, students.getEnglish());
            preStat.setDouble(8, students.getJava());

            return preStat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preStat);
        }
        return 0;

    }

    /**
     * 打印学生成绩表
     */
    private static void printStudentScort() {
        //判断集合是否为空
        if (isArrayEmpty()) return;
        PrimaryMenu.showTitle();
        for (Students students : STUDENTS_ARRAY_LIST) {
            System.out.println(students.printStuScort());
        }
        PrimaryMenu.buttonEnter();
    }


    /**
     * 修改学生信息
     */
    private static void updateStudentInfo() {
        //判断集合是否为空
        if (isArrayEmpty()) return;

        while (true) {
            System.out.println("请输入您要修改学生的学号:");
            String strNum = SYS_SCANNER.nextLine();
            if (PrimaryMenu.putIsEmpty(strNum) || PrimaryMenu.isNumber(strNum)) {
                System.err.println("数据不合法,请重新输入");
            } else {
                Students students1 = null;
                for (int i = 0; i < STUDENTS_ARRAY_LIST.size(); i++) {
                    if (STUDENTS_ARRAY_LIST.get(i).getStuNum().equals(strNum)) {
                        students1 = STUDENTS_ARRAY_LIST.get(i);
                        System.out.println("待修改的学生信息为:");
                        System.out.println("\t" + students1.p());

                        UpdataStuInfo.updataStudentInfo(STUDENTS_ARRAY_LIST, students1);
                        return;
                    }
                }
                if (students1 == null) {
                    System.err.println("没有找到该学生信息,请重新输入!");
                }
            }
        }
    }


    /**
     * 删除学生信息
     */
    private static void deleteStudentInfo() {
        //判断集合是否为空
        if (isArrayEmpty()) return;

        while (true) {
            System.out.println("请输入您要删除学生的学号:");
            String strNum = SYS_SCANNER.nextLine();
            if (PrimaryMenu.putIsEmpty(strNum) || PrimaryMenu.isNumber(strNum)) {
                System.err.println("数据不合法,请重新输入");
            } else {
                Students students1 = null;
                for (int i = 0; i < STUDENTS_ARRAY_LIST.size(); i++) {
                    if (STUDENTS_ARRAY_LIST.get(i).getStuNum().equals(strNum)) {
                        students1 = STUDENTS_ARRAY_LIST.get(i);
                        System.out.println("待删除的学生信息为:");
                        System.out.println("\t" + students1.p());

                        System.out.println("是否将学号为:" + students1.getStuNum() + "的学生信息删除,请确认(y/n)!");
                        String YN = SYS_SCANNER.nextLine();
                        if ("y".equals(YN)) {
                            STUDENTS_ARRAY_LIST.remove(i);
                            deleteDataStuInfo(students1);
                            System.out.println("删除成功!!");

                        } else {
                            System.out.println("删除失败!!");
                        }

                        if (PrimaryMenu.isExit()) {
                            return;
                        }
                    }
                }
                if (students1 == null) {
                    System.err.println("没有找到该学生信息,请重新输入!");
                }
            }

        }

    }

    /**
     * 从数据库中删除学生信息
     * @param students1
     */
    private static void deleteDataStuInfo(Students students1) {
        try {
            Connection connection = JdbcUtil.getConnection();
            String sql = "DELETE FROM students WHERE stuNum = ?";
            PreparedStatement preState= connection.prepareStatement(sql);
            preState.setString(1,students1.getStuNum());
            int i = preState.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }




    }

    /**
     * 按学号搜索学生信息
     */
    private static void searchStudentInfo() {
        //判断集合是否为空
        if (isArrayEmpty()) return;

        while (true) {
            System.out.println("请输入您要查找的学生学号:");
            String stuNum = SYS_SCANNER.nextLine();
            if (PrimaryMenu.putIsEmpty(stuNum) || PrimaryMenu.isNumber(stuNum)) {
                System.err.println("数据不合法!!请重新输入:");
            } else {
                Students students1 = null;
                for (int i = 0; i < STUDENTS_ARRAY_LIST.size(); i++) {
                    if (STUDENTS_ARRAY_LIST.get(i).getStuNum().equals(stuNum)) {
                        students1 = STUDENTS_ARRAY_LIST.get(i);
                        System.out.println("该学生信息如下:");
                        System.out.println("\t" + students1.p());
                        PrimaryMenu.buttonEnter();
                        return;
                    }
                }
                if (students1 == null) {
                    System.err.println("没有找到该学生信息,请重新输入!");
                }
            }
        }
    }


    /**
     * 录入学生成绩：数学、英语、Java
     */
    private static void addStudentScore() {
        //判断集合是否为空
        if (isArrayEmpty()) return;
        int sum = 0;
        while (true) {
            System.out.println("请输入您要录入成绩的学生学号:");
            String stuNum = SYS_SCANNER.nextLine();
            if (PrimaryMenu.putIsEmpty(stuNum) || PrimaryMenu.isNumber(stuNum)) {
                System.err.println("数据不合法!!请重新输入:");
            } else {
                Students students1 = null;
                for (int i = 0; i < STUDENTS_ARRAY_LIST.size(); i++) {
                    if (STUDENTS_ARRAY_LIST.get(i).getStuNum().equals(stuNum)) {
                        students1 = STUDENTS_ARRAY_LIST.get(i);

                        while (true) {
                            System.out.println("请输入英语成绩:");
                            String englishScore = SYS_SCANNER.nextLine();
                            if (PrimaryMenu.putIsEmpty(englishScore)) {
                                System.err.println("成绩不能为空!");
                            } else if (PrimaryMenu.isNumberDouble(englishScore)) {
                                System.err.println("成绩必须是百分制数字!请重新输入");
                            } else {
                                students1.setEnglish(Double.parseDouble(keepTwoDecimals(englishScore)));
                                break;
                            }
                        }

                        while (true) {
                            System.out.println("请输入数学成绩:");
                            String mathScore = SYS_SCANNER.nextLine();
                            if (PrimaryMenu.putIsEmpty(mathScore)) {
                                System.err.println("成绩不能为空!");
                            } else if (PrimaryMenu.isNumberDouble(mathScore)) {
                                System.err.println("成绩必须是百分制数字!请重新输入");
                            } else {
                                students1.setMath(Double.parseDouble(keepTwoDecimals(mathScore)));
                                break;
                            }
                        }

                        while (true) {
                            System.out.println("请输入Java成绩:");
                            String javaScore = SYS_SCANNER.nextLine();
                            if (PrimaryMenu.putIsEmpty(javaScore)) {
                                System.err.println("成绩不能为空!");
                            } else if (PrimaryMenu.isNumberDouble(javaScore)) {
                                System.err.println("成绩必须是百分制数字!请重新输入");
                            } else {
                                students1.setJava(Double.parseDouble(keepTwoDecimals(javaScore)));
                                break;
                            }
                        }

                        //将学生成绩写进数据库
                        sum +=updateDataScore(students1);

                        if (PrimaryMenu.isExit()) {
                            System.out.println("录入成绩成功,更新"+sum+"条数据!");
                            return;
                        } else {
                            break;
                        }
                    }
                }
                if (students1 == null) {
                    System.err.println("没有找到该学生信息,请重新输入!");
                }
            }
        }

    }

    /**
     * 录入成绩写进数据库
     *
     * @param students1 ;
     * @return :录入信息的条数
     */
    private static int updateDataScore(Students students1) {
        Connection connection = null;
        PreparedStatement preState = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "UPDATE students SET math=?,english=?,Java=? WHERE stuNum = ?";
            preState = connection.prepareStatement(sql);
            preState.setDouble(1, students1.getMath());
            preState.setDouble(2, students1.getEnglish());
            preState.setDouble(3, students1.getJava());
            preState.setString(4, students1.getStuNum());

            return preState.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preState);
        }
        return 0;
    }

    /**
     * 保留两位小数
     *
     * @param score :成绩
     * @return :保留两位小数的字符串
     */

    private static String keepTwoDecimals(String score) {

        return String.format("%.2f", Double.parseDouble(score));
    }


    /**
     * 打印学生信息
     */
    private static void printStudentInfo() {
        //判断集合是否为空
        if (isArrayEmpty()) return;
        System.out.println();
        System.out.println("*****************学生信息表****************");
        System.out.println("身份证号\t\t\t\t学号\t  姓名\t性别\t年龄\t ");
        System.out.println("******************************************");
        for (Students students1 : STUDENTS_ARRAY_LIST) {
            System.out.println(students1.p());
        }
        PrimaryMenu.buttonEnter();

    }

    /**
     * 录入学生信息
     */
    private static void addStudentInfo() {
        Students students = null;
        boolean flga = true;
        //记录学生信息条数
        int sum = 0;
        while (flga) {
            while (true) {
                System.out.println("请输入学生身份证号：");
                String string = SYS_SCANNER.nextLine();
                //进行数据校验
                if (PrimaryMenu.isIDRepetition(STUDENTS_ARRAY_LIST, string)) {
                    System.err.println("您要录入的身份证号已经存在,请重新输入!");
                } else if (PrimaryMenu.cheackID(string)) {
                    System.err.println("身份证号非法! 请重新输入");
                } else {
                    students = new Students();
                    students.setId(string);
                    break;
                }
            }
            while (true) {
                System.out.println("请输入学生姓名：");
                String name = SYS_SCANNER.nextLine();
                if (PrimaryMenu.putIsEmpty(name)) {
                    System.err.println("学生姓名不能为空！请重新输入\n");

                } else {
                    students.setName(name);
                    break;
                }
            }

            while (true) {
                System.out.println("请输入学生学号：");
                String stuId = SYS_SCANNER.nextLine();
                if (PrimaryMenu.isNumRepetition(STUDENTS_ARRAY_LIST, stuId)) {
                    System.err.println("您要录入的学生学号已经存在,请重新输入!");
                } else if (PrimaryMenu.putIsEmpty(stuId)) {
                    System.err.println("学号不能为空！请重新输入\n");

                } else {
                    students.setStuNum(stuId);
                    break;
                }
            }

            //输入性别
            PrimaryMenu.inputSex(students);

            //输入年龄
            PrimaryMenu.inputAge(students);


            STUDENTS_ARRAY_LIST.add(students);

            //将录入的学生信息写进数据库
            sum += writeToDatabase(students);
            if (PrimaryMenu.isExit()) {
                System.out.println("成功录入" + sum + "条学生主要信息！！");
                flga = false;
            }
        }
    }

    /**
     * 判断学生信息集合是否为空
     *
     * @return :集合为空则返回true,反之false.
     */
    private static boolean isArrayEmpty() {
        if (STUDENTS_ARRAY_LIST.size() == 0) {
            System.out.println("学生信息管理系统空空如也!!!");
            PrimaryMenu.buttonEnter();
            return true;
        }
        return false;
    }
}
