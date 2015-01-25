/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.userRights.entity;

import java.util.Date;
import support.commons.db.Column;
import support.commons.db.Index;
import support.commons.db.Table;
import support.commons.db.Primary;
import support.commons.Validator;
import support.enums.ColumnTypes;
import support.enums.ValidatorTypes;

/**
 *
 * @author kot
 */

@Table (name="role_link_right")
public class RoleLinkRight {
    
    
    @Column(name="role_id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long roleId;
    
    @Index
    @Column(name="right_id",isNull = false,type = ColumnTypes.INTEGER,isEdit = true)
    public Long rightId;
 
}
