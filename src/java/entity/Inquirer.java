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
@Table (name="inquirers")
public class Inquirer {
    
    @Primary
    @Column(name="id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long id;
    
    @Column(name="name",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    public String name;

}
