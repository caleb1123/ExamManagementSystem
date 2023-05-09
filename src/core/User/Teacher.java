/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.User;

import java.security.Permissions;

/**
 *
 * @author ryy15
 */
public class Teacher {

     String teacherID;
    String fullName;
    String email;
    String phoneNumber;
    String department;
 

    public Teacher(String teacherID) {
        this.teacherID = teacherID;
    }

    public Teacher(String teacherID, String fullName, String email, String phoneNumber, String department) {
        this.teacherID = teacherID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }
    



    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



    


    
}
