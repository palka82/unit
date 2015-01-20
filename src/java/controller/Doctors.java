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
 * @author Admin
 */
@Controller(description = "Врачи")
public class Doctors extends ControllerAbstract{
    
    public Doctors() {
    }
    
    @Right(description = "Список специальностей")
    public void showDoctors() {
        String result = "";
        try {
            entity.Doctors us = new entity.Doctors();
            Dao dao = getDao();
            List<Row> res = dao.find(us);
            addResponce("list", res);
            setResult(render.Doctors.showDoctors(getRequest(), getResponce()));
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
                addResponce("error", "передайте специализацию доктора");
            } else {
                entity.Doctors rl = new entity.Doctors();
                rl.name = name;
                try {
                    getDao().save(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Doctors());
            addResponce("list", res);
            setResult(render.Doctors.showDoctors(getRequest(), getResponce()));
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
                addResponce("error", "Передайте специальность врача");
            }else if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте id специальности доктора");
            }else {
                entity.Doctors rl = new entity.Doctors();
                rl.name = name;
                rl.id = Long.valueOf(id);
                try {
                    getDao().update(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Doctors());
            addResponce("list", res);
            setResult(render.Doctors.showDoctors(getRequest(), getResponce()));
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
                addResponce("error", "Передайте id специальности доктор");
            }else {
                entity.Doctors rl = new entity.Doctors();
                rl.id = Long.valueOf(id);
                try {
                    getDao().delete(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Doctors());
            addResponce("list", res);
            setResult(render.Doctors.showDoctors(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
}
    

