/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import support.commons.db.*;
import support.enums.ColumnTypes;
import support.enums.Validators;


/**
 *
 * @author kot
 */

@Table (name="users")
public class User {
    
    @Primary
    @Column(name="user_id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long userId;
    
    @Column(name="login",isNull = false,type = ColumnTypes.VARCHAR,isEdit = false)
    public String login;
    
    @Column(name="password",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    public String password;
    
    @Column(name="phonenumber",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    @Validator({Validators.MAILVALIDATOR,Validators.DATEFORMATVALIDATOR})
    public String phoneNumber;
    
    @Column(name="phonepass",isNull = true,type = ColumnTypes.VARCHAR,isEdit = true)
    public String phonePass;
    
    @Column(name="add_date",isNull = false,type = ColumnTypes.DATETIME,isEdit = false)
    public Date addDate;
    
    @Column (name="name", isNull = false, type = ColumnTypes.VARCHAR, isEdit = true)
    public String name;
    
    @Column (name="surname", isNull = false, type = ColumnTypes.VARCHAR, isEdit = true)
    public String surname;
    
    @Column(name="role_id",isNull = true,type = ColumnTypes.INTEGER,isEdit = true)
    public Long roleId;
}
