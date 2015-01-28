/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dental;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class PatientManager {

    public static Patient getData(String mobile) throws Exception {
        
            String query = "SELECT * from d4w.dba.patients where mobile = '" + mobile + "'";
            Patient dentalUser = new Patient();
            Connection con = DBConn.createConnection();
            Statement statement = con.createStatement();
            //statement.setString(1, mobile);
            ResultSet rs = statement.executeQuery(query);
        
            if (rs.next()) {
                dentalUser.setId(rs.getLong(1));
                dentalUser.setSurname(rs.getString(2));
                dentalUser.setName(rs.getString(3));
                dentalUser.setMiddlename(rs.getString(4));
                dentalUser.setDob(rs.getString(6));
                rs.close();
                statement.close();
            }
            con.close();
            
        return(dentalUser);
    }
    
}
