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
import support.commons.Controller;
import support.commons.Right;
import support.db.Dao;
import support.db.executor.Row;

/**
 *
 * @author Кот
 */
@Controller
public class Demo extends ControllerAbstract{    
    public Demo(){
        
    }
    
    @Right
    public void setUserDemo() throws Exception{
        String result = "";
        Row user=null;
        entity.User us = new entity.User();
         List<Row> resuldRow=getDao().find(us);
         for(Row res:resuldRow){
             if(res.get("user_id").equals(getSession().get("key"))){
                 user= res;
             }
         }
        addResponce("user", user);
        if(user!=null){
            result=render.Demo.showDemo(getRequest(), getResponce());
        }
        setResult(result);
    }


    
   
    
}
