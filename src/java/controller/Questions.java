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
}