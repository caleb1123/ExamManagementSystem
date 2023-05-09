/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.User;

import core.User.Admin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ryy15
 */
public class AdminList {

    private Admin[] admins;
    int size;
    String fName = "src\\data\\Admins.dat";

    public AdminList() {
        this.admins = new Admin[100];
        this.size = 0;
    }

    public boolean checkID(String adminID) {
        for (int i = 0; i < size; i++) {
            if (admins[i].getAdminID().equals(adminID)) {
                return true;
            }
        }
        return false;
    }

    public void readFromFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File is not existed!");
            System.exit(0);
        } else {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String adminID = stk.nextToken().trim();
                    String name = stk.nextToken().trim();
                    String email = stk.nextToken().trim();
                    String role = stk.nextToken().trim();
                    String status = stk.nextToken().trim();

                    Admin newAdmin = new Admin(adminID, name, email, status, role);
                    this.add(newAdmin);
                }
                br.close();
                fr.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void writeToFile() {
        if (admins.length == 0) {
            System.out.println("Empty list!");
        } else {
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(fName);
                for (int i = 0; i < size; i++) {
                    pw.println(admins[i].getAdminID() + ", " + admins[i].getName() + ", " + admins[i].getEmail() + ", " + admins[i].getStatus() + ", " + admins[i].getRole());
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

    public void addAdmin() {
        String adminID;
        String name;
        String email;
        String status;
        String role;
        String pass;
        int pos;
        do {
            adminID = checkValidation.checkValid.InputPattern("AdminID: ", "A[\\d]{5}");
            pos = -1;
            for (int i = 0; i < this.size; i++) {
                if (this.admins[i].getAdminID().equals(adminID)) {
                    pos = i;
                    break;
                }
            }
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);

        name = checkValidation.checkValid.checkLength("Name admin: ", 5, 30);
        email = checkValidation.checkValid.checkLength("Email admin: ", 5, 30);
        status = checkValidation.checkValid.InputPattern("Admin status(Available or Not Available): ", "[Aa][Vv][Aa][Ii][Ll][Aa][Bb][Ll][Ee]|[Nn][Oo][Tt]\\s{1}[Aa][Vv][Aa][Ii][Ll][Aa][Bb][Ll][Ee]");
        role = checkValidation.checkValid.checkLength("Role admin", 0, 20);
        Admin newAdmin = new Admin(adminID, name, email, status, role);
        this.add(newAdmin);
    }

    private void add(Admin admin) {
        if (this.size == this.admins.length) {
            this.admins = Arrays.copyOf(this.admins, this.admins.length * 2);
        }
        this.admins[this.size++] = admin;
    }

    public void removeAdmin() {
        // Kiểm tra xem ID của quản trị viên đã tồn tại hay chưa
        String adminID = checkValidation.checkValid.InputPattern("AdminID: ", "A[\\d]{5}");
        int adminIndex = -1;
        for (int i = 0; i < size; i++) {
            if (admins[i].getAdminID().equals(adminID)) {
                adminIndex = i;
                break;
            }
        }

        // Nếu quản trị viên đã tồn tại, xóa nó khỏi danh sách
        if (adminIndex != -1) {
            for (int i = adminIndex; i < size - 1; i++) {
                admins[i] = admins[i + 1];
            }
            size--;
            System.out.println("Admin removed successfully!");
        } else {
            System.out.println("Admin not found!");
        }
    }

}
