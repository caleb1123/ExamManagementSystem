/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import core.ExamList;
import core.QuestionList;
import core.ResultList;
import core.RoomList;
import core.User.Admin;
import core.User.AdminList;
import core.User.StudentList;
import core.User.TeacherList;
import java.util.Scanner;

/**
 *
 * @author ryy15
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String pchoice, choice2, achoice;
        String pYN = "";
        boolean con = false;
        AdminList adminlist = new AdminList();
        StudentList studentlist = new StudentList();
        TeacherList teacherList = new TeacherList();
        RoomList roomList = new RoomList();
        ExamList examList = new ExamList(roomList);
        ResultList resultList = new ResultList(studentlist, examList);
        QuestionList questionList = new QuestionList();
        adminlist.readFromFile();
        studentlist.readFromFile();
        teacherList.readFromFile();
        examList.readFromFile();
        resultList.readFromFile();
        questionList.readFromFile();
        
        do {
            System.out.println("Exam Management System:");
            System.out.println("1.Admin\n"
                    + "2.Teacher\n"
                    + "3.Student\n"
                    + "4.Quit");
            System.out.println("Which management program you want to use:");
            pchoice = sc.next();
            switch (pchoice) {
                case "1":
                    // Xử lý cho admin
                    String adminID = checkValidation.checkValid.InputPattern("Enter your admin ID: ", "A[\\d]{5}");
                    if (adminlist.checkID(adminID)) {
                        // Login thành công
                        System.out.println("Login successful!");
                        while (true) {

                            System.out.println("1.Add admin/student/teacher\n"
                                    + "2.Remove student/teacher\n"
                                    + "3.View student/teacher/room\n"
                                    + "4.Add/Remove Question\n"
                                    + "5.Save file\n"
                                    + "6.Quit");

                            achoice = sc.next();
                            switch (achoice) {
                                case "1":
                                    System.out.println("1.Admin\n"
                                            + "2.Student\n"
                                            + "3.Teacher\n"
                                            + "4.Quit\n"
                                            + "Choice: ");
                                    String addchoice = sc.next();
                                    switch (addchoice) {
                                        case "1":
                                            adminlist.addAdmin();
                                            break;
                                        case "2":
                                            studentlist.addStudent();
                                            break;
                                        case "3":
                                            teacherList.addTeacher();
                                            break;
                                        case "4":
                                            System.exit(0);

                                    }
                                    break;
                                case "2":
                                    System.out.println("1.Admin\n"
                                            + "2.Student\n"
                                            + "3.Teacher\n"
                                            + "4.Quit"
                                            + "Choice: ");
                                    String rchoice = sc.next();
                                    switch (rchoice) {
                                        case "1":

                                            adminlist.removeAdmin();
                                            break;
                                        case "2":
                                            studentlist.removeStudent();
                                            break;
                                        case "3":
                                            teacherList.removeTeacher();
                                            break;
                                        case "4":
                                            System.exit(0);
                                        default:
                                            System.out.println("This is not a valid Menu Option! Please Select Another");
                                            break;

                                    }
                                    break;
                                case "3":
                                    System.out.println("1.Teacher\n"
                                            + "2.Student\n"
                                            + "3.Room\n"
                                            + "4.Quit\n"
                                            + "Choice: ");
                                    String vchoice = sc.next();
                                    switch (vchoice) {
                                        case "1":
                                            teacherList.viewTeacher();
                                            break;
                                        case "2":
                                            studentlist.viewStudent();
                                            break;
                                        case "3":
                                            roomList.viewRooms();
                                            break;
                                        case "4":
                                            System.exit(0);
                                        default:
                                            System.out.println("This is not a valid Menu Option! Please Select Another");
                                            break;

                                    }
                                    break;
                                case "4":
                                    System.out.println("1.Add question\n"
                                            + "2.Remove question");
                                    String quetionchocie = sc.next();
                                    switch (quetionchocie) {
                                        case "1":
                                            questionList.addQuestion();
                                            break;
                                        case "2":
                                            questionList.removeQuestion();
                                            break;
                                        default:
                                            System.out.println("This is not a valid Menu Option! Please Select Another");
                                            break;
                                    }
                                case "5":
                                    adminlist.writeToFile();
                                    studentlist.writeToFile();
                                    teacherList.writeToFile();
                                    examList.writeToFile();
                                    break;
                                case "6":
                                    System.exit(0);
                                default:
                                    System.out.println("This is not a valid Menu Option! Please Select Another");
                                    break;
                            }
                            System.out.print("Do you want to go back to the main menu (Y|N): ");
                            sc = new Scanner(System.in);
                            pYN = sc.nextLine();
                            System.out.print("\n");
                            if ("N".equals(pYN) || "n".equals(pYN)) {
                                break; // Thoát khỏi vòng lặp con
                            }
                        }
                    } else {
                        System.out.println("Admin ID not found. Please try again.");
                    }
                    break;
                case "2":

                    String teacherID = checkValidation.checkValid.InputPattern("Enter your teacher ID: ", "T[\\d]{5}");
                    if (teacherList.checkID(teacherID)) {
                        // Login thành công
                        System.out.println("Login successful!");
                        while (true) {
                            System.out.println("1.View Students\n"
                                    + "2.Add Student"
                                    + "3.View exam\n"
                                    + "4. View Room\n"
                                    + "5.View result\n"
                                    + "6.View Question\n"
                                    + "7.Quit");
                            String tChoice = sc.next();
                            switch (tChoice) {
                                case "1":
                                    studentlist.viewStudent();
                                    break;
                                case "2":
                                    studentlist.addStudent();
                                    break;
                                case "3":
                                    examList.viewExam();
                                    break;
                                case "4":
                                    break;
                                case "5":
                                    resultList.viewResult();
                                    break;
                                case "6":
                                    questionList.viewQuestion();
                                case "7":
                                    System.exit(0);

                            }

                        }
                    }
                    break;
                case "3":
                    // Xử lý cho sinh viên
                    String studentID = checkValidation.checkValid.InputPattern("Enter your student ID: ", "S[\\d]{5}");
                    if (studentlist.checkID(studentID)) {
                        // Đăng nhập thành công
                        System.out.println("Login successful!");
                        while (true) {
                            System.out.println("1.View exam\n"
                                    + "2.View Result\n"
                                    + "3.Quit");

                            String studentChoice = sc.next();
                            switch (studentChoice) {
                                case "1":
                                    examList.viewExam();
                                    break;
                                case "2":

                                    resultList.searchStudentResult(studentID);
                                    break;
                                case "3":
                                    System.exit(0);
                                default:
                                    System.out.println("This is not a valid Menu Option! Please Select Another");
                                    break;
                            }
                        }
                    }
                    break;

                case "4":
                    // Thoát khỏi chương trình
                    System.exit(0);
                default:
                    System.out.println("This is not a valid Menu Option! Please Select Another");
                    break;
            }
        } while ("Y".equals(pYN) || "y".equals(pYN));
    }
}
