/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
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
@Controller
public class Role extends ControllerAbstract {

    public Role() {
    }

    @Right
    public void showRoles() {
        String result = "";
        try {
            entity.Role us = new entity.Role();
            Dao dao = getDao();
            List<Row> res = dao.find(us);
            addResponce("roleList", res);
            setResult(render.Role.showRoles(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(result = StringAdapter.getStackTraceException(e));
        }
    }

    @Right
    public void add() {
        String result = "";
        try {
            String name = StringAdapter.getString(getRequest().get("name"));
            if (StringAdapter.isNull(name)) {
                addResponce("error", "передайте название роли");
            } else {
                entity.Role rl = new entity.Role();
                rl.name = name;
                try {
                    getDao().save(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Role());
            addResponce("roleList", res);
            setResult(render.Role.showRoles(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }

    @Right
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
                entity.Role rl = new entity.Role();
                rl.name = name;
                rl.roleId = Long.valueOf(id);
                try {
                    getDao().update(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Role());
            addResponce("roleList", res);
            setResult(render.Role.showRoles(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right
    public void delete() {
        String result = "";
        try {
            String id = StringAdapter.getString(getRequest().get("role_id"));
            if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте ид роли");
            }else {
                entity.Role rl = new entity.Role();
                rl.roleId = Long.valueOf(id);
                try {
                    getDao().delete(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Role());
            addResponce("roleList", res);
            setResult(render.Role.showRoles(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }

}
