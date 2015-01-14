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
import support.enums.ColumnTypes;


/**
 *
 * @author kot
 */

@Table (name="groups")
public class Group {
    
    
    @Primary
    @Column(name="group_id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    private Long userId;
    
    @Column(name="name",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    private String name;
    
     @Column(name="add_date",isNull = false,type = ColumnTypes.DATETIME,isEdit = false)
    private Date addDate;
 
}
