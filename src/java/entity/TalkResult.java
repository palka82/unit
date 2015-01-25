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
import support.commons.Validator;
import support.enums.ColumnTypes;
import support.enums.ValidatorTypes;

/**
 *
 * @author morion
 */
@Table (name="talk_results")
public class TalkResult {
    @Primary
    @Column(name="id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long talkresultId;
    
    @Column(name="name",isNull = false,type = ColumnTypes.VARCHAR,isEdit = true)
    public String name;    
    
}
