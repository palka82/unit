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
public class TalkResult extends ControllerAbstract {
   
    public TalkResult() {
    }
 
    @Right
    public void showTalkResult() {
        String result = "";
        try {
            entity.TalkResult us = new entity.TalkResult();
            Dao dao = getDao();
            List<Row> res = dao.find(us);
            addResponce("list", res);
            setResult(render.TalkResult.showTalkResult(getRequest(), getResponce()));
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
                addResponce("error", "передайте итог разговора");
            } else {
                entity.TalkResult rl = new entity.TalkResult();
                rl.name = name;
                try {
                    getDao().save(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.TalkResult());
            addResponce("list", res);
            setResult(render.TalkResult.showTalkResult(getRequest(), getResponce()));
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
                addResponce("error", "передайте итог разговора");
            }else if (StringAdapter.isNull(id)) {
                addResponce("error", "Передайте id итог разговора");
            }else {
                entity.TalkResult rl = new entity.TalkResult();
                rl.name = name;
                rl.talkresultId = Long.valueOf(id);
                try {
                    getDao().update(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.TalkResult());
            addResponce("list", res);
            setResult(render.TalkResult.showTalkResult(getRequest(), getResponce()));
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
                addResponce("error", "Передайте id итог разговора");
            }else {
                entity.TalkResult rl = new entity.TalkResult();
                rl.talkresultId = Long.valueOf(id);
                try {
                    getDao().delete(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            List<Row> res = getDao().find(new entity.TalkResult());
            addResponce("list", res);
            setResult(render.TalkResult.showTalkResult(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
}
