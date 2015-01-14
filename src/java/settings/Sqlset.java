/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import support.settings.SqlSettings;

/**
 *
 * @author Кот
 */
public class Sqlset implements SqlSettings{
    /**
 *
 * @author kot
 */
    public final static String uri="jdbc:mysql://localhost:3306";
    public final static String user="unit_zub_db";
    public final static String password="123456";
    public final static String dbName="unit_zub";
    public final static String dbEncoding="UTF-8";
    public final static String dbDriverName="com.mysql.jdbc.Driver";
    
    public  Sqlset(){
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getUser() {
         return user;
    }

    @Override
    public String getPassword() {
         return password;
    }

    @Override
    public String getDbName() {
         return dbName;
    }

    @Override
    public String getDbEncoding() {
        return dbEncoding;
    }

    @Override
    public String getDbDriverName() {
        return dbDriverName;
    }
    
}
