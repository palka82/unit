/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.userRights.entity;

import java.util.Date;
import support.commons.db.Column;
import support.commons.db.Table;
import support.commons.db.Primary;
import support.commons.Validator;
import support.enums.ColumnTypes;
import support.enums.ValidatorTypes;


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
    
    @Column(name="object_description",isNull = true,type = ColumnTypes.VARCHAR,isEdit = true)
    public String objectDescription;
    
    @Column(name="action_description",isNull = true,type = ColumnTypes.VARCHAR,isEdit = true)
    public String actionDescription;
     
    @Column(name="add_date",isNull = false,type = ColumnTypes.DATETIME,isEdit = false)
    public Date addDate;
 
}
