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
@Controller
public class TalkShablonIn extends ControllerAbstract{
    
    public TalkShablonIn() {
    }

    @Right
    public void showTalkShablonIn() {
        String result = "";
        try {
            entity.TalkShablonIn us = new entity.TalkShablonIn();
            Dao dao = getDao();
            List<Row> res = dao.find(us);
            addResponce("list", res);
            setResult(render.TalkShablonIn.showTalkShablonIn(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(result = StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right
    public void add() {
        String result = "";
        try {
            String value = StringAdapter.getString(getRequest().get("value"));
            if (StringAdapter.isNull(value)) {
                addResponce("error", "передайте шаблон");
            } else {
                entity.TalkShablonIn rl = new entity.TalkShablonIn();
                rl.value = value;
                try {
                    getDao().save(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.TalkShablonIn());
            addResponce("list", res);
            setResult(render.TalkShablonIn.showTalkShablonIn(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right
    public void change() {
        String result = "";
        try {
            String value = StringAdapter.getString(getRequest().get("value"));
            String id = StringAdapter.getString(getRequest().get("id"));
            if (StringAdapter.isNull(value)) {
                addResponce("error", "Передайте шаблон");
            }else if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте id шаблона");
            }else {
                entity.TalkShablonIn rl = new entity.TalkShablonIn();
                rl.value = value;
                rl.id = Long.valueOf(id);
                try {
                    getDao().update(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.TalkShablonIn());
            addResponce("list", res);
            setResult(render.TalkShablonIn.showTalkShablonIn(getRequest(), getResponce()));
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
                addResponce("error", "Передайте id шаблона");
            }else {
                entity.TalkShablonIn rl = new entity.TalkShablonIn();
                rl.id = Long.valueOf(id);
                try {
                    getDao().delete(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.TalkShablonIn());
            addResponce("list", res);
            setResult(render.TalkShablonIn.showTalkShablonIn(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
}