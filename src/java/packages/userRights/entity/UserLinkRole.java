/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.userRights.entity;

import java.util.Date;
import support.commons.db.*;
import support.enums.ColumnTypes;
import support.enums.Validators;


/**
 *
 * @author kot
 */

@Table (name="user_link_role")
public class UserLinkRole {
    
    @Primary
    @Column(name="user_id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long userId;
    
    @Column(name="role_id",isNull = false,type = ColumnTypes.INTEGER,isEdit = false)
    public Long roleId;
    
}
