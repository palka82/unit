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
import support.DateAdapter;
import support.Security;
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
 * @author Кот
 */
@Controller
public class User extends ControllerAbstract{

    public User(){
    }

    @Right
    public void showUsers() {
        String result = "";
        try {
            entity.User us = new entity.User();            
            Dao dao=getDao();
            
            if (StringAdapter.NotNull(getRequest().get("spec"))) {
                if (StringAdapter.NotNull(getRequest().get("user_id"))) {
                    entity.User user = new entity.User();
                    user.userId = Long.valueOf(StringAdapter.getString(getRequest().get("user_id")));
                    List<Object> users = dao.findByPrimary(user);
                    if (users.isEmpty()) {
                        addResponce("error", "Не существует пользователя с данными Id");
                    } else {
                        user=(entity.User) users.get(0);
                        packages.userRights.entity.Role role = new packages.userRights.entity.Role();
                        String roleId=StringAdapter.getString(getRequest().get("role_id"));
                        if(StringAdapter.NotNull(roleId)){
                            role.roleId = Long.valueOf(roleId);
                            if (dao.findByPrimary(role).isEmpty()) {
                                addResponce("error", "Не существует роли с данными Id");
                            }else{
                                user.roleId=Long.valueOf(roleId);
                            }
                        }else{
                            user.roleId=null;
                        }
                        dao.update(user);
                    }
                } else {
                    addResponce("error", "не передана роль или пользователь");
                }
            }
            packages.userRights.entity.Role role = new packages.userRights.entity.Role();
            List<Row> roles = dao.find(role);
            addResponce("roleList", roles);
            
            List<Row> res=dao.find(us);
            addResponce("userList",res);
            setResult(render.User.showUsers(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(result=StringAdapter.getStackTraceException(e));
        }
    }
    
    @Right
     public void addUser() {
        String result = "";
        try {
            String login=StringAdapter.getString(getRequest().get("login"));
            String phonenumber=StringAdapter.getString(getRequest().get("phonenumber"));
            String phonepass=StringAdapter.getString(getRequest().get("phonepass"));
            String username=StringAdapter.getString(getRequest().get("name"));
            String usersurname=StringAdapter.getString(getRequest().get("surname"));
            if(StringAdapter.isNull(login)){
                addResponce("error", "передайте имя пользователя");
            }else{
                String addDate=DateAdapter.getCurrentDateInMysql();
                String query= "insert into users set login='"+login+"'"
                        + " ,add_date='"+addDate+"'"
                        + ",password='"+Security.md5("0000")+"' , phonenumber='"+phonenumber+"'"
                        + ",phonepass='"+phonepass+"'"
                        + ",name='"+ username +"'"
                        + ",surname='"+ usersurname + "'";
                QueryExecutor qe=ExecutorFabric.getExecutor(getDao().getConnection(), query, DbTypes.MySQL);
                qe.update();
                if(!qe.getError().isEmpty()){
                    addResponce("error", StringAdapter.getStringFromList(qe.getError()));
                }
            }
            List<Row> res=getDao().find(new entity.User());
            addResponce("userList",res);
            setResult(render.User.showUsers(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
     
     public void showUsersJSON() {
        String result = "";
        
        try {
            List<Row> res=getDao().find(new entity.User());
            addResponce("userList",res);
            setResult(render.User.showUsersJson(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
    }
     
     public void auth() {
        String result = "";
        try {
            if(StringAdapter.NotNull(getRequest().get("submit"))){
                if(authEd()){
                    Row user=(Row) getResponce().get("user");
                    addSession("key", user.get("user_id"));
                    addSession("name", user.get("name")+" "+user.get("surname"));
                }
            }
            setResult(render.User.auth(getRequest(), getResponce()));
        } catch (Exception e) {
            setResult(StringAdapter.getStackTraceException(e));
        }
       
    }
    
     
     private boolean authEd() throws Exception{
        boolean result=false;
        if(StringAdapter.NotNull(getRequest().get("login"),getRequest().get("password"))){
           String login= getRequest().get("login").toString();
           String password= getRequest().get("password").toString();
            
           String query= "select user_id, name, surname from users where login='"+login+"' and "
                   + "password='"+Security.md5(password)+"';"; 
            QueryExecutor qe=ExecutorFabric.getExecutor(getDao().getConnection(), query, DbTypes.MySQL);
            qe.select();
            if(qe.getError().isEmpty()){
                if(qe.getResultList().isEmpty()){
                    addResponce("error","Не найдено пользователя с таким логином и паролем");
                }else{
                    addResponce("user", qe.getResultList().get(0));
                    result=true;
                }
            }else{
                addResponce("error",StringAdapter.getStringFromList(qe.getError()));
            }
        }else{
            addResponce("error", "Передайте логин и пароль");
        }
        return result;
    }

}
