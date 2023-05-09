/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.User;

/**
 *
 * @author ryy15
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TeacherList {

    Teacher[] teachers;
    int size;
    String fName = "src\\data\\teachers.dat";

    public TeacherList() {
        this.teachers = new Teacher[100];
        this.size = 0;
    }

   public boolean checkID(String teacherID) {
    for (int i = 0; i < size; i++) {
        if (teachers[i].getTeacherID().equals(teacherID)) {
            return true;
        }
    }
    return false;
}


    public void addTeacher() {
        String teacherID;
        String fullName;
        String email;
        String phoneNumber;
        String department;
        int pos;

        do {
            teacherID = checkValidation.checkValid.InputPattern("Teacher ID: ", "T[\\d]{5}");
            pos = -1;
            for (int i = 0; i < this.size; i++) {
                if (this.teachers[i].getTeacherID().equals(teacherID)) {
                    pos = i;
                    break;
                }
            }
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);

        fullName = checkValidation.checkValid.checkLength("Full name: ", 0, 30);
        email = checkValidation.checkValid.checkLength("Email: ", 0, 30);
        phoneNumber = checkValidation.checkValid.checkLength("Phone number: ", 10, 12);
        department = checkValidation.checkValid.checkLength("Department: ", 0, 20);

        Teacher newTeacher = new Teacher(teacherID, fullName, email, phoneNumber, department); // Khởi tạo giáo viên mới
        this.add(newTeacher); // Thêm giáo viên mới vào danh sách
    }

    private void add(Teacher teacher) {
        if (this.size == this.teachers.length) {
            this.teachers = Arrays.copyOf(this.teachers, this.teachers.length * 2);
        }
        this.teachers[this.size++] = teacher;
    }

    public void viewTeacher() {
        System.out.println("List of teachers:");
        System.out.format("+--------+------------------------+-------------------------------+---------------+----------------+\n");
        System.out.format("|   ID   |          Name          |             Email             |     Phone     |  Department Name|\n");
        System.out.format("+--------+------------------------+-------------------------------+---------------+----------------+\n");

        for (int i = 0; i < size; i++) {
            System.out.format("| %-6s | %-22s | %-29s | %-13s | %-14s |\n",
                    teachers[i].getTeacherID(), teachers[i].getFullName(), teachers[i].getEmail(),
                    teachers[i].getPhoneNumber(), teachers[i].getDepartment());
            System.out.format("+--------+------------------------+-------------------------------+---------------+----------------+\n");
        }
    }

    public void removeTeacher() {
        String teacherID = checkValidation.checkValid.InputPattern("Teacher ID: ", "T[\\d]{5}");
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (teachers[i].getTeacherID().equals(teacherID)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                teachers[i] = teachers[i + 1];
            }
            size--;
            System.out.println("Teacher with ID " + teacherID + " has been removed.");
        } else {
            System.out.println("Teacher with ID " + teacherID + " does not exist in the system!");
        }
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
                StringTokenizer st = new StringTokenizer(line, ",");
                String teacherID = st.nextToken().trim();
                String fullName = st.nextToken().trim();
                String email = st.nextToken().trim();
                String phoneNumber = st.nextToken().trim();
                String department = st.nextToken().trim();

                Teacher teacher = new Teacher(teacherID, fullName, email, phoneNumber, department);
                add(teacher);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
public void writeToFile() {
    if (size == 0) {
        System.out.println("Empty list!");
    } else {
        try (PrintWriter pw = new PrintWriter(new File(fName))) {
            for (int i = 0; i < size; i++) {
                pw.println(teachers[i].getTeacherID() + ", " + teachers[i].getFullName() + ", " + teachers[i].getEmail() + ", " + teachers[i].getPhoneNumber() + ", " + teachers[i].getDepartment());
            }
            System.out.println("Writing to file: DONE.");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

}
