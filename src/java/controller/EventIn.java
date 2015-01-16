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
public class EventIn extends ControllerAbstract{
    
    public EventIn() {
    }
    
    @Right
    public void showEventIn() {
        String result = "";
        try {
            String user_fio = StringAdapter.getString(getSession().get("key"));
            String phone = StringAdapter.getString(getRequest().get("number"));
            if (StringAdapter.isNull(user_fio)) {
                addResponce("error", "нет ФИО пользователя");
            }
            if (StringAdapter.isNull(phone)) {
                addResponce("error", "нет номера телефона");
            }
            Dao dao = getDao();
            entity.PatientsTypes us1 = new entity.PatientsTypes();
            List<Row> res1 = dao.find(us1);
            addResponce("patientstypes",res1);
            //res1.clear();
            entity.Treatment us2 = new entity.Treatment();
            List<Row> res2 = dao.find(us2);
            addResponce("treatments",res2);
            //res2.clear();
            addResponce("name", user_fio);
            setResult(render.EventIn.showEventIn(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(result = StringAdapter.getStackTraceException(e));
        }
    }
    
}
