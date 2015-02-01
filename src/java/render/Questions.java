/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import api.FabricRender;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import settings.Project;
import support.StringAdapter;
import support.db.executor.Row;
import support.web.AbsEnt;
import support.web.FormOptionInterface;
import Dental.Patient;
import support.web.EnumAttrType;
import support.web.entities.WebEnt;


/**
 *
 * @author Admin
 */
public class Questions {
    
    public static String showQuestions(Map<String, Object> request, Map<String, Object> service) {
        String result = "";
        try {
            FabricRender fr = FabricRender.getInstance(new Project());
            AbsEnt base = fr.div("float:left;width:100%", null);

            AbsEnt div = fr.div("float:left;width:100%", null);
            base.addEnt(div);

            if (StringAdapter.NotNull(service.get("error"))) {
                div.setValue(service.get("error"));
            }
            
            List<Row> questions = (List<Row>) service.get("questionsList");
            
            
            FormOptionInterface fo = fr.getFormOption();
            fo.setHorisontal(Boolean.TRUE);
            fo.setButtonName("Добавить");
            fo.setAction("addQuestion");
            fo.setObject("Questions");
            fo.setNoValidateRights();
            Map<AbsEnt, String> inner = new LinkedHashMap();
            inner.put(fr.textInput("name", "", "Вопрос"), "");
            inner.put(fr.hiddenInput("primaryId", request.get("id")), "");
            //Map<String, Object> mapQuestions = fr.createComboMap(questions, "id", "value");
            
            //inner.put((AbsEnt) mapQuestions,"");
            AbsEnt se = fr.rightForm(inner, fo);

            div = fr.div("float:left;width:100%", null);
            base.addEnt(div);
            div.addEnt(se);
            
            //result += base.render();
            //String html = "<ul>";
            
                        
            for (Row q : questions) {
                //li = fr.
                //html +="<li>"+q.get("value")+"</li>"+getDeleteForm(q.get("id"));
                AbsEnt li = WebEnt.getEnt(WebEnt.Type.LI);
                AbsEnt divAddQ = fr.div("divaddq"+q.get("id"), null,"", "");
                divAddQ.setJs("style=float:left;width:100%;display:none;");
                AbsEnt textanswer = WebEnt.getEnt(WebEnt.Type.INPUT);
                textanswer.setAttribute(EnumAttrType.type, "text");
                textanswer.setAttribute(EnumAttrType.size, "");
                textanswer.setValue("");
                AbsEnt buttonAdd = WebEnt.getEnt(WebEnt.Type.BUTTON);
                buttonAdd.setValue("Сохранить");
                divAddQ.addEnt(textanswer,buttonAdd);
                //divAddQ.addEnt(buttonAddQ);
                //li.setValue(q.get("value"));
                li.addEnt(fr.div("",null).addEnt(fr.div("",q.get("value")),fr.div("", null).addEnt(getAddForm(q.get("id"))),fr.div("", "").addEnt(getDeleteForm(q.get("id")))),divAddQ);
                base.addEnt(li);
            }
            
            result += base.render();
            //html +="</ul>";
            //result += html;
            /*Patient test = Dental.PatientManager.getData("89197005302");
            AbsEnt label1 = fr.label("test1", "", "", "");
            label1.setValue(test.getSurname());
            base.addEnt(label1);
            AbsEnt label2 = fr.label("test2", "", "", "");
            label2.setValue(test.getName());
            base.addEnt(label2);
            AbsEnt label3 = fr.label("test3", "", "", "");
            label3.setValue(test.getMiddlename());
            base.addEnt(label3);*/
            
            /*AbsEnt table = fr.table("1", "5", "0");
            List<Row> list = (List<Row>) service.get("roleList");
            for (Row role : list) {
                fr.tr(table,getShowConsistForm(role.get("role_id")) ,getChangeForm(role.get("role_id"),role.get("name")),getDeleteForm(role.get("role_id")));
            }

            div = fr.div("float:left;width:100%", null);
            base.addEnt(div);
            div.addEnt(table);*/

        } catch (Exception e) {
            result += StringAdapter.getStackTraceException(e);
        }
        return result;
    }
 
    private static AbsEnt getDeleteForm(Object primaryId) throws Exception {
        FabricRender fr = FabricRender.getInstance(new Project());
        FormOptionInterface fo = fr.getFormOption();
        fo.setHorisontal(Boolean.TRUE);
        fo.setButtonName("Удалить");
        fo.setAction("delete");
        fo.setObject("Questions");
        fo.setNoValidateRights();
        Map<AbsEnt, String> inner = new LinkedHashMap();
        inner.put(fr.hiddenInput("q_id", primaryId), "");
        AbsEnt se = fr.rightForm(inner, fo);
        return se;
    }
    
    private static AbsEnt getAddForm(Object primaryId) throws Exception {
        /*FabricRender fr = FabricRender.getInstance(new Project());
        FormOptionInterface fo = fr.getFormOption();
        fo.setHorisontal(Boolean.TRUE);
        fo.setButtonName("Добавить");
        fo.setAction("addAnswer");
        fo.setObject("Questions");
        fo.setNoValidateRights();
        Map<AbsEnt, String> inner = new LinkedHashMap();
        inner.put(fr.hiddenInput("q_id", primaryId), "");
        AbsEnt se = fr.rightForm(inner, fo);*/
        AbsEnt buttonAddQ = WebEnt.getEnt(WebEnt.Type.BUTTON);
        buttonAddQ.setValue("Добавить ответ");
        buttonAddQ.setJs("onclick=\"showAddForm("+primaryId+");\"");

        return buttonAddQ;
    }
}
