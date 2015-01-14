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
 * @author morion
 */

@Table (name="reports")
public class Reports {
    
    @Primary
    @Column(name="report_id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long reportId;
    
    @Column(name="name",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    public String name;
}
