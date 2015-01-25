/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import api.ControllerAbstract;
import java.util.List;
import support.StringAdapter;
import support.db.Dao;
import support.db.executor.Row;
/**
 *
 * @author Admin
 */
public class Questions extends ControllerAbstract{
    
    public Questions() {
        
    }
    
    public void showQuestions() {
        String result = "";
        try {
            entity.Questions us = new entity.Questions();
            Dao dao = getDao();
            List<Row> res = dao.find(us);
            addResponce("questionsList", res);
            setResult(render.Questions.showQuestions(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(result = StringAdapter.getStackTraceException(e));
        }
    }
}
