/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import support.commons.db.Column;
import support.commons.db.Table;
import support.commons.db.Primary;
import support.commons.db.Validator;
import support.enums.ColumnTypes;
import support.enums.Validators;


/**
 *
 * @author kot
 */

@Table (name="rights")
public class Rights {
    
    
    @Primary
    @Column(name="right_id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long rightId;
    
    @Column(name="object",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    public String object;
    
    @Column(name="action",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    public String action;
     
    @Column(name="add_date",isNull = false,type = ColumnTypes.DATETIME,isEdit = false)
    public Date addDate;
 
}
