/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import api.ControllerAbstract;
import controller.Demo;
import controller.Role;
import controller.User;
import controller.Treatment;
import controller.PatientsTypes;
import controller.TalkResult;
import entity.Rights;
import java.io.File;
import support.db.Connect;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import api.FabricRender;
import settings.Project;
import settings.Sqlset;
import support.DateAdapter;
import support.JarScan;
import support.StringAdapter;
import support.db.Creator;
import support.db.Dao;
import support.db.Persistence;
import support.logic.RightObject;
import support.web.AbsEnt;
import support.web.HrefOptionInterface;
import support.web.Parameter;
import support.web.webclient.WebClientImpl;

/**
 *
 * @author kot
 */
public class index extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\"><link rel=\"stylesheet\" type=\"text/css\" href=\"css/index.css\">\n" +
"	<script src=\"js/SIPml-api.js?svn=222\" type=\"text/javascript\"> </script><script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-latest.min.js\"></script>");
            out.println("<title>Servlet index</title>");
            out.println("</head>");
            out.println("<body>");
            try{
            Connection con = Connect.createConnection(new Sqlset());
                try {
                    Creator.saveNewtables(con,index.class);
                    WebClientImpl wc= new WebClientImpl(request,new Project(),new Sqlset());
                    Map<String,Object> requestParam=wc.getInnerRequest();
                    String result="";
                    result+=getHtmlMenu();
                    result+="<div style='float:left;width:100%;margin-top:50px;'>";
                    if(StringAdapter.NotNull(requestParam.get("logout"))){
                        request.getSession().removeAttribute("key");
                    }
                    if(StringAdapter.isNull(request.getSession().getAttribute("key"))){
                       response.sendRedirect("/unit/auth");
                    }
                    Dao dao=Dao.getInstance(con);
                    
                    Persistence persistence=Persistence.getInstance();
                    RightObject listRights=persistence.createRightsFromJar(index.class);
                    for(support.logic.Right jarRight:listRights.getRights()){
                        entity.Rights right=new entity.Rights();
                        right.object=jarRight.getObject();
                        right.action=jarRight.getAction();
                        if(dao.findByValues(right).isEmpty()){
                            out.println(right.rightId);
                            right.addDate=new Date();
                            dao.save(right);
                            right.addDate=null;
                        }
                    }
                    
                    ControllerAbstract controller=null;
                    
                    
                    RightObject userRight=listRights;
                    
                    if(StringAdapter.NotNull(wc.getObject(),wc.getAction())){
                        String realName="controller."+StringAdapter.ucFirst(wc.getObject());
                        String realMethod=wc.getAction();
                    
                        if(userRight.isRight(realName, realMethod)){
  
                            Class contr=Class.forName(realName);
                            Object obj = contr.newInstance();
                            controller=(ControllerAbstract) obj;
                            controller.setDao(dao);
                            controller.setRequest(requestParam);
                            controller.setSession(wc.getInnerSession());

                            Method method = contr.getMethod(realMethod); 
                            method.invoke(obj); 

                         }
                    }
                    if(controller!=null){
                        result+=controller.getResult()+"</div>";
                    }else{
                        result+="</div>";
                    }
                
                    out.println(result);
                    if(con!=null){
                        if(!con.isClosed()){
                           con.close();
                        }
                    }
                } catch (Exception ex) {
                    out.println(StringAdapter.getStackTraceException(ex));
                    if(con!=null){
                        if(!con.isClosed()){
                           con.close();
                        }
                    }
                }
            }catch(Exception e){
                out.println(StringAdapter.getStackTraceException(e));
            }
            
            out.println("</body>");
            out.println("</html>");
        }

    }
    
    private String getHtmlMenu() throws Exception{
        FabricRender fr = FabricRender.getInstance(new Project());

        AbsEnt base=fr.div("float:left;width:100%", null);
            
        AbsEnt div= fr.div("float:left;width:100%", null);
        base.addEnt(div);
        
        List<Parameter> list = new ArrayList();
        HrefOptionInterface demo=fr.getHrefOption();
        demo.setObject("demo");
        demo.setAction("setUserDemo");
        demo.setName("Демо");
        demo.setNoValidateRights();
        
        div.addEnt(fr.href(list, demo).setCss("knopka"));
        
        HrefOptionInterface users=fr.getHrefOption();
        users.setObject("user");
        users.setAction("showUsers");
        users.setNoValidateRights();
        users.setName("Пользователи");
        div.addEnt(fr.href(list, users).setCss("knopka"));
        
        List<Parameter> list2 = new ArrayList();
        HrefOptionInterface roles=fr.getHrefOption();
        roles.setObject("role");
        roles.setAction("showRoles");
        roles.setNoValidateRights();
        roles.setName("Роли");
        div.addEnt(fr.href(list2, roles).setCss("knopka"));

        List<Parameter> list3 = new ArrayList();
        HrefOptionInterface treatments=fr.getHrefOption();
        treatments.setObject("Treatment");
        treatments.setAction("showTreatments");
        treatments.setNoValidateRights();
        treatments.setName("Причины обращения");
        div.addEnt(fr.href(list3, treatments).setCss("knopka"));
      
        List<Parameter> list4 = new ArrayList();
        HrefOptionInterface patientstypes=fr.getHrefOption();
        patientstypes.setObject("PatientsTypes");
        patientstypes.setAction("showPatientsTypes");
        patientstypes.setNoValidateRights();
        patientstypes.setName("Тип пациента");
        div.addEnt(fr.href(list4, patientstypes).setCss("knopka"));

        List<Parameter> list5 = new ArrayList();
        HrefOptionInterface talkresult=fr.getHrefOption();
        talkresult.setObject("TalkResult");
        talkresult.setAction("showTalkResult");
        talkresult.setNoValidateRights();
        talkresult.setName("Итог разговора");
        div.addEnt(fr.href(list5, talkresult).setCss("knopka"));

        List<Parameter> endlist = new ArrayList();
        list2.add(new Parameter("logout", "logout"));
        HrefOptionInterface logout=fr.getHrefOption();
        logout.setNoValidateRights();
        logout.setName("Выход");
        div.addEnt(fr.href(list2, logout).setCss("knopka"));
        
        


        return base.render();
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
