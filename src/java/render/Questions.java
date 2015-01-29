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
            fo.setAction("add");
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

            result += base.render();

        } catch (Exception e) {
            result += StringAdapter.getStackTraceException(e);
        }
        return result;
    }
 
}
