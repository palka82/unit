/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import api.ControllerAbstract;
import java.util.List;
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
@Controller(description = "Вопросы")
public class Questions extends ControllerAbstract{
    
    public Questions() {
        
    }
    
    @Right(description = "Показать вопросы")
    public void showQuestions() throws Exception {
        String result = "";
        List<Row> res = null;
        
        String query="select id, value from questions where root_id is null";
        
        //setResult(render.Questions.showQuestions(getRequest(), getResponce()));
        QueryExecutor qe=ExecutorFabric.getExecutor(getDao().getConnection(), query, DbTypes.MySQL);
        qe.select();
        if(qe.getError().isEmpty()){
            for(Row rw:qe.getResultList()){
                res.add(rw);
            }
            
            addResponce("questionsList",res);
            setResult(render.Questions.showQuestions(getRequest(), getResponce()));
        }else{
                throw new Exception(StringAdapter.getStringFromList(qe.getError()));
        }
    }
    
    @Right(description = "Добавить")
    public void add() {
        String result = "";
        try {
            String name = StringAdapter.getString(getRequest().get("name"));
            String primaryId = StringAdapter.getString(getRequest().get("primaryId"));
            if (StringAdapter.isNull(name)) {
                addResponce("error", "передайте название роли");
            } else {
                entity.Questions rl = new entity.Questions();
                rl.value = name;
                rl.rootId = Long.valueOf(primaryId);
                try {
                    getDao().save(rl);
                } catch (Exception ex) {
                    addResponce("error", StringAdapter.getStackTraceException(ex));
                }
            }
            /*List<Row> res = getDao().find(new packages.userRights.entity.Role());
            addResponce("roleList", res);
            setResult(packages.userRights.render.Role.showRoles(getRequest(), getResponce()));*/
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
    
}