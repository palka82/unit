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
 * @author Кот
 */
public class User {

    public static String showUsers(Map<String, Object> request, Map<String, Object> service) {
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
            fo.setAction("addUser");
            fo.setObject("User");
            fo.setNoValidateRights();
            Map<AbsEnt, String> inner = new LinkedHashMap();
            inner.put(fr.textInput("login", "", "Логин"), "");
            inner.put(fr.textInput("name", "", "Имя"), "");
            inner.put(fr.textInput("surname", "", "Фамилия"), "");
            inner.put(fr.textInput("phonenumber", "", "Номер телефона"), "");
            inner.put(fr.textInput("phonepass", "", "Пароль к телефону"), "");
            AbsEnt se = fr.rightForm(inner, fo);

            div = fr.div("float:left;width:100%", null);
            div.addEnt(se);
            base.addEnt(div);
            

            List<Row> roles = (List<Row>) service.get("roleList");
            Map<String, Object> mapRole = fr.createComboMap(roles, "role_id", "name");

            AbsEnt table = fr.table("1", "5", "0");
            List<Row> list = (List<Row>) service.get("userList");
            for (Row user : list) {
                fr.tr(table, user.get("user_id"), user.get("login"), user.get("name") + " " + user.get("surname"), user.get("phonepass"), user.get("phonenumber"), getRole(user.get("role_id"), mapRole, user.get("user_id")));
            }

            div = fr.div("float:left;width:100%", null);
            div.addEnt(table);
            base.addEnt(div);
            

            Project pj = new Project();
            result += base.render()+("<script type=\"text/javascript\">function sendChange(usId) {"
                    + "var path='"+pj.getBaseLinkPath()+"?object=user&&action=showUsers&&spec=changeRole&&user_id='+usId;"
                    + "var roleId=$(\"#\"+usId+\"\").val();"
                    + "path=path+'&&role_id='+roleId;"
                    + "return location.href =path;"
                    + "};"
                    + "</script>");

        } catch (Exception e) {
            result += StringAdapter.getStackTraceException(e);
        }
        return result;
    }

    public static String showUsersJson(Map<String, Object> request, Map<String, Object> service) {
        String result = "";
        List<Row> list = (List<Row>) service.get("userList");
        List<Map<String, Object>> lists = new ArrayList();
        for (Row rw : list) {
            Map<String, Object> map = new HashMap();
            map.put("user_id", rw.get("user_id"));
            map.put("login", rw.get("login"));
            map.put("phonepass", rw.get("phonepass"));
            map.put("phonenumber", rw.get("phonenumber"));
            lists.add(map);
        }
        Gson gs = new Gson();
        result = gs.toJson(lists);
        return result;
    }

    public static String auth(Map<String, Object> request, Map<String, Object> service) throws Exception {
        String result = "";
        FabricRender fr = FabricRender.getInstance(new Project());
        AbsEnt form = WebEnt.getEnt(WebEnt.Type.FORM).setId("slick-login").setAttribute(EnumAttrType.action, "/unit/auth");
        form.setAttribute(EnumAttrType.method, "POST");
        if (StringAdapter.NotNull(service.get("error"))) {
            form.addEnt(fr.div("", service.get("error")).setCss("error"));
        }
        form.addEnt(fr.label("login", "Логин", null, null));
        form.addEnt(fr.textInput("login", request.get("login"), "Логин").setCss("placeholder"));
        form.addEnt(fr.label("password", "Пароль:", null, null));
        form.addEnt(fr.passInput("password", "", "Пароль").setCss("placeholder"));
        form.addEnt(WebEnt.getEnt(WebEnt.Type.INPUT).setAttribute(EnumAttrType.type, "submit").setValue("ВОЙТИ").setAttribute(EnumAttrType.name, "submit"));
        result = form.render();
        return result;
    }

    private static AbsEnt getRole(Object roleId, Map<String, Object> mapRole, Object userId) throws Exception {
        FabricRender fr = FabricRender.getInstance(new Project());
        AbsEnt select;
        select = WebEnt.getEnt(WebEnt.Type.SELECT);
        select.setAttribute(EnumAttrType.name, "role_id");
        mapRole.put("", "Не выбрано");
        for (String st : mapRole.keySet()) {
            AbsEnt option = WebEnt.getEnt(WebEnt.Type.OPTION);
            option.setValue(st);
            AbsEnt txt = WebEnt.getEnt(WebEnt.Type.TXT);
            txt.setValue(mapRole.get(st));
            option.addEnt(txt);
            if (StringAdapter.getString(roleId).equals(st)) {
                option.setJs("selected");
            }
            select.addEnt(option);
        }
        String usId = StringAdapter.getString(userId);
        select.setId(usId);
        select.setJs(select.getJs() + " onchange=\"sendChange(" + usId + ")\"");
        return select;

    }

}
