/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.User;

import core.User.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author ryy15
 */
public class StudentList {

    Student[] students;
    int size;
    String fName = "src\\data\\Students.dat";

    public StudentList() {
        this.students = new Student[100];
        this.size = 0;
    }

    public boolean checkID(String ID) {
    for (int i = 0; i < students.length; i++) {
        if (students[i] != null && students[i].getStudentID().equals(ID)) {
            return true;
        }
    }
    return false;
}


    public Student getStudentByID(String studentID) {
    for (int i = 0; i < size; i++) {
        if (students[i] != null && students[i].getStudentID().equals(studentID)) {
            return students[i];
        }
    }
    return null; // Student not found
}

   public void readFromFile() {
    File file = new File(fName);
    if (!file.exists()) {
        System.out.println("File does not exist!");
        System.exit(0);
    } else {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String studentID = stk.nextToken().trim();
                    String name = stk.nextToken().trim();
                    String email = stk.nextToken().trim();
                    String phone = stk.nextToken().trim();
                    String className = stk.nextToken().trim();
                    String department = stk.nextToken().trim();
                    

                    Student student = new Student(studentID, name, email, phone, className, department);
                add(student);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

  
    public void writeToFile() {
        if (students.length == 0) {
            System.out.println("Empty list!");
        } else {
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(fName);
                for (int i = 0; i < size; i++) {
                    pw.println(students[i].getStudentID() + ", " + students[i].getName() + ", " + students[i].getEmail() + ", " + students[i].getPhone() + ", " + students[i].getClassName() + ", " + students[i].getDepartment());
                }
                pw.close();
                System.out.println("Writing file: DONE.");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
        }
    }
public void addStudent() {
    String studentID;
    String name;
    String email;
    String phone;
    String className;
    String department;
    double score;
    int pos;
    do {
        studentID = checkValidation.checkValid.InputPattern("Student ID: ", "S[\\d]{5}");
        pos = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.students[i].getStudentID().equals(studentID)) {
                pos = i;
                break;
            }
        }
        if (pos >= 0) {
            System.out.println("ID is duplicated!");
        }
    } while (pos >= 0);

    name = checkValidation.checkValid.checkLength("Name student: ", 0, 30);
    email = checkValidation.checkValid.checkLength("Email student: ", 0, 30);
    phone = checkValidation.checkValid.checkLength("Phone student: ",10,12);
    className = checkValidation.checkValid.checkLength("Class name: ", 0, 20);
    department = checkValidation.checkValid.checkLength("Department name: ", 0, 20);
    

    Student newStudent = new Student(studentID, name, email, phone, className, department);
    this.add(newStudent);
}

private void add(Student student) {
    if (this.size == this.students.length) {
        this.students = Arrays.copyOf(this.students, this.students.length * 2);
    }
    this.students[this.size++] = student;
}

public void updateStudent() {
   

    // Ask for student ID
    System.out.print("Enter the student's ID: ");
    String studentId = checkValidation.checkValid.InputPattern("Student ID: ", "S[\\d]{5}");

    // Search for student with the given ID
    int index = -1;
    for (int i = 0; i < size; i++) {
        if (students[i].getStudentID().equals(studentId)) {
            index = i;
            break;
        }
    }

    // If student is found, update student information
    if (index >= 0) {
        System.out.println("Enter new student information (leave blank if you don't want to update):");
        String name = checkValidation.checkValid.checkLength("Name: ", 0, 20);
        if (!name.isEmpty()) {
            students[index].setName(name);
        }

        
        String email = checkValidation.checkValid.checkLength("Email: ", 0, 30);
        if (!email.isEmpty()) {
            students[index].setEmail(email);
        }

        
        String phone = checkValidation.checkValid.checkLength("Phone: ", 10, 12);
        if (!phone.isEmpty()) {
            students[index].setPhone(phone);
        }

        
        String className = checkValidation.checkValid.checkLength("Class name: ", 0, 20);
        if (!className.isEmpty()) {
            students[index].setClassName(className);
        }

        
        String department = checkValidation.checkValid.checkLength("Department name: ", 0,20);
        if (!department.isEmpty()) {
            students[index].setDepartment(department);
        }

        System.out.println("Student information has been updated.");
    } else {
        System.out.println("No student with ID " + studentId + " found.");
    }

    // Write changes to file
    writeToFile();
}
public void viewStudent() {
    System.out.println("List of students:");
    System.out.format("+--------+------------------------+-------------------------------+---------------+---------------+----------------+\n");
    System.out.format("|   ID   |          Name          |             Email             |     Phone     |  Class Name   | Department Name|\n");
    System.out.format("+--------+------------------------+-------------------------------+---------------+---------------+----------------+\n");

    for (int i = 0; i < size; i++) {
        System.out.format("| %-6s | %-22s | %-29s | %-13s | %-13s | %-14s |\n",
            students[i].getStudentID(), students[i].getName(), students[i].getEmail(),
            students[i].getPhone(), students[i].getClassName(), students[i].getDepartment());
        System.out.format("+--------+------------------------+-------------------------------+---------------+---------------+----------------+\n");
    }
}
public void removeStudent() {
    String studentID = checkValidation.checkValid.InputPattern("Student ID: ", "S[\\d]{5}");
    int index = -1;
    for (int i = 0; i < size; i++) {
        if (students[i].getStudentID().equals(studentID)) {
            index = i;
            break;
        }
    }

    if (index != -1) {
        for (int i = index; i < size - 1; i++) {
            students[i] = students[i + 1];
        }
        size--;
        System.out.println("Student with ID " + studentID + " has been removed.");
    } else {
        System.out.println("Student with ID " + studentID + " does not exist in the system!");
    }
}

}
