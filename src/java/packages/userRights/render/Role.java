/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages.userRights.render;

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
import support.web.HrefOptionInterface;
import support.web.Parameter;
import support.web.entities.WebEnt;

/**
 *
 * @author Кот
 */
public class Role {   
        
    public static String showRoles(Map<String, Object> request, Map<String, Object> service) {
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
            fo.setObject("role");
            fo.setNoValidateRights();
            Map<AbsEnt, String> inner = new LinkedHashMap();
            inner.put(fr.textInput("name", "", "Название"), "");
            AbsEnt se = fr.rightForm(inner, fo);

            div = fr.div("float:left;width:100%", null);
            base.addEnt(div);
            div.addEnt(se);

            AbsEnt table = fr.table("1", "5", "0");
            List<Row> list = (List<Row>) service.get("roleList");
            for (Row role : list) {
                fr.tr(table,getShowConsistForm(role.get("role_id")) ,getChangeForm(role.get("role_id"),role.get("name")),getDeleteForm(role.get("role_id")));
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
    
    public static String showRolesConsist(Map<String, Object> request, Map<String, Object> service) {
        String result = "";
        try {
            FabricRender fr = FabricRender.getInstance(new Project());
            AbsEnt base = fr.div("float:left;width:100%", null);

            AbsEnt div = fr.div("float:left;width:100%", null);
            base.addEnt(div);

            if (StringAdapter.NotNull(service.get("error"))) {
                div.setValue(service.get("error"));
                div = fr.div("float:left;width:100%", null);
                base.addEnt(div);
            }else{
                //получить права
                List<Row> rights =(List<Row>) service.get("rights");
                Map<String,Object> riMap=fr.createComboMap(rights, "right_id","object_description","action_description");

                //получить права
                List<Row> roles =(List<Row>) service.get("roles");
                Map<String,Object> roMap=fr.createComboMap(roles, "role_id","name");

                div = fr.div("float:left;width:100%", null);
                base.addEnt(div);

                AbsEnt table = fr.table("1", "5", "0");
                List<Row> links = (List<Row>) service.get("links");
                Map<String,Object> liMap=fr.createComboMap(links, "right_id","right_id");
                
                for (Row ri: rights) {
                    String rightId=StringAdapter.getString(ri.get("right_id"));
                    fr.tr(table,ri.get("right_id"),riMap.get(rightId),getCheckBoxForm(service.get("role_id"), ri.get("right_id"), liMap.get(rightId)));
                }

                div.addEnt(table);
            }
            result += base.render();

        } catch (Exception e) {
            result += StringAdapter.getStackTraceException(e);
        }
        return result;
    }

    private static AbsEnt getChangeForm(Object primariId,Object currentName) throws Exception {
        FabricRender fr = FabricRender.getInstance(new Project());
        FormOptionInterface fo = fr.getFormOption();
        fo.setHorisontal(Boolean.TRUE);
        fo.setButtonName("Изменить");
        fo.setAction("change");
        fo.setObject("role");
        fo.setNoValidateRights();
        Map<AbsEnt, String> inner = new LinkedHashMap();
        inner.put(fr.textInput("name", currentName, "Название"), "");
        inner.put(fr.hiddenInput("role_id", primariId), "");
        AbsEnt se = fr.rightForm(inner, fo);
        return se;
    }
    
    
    private static AbsEnt getDeleteForm(Object primariId) throws Exception {
        FabricRender fr = FabricRender.getInstance(new Project());
        FormOptionInterface fo = fr.getFormOption();
        fo.setHorisontal(Boolean.TRUE);
        fo.setButtonName("Удалить");
        fo.setAction("delete");
        fo.setObject("role");
        fo.setNoValidateRights();
        Map<AbsEnt, String> inner = new LinkedHashMap();
        inner.put(fr.hiddenInput("role_id", primariId), "");
        AbsEnt se = fr.rightForm(inner, fo);
        return se;
    }

    
    private static AbsEnt getShowConsistForm(Object primariId) throws Exception{
        FabricRender fr = FabricRender.getInstance(new Project());
        HrefOptionInterface fo = fr.getHrefOption();
        fo.setAction("showRolesConsist");
        fo.setObject("role");
        fo.setName("Состав роли");
        fo.setNoValidateRights();
        List<Parameter> li=new ArrayList();
        li.add(new Parameter("role_id", primariId));
        AbsEnt se = fr.href(li, fo);
        return se;
    }
    
    
    private static AbsEnt getCheckBoxForm(Object roleId,Object rightId,Object realRight) throws Exception{
        Project pj=new Project();
        FabricRender fr = FabricRender.getInstance(new Project());
        String hrefParams="object=role&&action=showRolesConsist&&spec=change&&role_id="+roleId+"&&right_id="+rightId;
        AbsEnt se=fr.checkBox("right_id",realRight);
        se.setJs(se.getJs()+" onclick=\"return location.href =\'"+pj.getBaseLinkPath()+"?"+hrefParams+"\'\"");
        return se;
    }
}
