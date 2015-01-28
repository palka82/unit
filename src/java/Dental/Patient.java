/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dental;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Admin
 */
public class Patient {
    
    private long id;
    private String name;
    private String middlename;
    private String surname;
    private String dob;
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public String getDob() {
        return dob;
    }
    
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }
    
    public String getMiddlename() {
        return middlename;
    }
}
