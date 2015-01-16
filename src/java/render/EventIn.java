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
            AbsEnt base = fr.div("float:left;width:100%", null);
            
            
            AbsEnt div = fr.div("float:left;width:100%", null);
            base.addEnt(div);

            if (StringAdapter.NotNull(service.get("error"))) {
                div.setValue(service.get("error"));
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
            if (StringAdapter.NotNull(StringAdapter.getString(service.get("name")))) {
                AbsEnt label = fr.label("", "", "", "");
                label.setValue(StringAdapter.getString(service.get("name")));
                base.addEnt(label);
            }
            if (StringAdapter.NotNull(StringAdapter.getString(request.get("number")))) {
                AbsEnt label = fr.label("", "", "", "");
                label.setValue(StringAdapter.getString(request.get("number")));
                base.addEnt(label);
            }
            AbsEnt label1 = fr.label("", "", "", "");
            label1.setValue("Тип пациента:");
            base.addEnt(label1);
            List<Row> list1 = (List<Row>) service.get("patientstypes");
            AbsEnt combo1 = fr.combo(fr.createComboMap(list1, "id","name"), null, "patientstypes");
            base.addEnt(combo1);
            AbsEnt label2 = fr.label("", "", "", "");
            label2.setValue("Причина обращения пациента:");
            base.addEnt(label2);
            List<Row> list2 = (List<Row>) service.get("treatments");
            AbsEnt combo2 = fr.combo(fr.createComboMap(list2, "treatment_id","name"), null, "treatments");
            base.addEnt(combo2);
            
            result += base.render();

        } catch (Exception e) {
            result += StringAdapter.getStackTraceException(e);
        }
        return result;
    }
}
    

