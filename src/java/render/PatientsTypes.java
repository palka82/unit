/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package render;

import api.FabricRender;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import settings.Project;
import support.StringAdapter;
import support.db.executor.Row;
import support.web.AbsEnt;
import support.web.EnumAttrType;
import support.web.FormOption;
import support.web.FormOptionInterface;
import support.web.entities.WebEnt;

/**
 *
 * @author morion
 */
public class PatientsTypes {
    
    public static String showPatientsTypes(Map<String, Object> request, Map<String, Object> service) {
        String result = "";
        try {
            FabricRender fr = FabricRender.getInstance(new Project());
            AbsEnt base = fr.div("float:left;width:100%", null);
            
            
            AbsEnt div = fr.div("float:left;width:100%", null);
            base.addEnt(div);

            if (StringAdapter.NotNull(service.get("error"))) {
                div.setValue(service.get("error"));
            }

            FormOptionInterface fo = fr.getFormOption();
            fo.setHorisontal(Boolean.TRUE);
            fo.setButtonName("Добавить");
            fo.setAction("add");
            fo.setObject("PatientsTypes");
            fo.setNoValidateRights();
            Map<AbsEnt, String> inner = new LinkedHashMap();
            inner.put(fr.textInput("name", "", "Название"), "");
            AbsEnt se = fr.rightForm(inner, fo);

            div = fr.div("float:left;width:100%", null);
            base.addEnt(div);
            div.addEnt(se);

            AbsEnt table = fr.table("1", "5", "0");
            List<Row> list = (List<Row>) service.get("list");
            for (Row patientstypes : list) {
                fr.tr(table, patientstypes.get("id"),getChangeForm(patientstypes.get("id"),patientstypes.get("name")),getDeleteForm(patientstypes.get("id")));
            }

            div = fr.div("float:left;width:100%", null);
            base.addEnt(div);
            div.addEnt(table);

            result += base.render();

        } catch (Exception e) {
            result += StringAdapter.getStackTraceException(e);
        }
        return result;
    }    

    private static AbsEnt getChangeForm(Object primaryId,Object currentName) throws Exception {
        FabricRender fr = FabricRender.getInstance(new Project());
        FormOptionInterface fo = fr.getFormOption();
        fo.setHorisontal(Boolean.TRUE);
        fo.setButtonName("Изменить");
        fo.setAction("change");
        fo.setObject("PatientsTypes");
        fo.setNoValidateRights();
        Map<AbsEnt, String> inner = new LinkedHashMap();
        inner.put(fr.textInput("name", currentName, "Название"), "");
        inner.put(fr.hiddenInput("id", primaryId), "");
        AbsEnt se = fr.rightForm(inner, fo);
        return se;
    }
    
    private static AbsEnt getDeleteForm(Object primaryId) throws Exception {
        FabricRender fr = FabricRender.getInstance(new Project());
        FormOptionInterface fo = fr.getFormOption();
        fo.setHorisontal(Boolean.TRUE);
        fo.setButtonName("Удалить");
        fo.setAction("delete");
        fo.setObject("PatientsTypes");
        fo.setNoValidateRights();
        Map<AbsEnt, String> inner = new LinkedHashMap();
        inner.put(fr.hiddenInput("id", primaryId), "");
        AbsEnt se = fr.rightForm(inner, fo);
        return se;
    }
}
