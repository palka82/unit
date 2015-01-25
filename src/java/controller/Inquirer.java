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
import support.db.select.SelectMysql;
import support.enums.DbTypes;
import support.logic.RightStack;


/**
 *
 * @author Admin
 */
@Controller(description = "Опросник")
public class Inquirer extends ControllerAbstract {
    
    public Inquirer() {
        
    }

    @Right(description = "Показать вопросы")
    public void showInquirer() {
        String result = "";
        try {
            entity.Inquirer us = new entity.Inquirer();
            Dao dao = getDao();
            List<Row> res = dao.find(us);
            addResponce("list", res);
            setResult(render.Inquirer.showInquirer(getRequest(), getResponce()));
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
                addResponce("error", "передайте название опросника");
            } else {
                entity.Inquirer rl = new entity.Inquirer();
                rl.name = name;               
                
                try {
                    getDao().save(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Inquirer());
            addResponce("list", res);
            setResult(render.Inquirer.showInquirer(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }

    @Right(description = "Удалить")
    public void delete() {
        String result = "";
        try {
            String id = StringAdapter.getString(getRequest().get("id"));
            if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте id опросника");
            }else {
                entity.Inquirer rl = new entity.Inquirer();
                rl.id = Long.valueOf(id);
                try {
                    getDao().delete(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Inquirer());
            addResponce("list", res);
            setResult(render.Inquirer.showInquirer(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right(description = "Изменить")
    public void change() {
        String result = "";
        try {
            String name = StringAdapter.getString(getRequest().get("name"));
            String id = StringAdapter.getString(getRequest().get("id"));
            if (StringAdapter.isNull(name)) {
                addResponce("error", "передайте название опросника");
            }else if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте id опросника");
            }else {
               entity.Inquirer rl = new entity.Inquirer();
                rl.name = name;
                rl.id = Long.valueOf(id);
                try {
                    getDao().update(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Inquirer());
            addResponce("list", res);
            setResult(render.Inquirer.showInquirer(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
}
