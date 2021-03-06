/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import support.commons.db.Column;
import support.commons.db.Primary;
import support.commons.db.Table;
import support.enums.ColumnTypes;

/**
 *
 * @author Admin
 */
@Table (name="questions")
public class Questions {
    
    @Primary
    @Column(name="id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long id;
    
    @Column(name="root_id",isNull = true,type = ColumnTypes.INTEGER,isEdit = true)
    public Long rootId;
    
    @Column(name="value",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    public String value;
    
    @Column(name="comment",isNull = true,type = ColumnTypes.VARCHAR,isEdit = true)
    public String comment;
    
    @Column(name="inquirerId",isNull = false,type = ColumnTypes.INTEGER,isEdit = true)
    public Long inquirerId;
}
