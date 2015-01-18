/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.userRights.controller;

import api.ControllerAbstract;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import support.DateAdapter;
import support.Security;
import support.StringAdapter;
import support.commons.Controller;
import support.commons.Right;
import support.db.Dao;
import support.db.executor.ExecutorFabric;
import support.db.executor.QueryExecutor;
import support.db.executor.Row;
import support.enums.DbTypes;

/**
 *
 * @author Кот
 */
@Controller(description = "Роли")
public class Role extends ControllerAbstract {

    public Role() {
    }

    @Right(description = "Показать роли")
    public void showRoles() {
        String result = "";
        try {
            packages.userRights.entity.Role us = new packages.userRights.entity.Role();
            Dao dao = getDao();
            List<Row> res = dao.find(us);
            addResponce("roleList", res);
            setResult(packages.userRights.render.Role.showRoles(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(result = StringAdapter.getStackTraceException(e));
        }
    }

    
    @Right(description = "Состав роли")
    public void showRolesConsist() {
        String result = "";
        try {
            Dao dao = getDao();
            if(StringAdapter.NotNull(getRequest().get("spec"))){
                if(StringAdapter.NotNull(getRequest().get("role_id"),getRequest().get("right_id"))){
                    packages.userRights.entity.RoleLinkRight ch = new packages.userRights.entity.RoleLinkRight();
                    ch.roleId=Long.valueOf(StringAdapter.getString(getRequest().get("role_id")));
                    ch.rightId=Long.valueOf(StringAdapter.getString(getRequest().get("right_id")));
                    if(dao.findByValues(ch).isEmpty()){
                        dao.save(ch);
                    }else{
                        dao.delete(ch);
                    }
                } else{
                    addResponce("error", "не передана роль или право");
                }
            }
            
            if(StringAdapter.NotNull(getRequest().get("role_id"))){
                packages.userRights.entity.RoleLinkRight link = new packages.userRights.entity.RoleLinkRight();
                
                link.roleId=Long.valueOf(StringAdapter.getString(getRequest().get("role_id")));
                List<Row> links = dao.findByValues(link);
                addResponce("links", links);

                packages.userRights.entity.Role role = new packages.userRights.entity.Role();
                List<Row> roles=dao.find(role);
                addResponce("roles", roles);


                packages.userRights.entity.Rights right = new packages.userRights.entity.Rights();
                List<Row> rights=dao.find(right);
                String err="";
                addResponce("role_id", getRequest().get("role_id"));
                addResponce("rights", rights);
            }else{
                addResponce("error", "Не передан id роли - невозможно определить роль");
            }
            setResult(packages.userRights.render.Role.showRolesConsist(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(result = StringAdapter.getStackTraceException(e));
        }
    }
    
    
    @Right(description = "Добавить")
    public void add() {
        String result = "";
        try {
            String name = StringAdapter.getString(getRequest().get("name"));
            if (StringAdapter.isNull(name)) {
                addResponce("error", "передайте название роли");
            } else {
                packages.userRights.entity.Role rl = new packages.userRights.entity.Role();
                rl.name = name;               
                
                try {
                    getDao().save(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new packages.userRights.entity.Role());
            addResponce("roleList", res);
            setResult(packages.userRights.render.Role.showRoles(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }

    @Right(description = "Изменить")
    public void change() {
        String result = "";
        try {
            String name = StringAdapter.getString(getRequest().get("name"));
            String id = StringAdapter.getString(getRequest().get("role_id"));
            if (StringAdapter.isNull(name)) {
                addResponce("error", "передайте название роли");
            }else if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте ид роли");
            }else {
                packages.userRights.entity.Role rl = new packages.userRights.entity.Role();
                rl.name = name;
                rl.roleId = Long.valueOf(id);
                try {
                    getDao().update(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new packages.userRights.entity.Role());
            addResponce("roleList", res);
            setResult(packages.userRights.render.Role.showRoles(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right(description = "Удалить")
    public void delete() {
        String result = "";
        try {
            String id = StringAdapter.getString(getRequest().get("role_id"));
            if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте ид роли");
            }else {
                packages.userRights.entity.Role rl = new packages.userRights.entity.Role();
                rl.roleId = Long.valueOf(id);
                try {
                    getDao().delete(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new packages.userRights.entity.Role());
            addResponce("roleList", res);
            setResult(packages.userRights.render.Role.showRoles(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }

}
