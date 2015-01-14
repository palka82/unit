/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import controller.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import settings.Project;
import settings.Sqlset;
import support.StringAdapter;
import support.db.Connect;
import support.db.Dao;
import support.web.webclient.WebClientImpl;

/**
 *
 * @author Кот
 */
public class auth extends HttpServlet {

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
            out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/auth.css\">\n" +
"	<script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-latest.min.js\"></script>");
            out.println("<title>Servlet auth</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                Connection con = Connect.createConnection(new Sqlset());
                try {

                    WebClientImpl wc= new WebClientImpl(request,new Project(),new Sqlset());
                    Map<String,Object> requestParam=wc.getInnerRequest();
                    
                    if(StringAdapter.NotNull(request.getSession().getAttribute("key"))){
                        response.sendRedirect("/unit/index");
                    }
                    
                    
                    
                    
                    
                    User controller=new User();
                    controller.setDao(Dao.getInstance(con));
                    controller.setRequest(requestParam);
                    controller.auth();
                    
                   
                    
                    
                    
                    if(StringAdapter.NotNull(controller.getSession().get("key"))){
                        request.getSession().setAttribute("key", controller.getSession().get("key"));
                        request.getSession().setAttribute("name", controller.getSession().get("name"));
                        response.sendRedirect("/unit/index");
                    }else{
                        out.println(controller.getResult());
                    }
                    if (con != null) {
                        if (!con.isClosed()) {
                            con.close();
                        }
                    }
                } catch (Exception e) {
                    out.println(StringAdapter.getStackTraceException(e));
                    if (con != null) {
                        if (!con.isClosed()) {
                            con.close();
                        }
                    }
                }
            } catch (Exception ex) {
                out.println(StringAdapter.getStackTraceException(ex));
            }
            out.println("</body>");
            out.println("</html>");
        }
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
