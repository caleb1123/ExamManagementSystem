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
public class Admin {
    String adminID;
    String name;
    String email;
    String status;
    String role;
    

    public Admin(String adminID) {
        this.adminID = adminID;
    }

    public Admin(String adminID, String name, String email, String status, String role) {
        this.adminID = adminID;
        this.name = name;
        this.email = email;
        this.status = status;
        this.role = role;
        
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    
   

    @Override
    public String toString() {
        return this.adminID + ", "  + this.name + ", "  + this.email + ", "  + this.status + ", "  + this.role;
    }
    
    
  
   
    
}
