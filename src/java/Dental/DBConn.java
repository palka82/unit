/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dental;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Admin
 */
public class DBConn {
    private static final DBConn instance = new DBConn();
    //private static final String dburl = "jdbc:sqlanywhere:uid=dba;pwd=sql;eng=dental;database=d4w;links=tcpip(host=192.168.0.26)";
    private static final String dburl = "jdbc:sybase:Tds:192.168.0.26:2638?SERVICENAME=d4w";
    
    private DBConn() {
        
    }
    
    public static Connection createConnection() throws Exception{
        Connection connection = null;
        Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
        connection = DriverManager.getConnection(dburl,"dba","sql");
        return connection;
    }
}
