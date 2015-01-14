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
 * @author Admin
 */
@Table (name="talk_shablon_in")
public class TalkShablonIn {
    @Primary
    @Column(name="id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long id;
    
    @Column(name="value",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    public String value;    
}