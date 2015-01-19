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

/**
 *
 * @author Admin
 */
public class EventIn {

    public static String showEventIn(Map<String, Object> request, Map<String, Object> service) {
        String result = "";
        try {
            FabricRender fr = FabricRender.getInstance(new Project());
            //AbsEnt base = fr.div("dlg-background", null, "", "");
            
            
            AbsEnt div_content = fr.div("dlg-content", null,"display:block;","");
            
            if (StringAdapter.NotNull(service.get("error"))) {
                div_content.setValue(service.get("error"));
            }

            /*FormOptionInterface fo = fr.getFormOption();
            fo.setHorisontal(Boolean.TRUE);
            fo.setButtonName("Добавить");
            fo.setAction("add");
            fo.setObject("WelcomeSpeechShablon");
            fo.setNoValidateRights();
            Map<AbsEnt, String> inner = new LinkedHashMap();
            inner.put(fr.textInput("value", "", "Текст приветствия"), "");
            AbsEnt se = fr.rightForm(inner, fo);

            div = fr.div("float:left;width:100%", null);
            base.addEnt(div);
            div.addEnt(se);

            AbsEnt table = fr.table("1", "5", "0");
            List<Row> list = (List<Row>) service.get("list");
            for (Row shablon : list) {
                fr.tr(table, shablon.get("id"),getChangeForm(shablon.get("id"),shablon.get("value")),getDeleteForm(shablon.get("id")));
            }

            div = fr.div("float:left;width:100%", null);
            base.addEnt(div);
            div.addEnt(table);*/
            //AbsEnt table = fr.table("tbl_eventin", "", "1", "5", "0", "");
            
            if (StringAdapter.NotNull(StringAdapter.getString(service.get("name")))) {
                AbsEnt label = fr.label("", "", "", "");
                label.setValue(StringAdapter.getString(service.get("name")));
                div_content.addEnt(label);
            }
            if (StringAdapter.NotNull(StringAdapter.getString(request.get("number")))) {
                AbsEnt label = fr.label("", "", "", "");
                label.setValue(StringAdapter.getString(request.get("number")));
                div_content.addEnt(label);
            }
            AbsEnt label1 = fr.label("patientstypes", "", "", "");
            label1.setValue("Тип пациента:");
            div_content.addEnt(label1);
            List<Row> list1 = (List<Row>) service.get("patientstypes");
            AbsEnt combo1 = fr.combo(fr.createComboMap(list1, "id","name"), null, "patientstypes");
            div_content.addEnt(combo1);
            AbsEnt label2 = fr.label("treatments", "", "", "");
            label2.setValue("Причина обращения пациента:");
            div_content.addEnt(label2);
            List<Row> list2 = (List<Row>) service.get("treatments");
            AbsEnt combo2 = fr.combo(fr.createComboMap(list2, "treatment_id","name"), null, "treatments");
            div_content.addEnt(combo2);
            
            AbsEnt label3 = fr.label("talkresult", "", "", "");
            label3.setValue("Итог разговора:");
            div_content.addEnt(label3);
            List<Row> list3 = (List<Row>) service.get("talkresult");
            AbsEnt combo3 = fr.combo(fr.createComboMap(list3, "id","name"), null, "talkresult");
            div_content.addEnt(combo3);

            AbsEnt label4 = fr.label("doctors", "", "", "");
            label4.setValue("Нужный врач:");
            div_content.addEnt(label4);
            List<Row> list4 = (List<Row>) service.get("doctors");
            AbsEnt combo4 = fr.combo(fr.createComboMap(list4, "id","name"), null, "doctors");
            div_content.addEnt(combo4);

            AbsEnt label5 = fr.label("comment", "", "", "");
            label5.setValue("Комментарий:");
            div_content.addEnt(label5);
            AbsEnt comment = fr.textArea("comment", null, 4, 50, "Комментарий");
            div_content.addEnt(comment);
            //result += base.render();
            result += "<input type=\"button\" onclick=\"hide();\" value=\"hide\"/>";
            result += div_content.render();
            

        } catch (Exception e) {
            result += StringAdapter.getStackTraceException(e);
        }
        return result;
    }
}
    

