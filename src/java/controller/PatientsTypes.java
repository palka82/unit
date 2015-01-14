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
 * @author morion
 */
@Controller
public class PatientsTypes extends ControllerAbstract {
    
    public PatientsTypes() {
    }
    
    @Right
    public void showPatientsTypes() {
        String result = "";
        try {
            entity.PatientsTypes us = new entity.PatientsTypes();
            Dao dao = getDao();
            List<Row> res = dao.find(us);
            addResponce("list", res);
            setResult(render.PatientsTypes.showPatientsTypes(getRequest(), getResponce()));
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
                addResponce("error", "передайте тип пациента");
            } else {
                entity.PatientsTypes rl = new entity.PatientsTypes();
                rl.name = name;
                try {
                    getDao().save(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.PatientsTypes());
            addResponce("list", res);
            setResult(render.PatientsTypes.showPatientsTypes(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right
    public void change() {
        String result = "";
        try {
            String name = StringAdapter.getString(getRequest().get("name"));
            String id = StringAdapter.getString(getRequest().get("id"));
            if (StringAdapter.isNull(name)) {
                addResponce("error", "передайте тип пациента");
            }else if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте id тип пациента");
            }else {
                entity.PatientsTypes rl = new entity.PatientsTypes();
                rl.name = name;
                rl.patientstypesId = Long.valueOf(id);
                try {
                    getDao().update(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.PatientsTypes());
            addResponce("list", res);
            setResult(render.PatientsTypes.showPatientsTypes(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right
    public void delete() {
        String result = "";
        try {
            String id = StringAdapter.getString(getRequest().get("id"));
            if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте id тип пациента");
            }else {
                entity.PatientsTypes rl = new entity.PatientsTypes();
                rl.patientstypesId = Long.valueOf(id);
                try {
                    getDao().delete(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.PatientsTypes());
            addResponce("list", res);
            setResult(render.PatientsTypes.showPatientsTypes(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }

}
