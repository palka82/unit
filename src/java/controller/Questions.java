/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import api.ControllerAbstract;
import java.util.ArrayList;
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
        List<Row> questions = new ArrayList();
        
        String primaryId = StringAdapter.getString(getRequest().get("id"));
        if (StringAdapter.isNull(primaryId)) {
                addResponce("error", "передайте ID опросника");
        } else {
                String query="select id, value from questions where inquirerId = '" + primaryId + "' and root_id is null";
        
                //setResult(render.Questions.showQuestions(getRequest(), getResponce()));
                QueryExecutor qe=ExecutorFabric.getExecutor(getDao().getConnection(), query, DbTypes.MySQL);
                qe.select();
                if(qe.getError().isEmpty()){
                    for(Row rw:qe.getResultList()){
                        questions.add(rw);
                    }
            
                    addResponce("questionsList",questions);
                    setResult(render.Questions.showQuestions(getRequest(), getResponce()));
                }else{
                    throw new Exception(StringAdapter.getStringFromList(qe.getError()));
                }
        }
    }
    
    @Right(description = "Добавить")
    public void addQuestion() {
        String result = "";
        try {
            String name = StringAdapter.getString(getRequest().get("name"));
            String primaryId = StringAdapter.getString(getRequest().get("primaryId"));
            if (StringAdapter.isNull(name)) {
                addResponce("error", "передайте вопрос");
            } else {
                entity.Questions rl = new entity.Questions();
                rl.value = name;
                rl.inquirerId = Long.valueOf(primaryId);
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
    
    @Right(description = "Добавить ответ")
    public void addAnswer() throws Exception {
        String result = "";
        try {
            String answer = StringAdapter.getString(getRequest().get("answer"));
            String rootId = StringAdapter.getString(getRequest().get("rootId"));
            String primaryId = StringAdapter.getString(getRequest().get("primaryId"));
            if (StringAdapter.isNull(answer)) {
                addResponce("error", "поле значение не должно быть пустым");
            } else {
                entity.Questions rl = new entity.Questions();
                rl.value = answer;
                rl.rootId = Long.valueOf(rootId);
                rl.inquirerId = Long.valueOf(primaryId);
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
    
    public List<Row> getAnswers(Object qId) throws Exception {
        List<Row> res = new ArrayList();
        
        if (StringAdapter.NotNull(qId)) {
                String query="select id, value from questions where rootId = '" + qId + "'";
                QueryExecutor qe=ExecutorFabric.getExecutor(getDao().getConnection(), query, DbTypes.MySQL);
                qe.select();
                if(qe.getError().isEmpty()){
                    for(Row rw:qe.getResultList()){
                        res.add(rw);
                    }
                }else{
                    throw new Exception(StringAdapter.getStringFromList(qe.getError()));
                }
        }
        return res;
    }
}