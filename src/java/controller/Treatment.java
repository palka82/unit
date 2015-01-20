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
@Controller(description = "Тип обращения")
public class Treatment extends ControllerAbstract {
    
    public Treatment() {
    }

    @Right(description = "Показать список")
    public void showTreatments() {
        String result = "";
        try {
            entity.Treatment us = new entity.Treatment();
            Dao dao = getDao();
            List<Row> res = dao.find(us);
            addResponce("treatmentList", res);
            setResult(render.Treatment.showTreatments(getRequest(), getResponce()));
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
                addResponce("error", "передайте тип обращения");
            } else {
                entity.Treatment rl = new entity.Treatment();
                rl.name = name;
                try {
                    getDao().save(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Treatment());
            addResponce("treatmentList", res);
            setResult(render.Treatment.showTreatments(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right(description = "Изменить")
    public void change() {
        String result = "";
        try {
            String name = StringAdapter.getString(getRequest().get("name"));
            String id = StringAdapter.getString(getRequest().get("treatment_id"));
            if (StringAdapter.isNull(name)) {
                addResponce("error", "передайте тип обращения");
            }else if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте id обращения");
            }else {
                entity.Treatment rl = new entity.Treatment();
                rl.name = name;
                rl.treatmentId = Long.valueOf(id);
                try {
                    getDao().update(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Treatment());
            addResponce("treatmentList", res);
            setResult(render.Treatment.showTreatments(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right(description = "Удалить")
    public void delete() {
        String result = "";
        try {
            String id = StringAdapter.getString(getRequest().get("treatment_id"));
            if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте id обращения");
            }else {
                entity.Treatment rl = new entity.Treatment();
                rl.treatmentId = Long.valueOf(id);
                try {
                    getDao().delete(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.Treatment());
            addResponce("treatmentList", res);
            setResult(render.Treatment.showTreatments(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
}
