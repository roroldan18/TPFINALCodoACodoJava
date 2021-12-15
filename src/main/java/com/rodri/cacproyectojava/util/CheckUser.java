
package com.rodri.cacproyectojava.util;

import com.rodri.cacproyectojava.persistence.BDConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet( name="checkuser", urlPatterns = {"/checkuser"} )
public class CheckUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                        
            BDConnection base = new BDConnection();
            
            String query = "SELECT * FROM usuarios WHERE usuario = " + "'" + request.getParameter("inputEmail") + "'"
                    + " and clave = " + "'"+ request.getParameter("inputPassword")+ "'";

            
            ResultSet rs = base.consultaSQL(query);
            

            while(rs.next()){
                out.println(rs.getString("usuario"));
                
                //out.println(rs.getString("clave"));
                out.println(rs.getString("nombreyapellido")+ "<br>" );
            }
            
            
                        
            if (rs.first()==false){
                out.println("No hay usuarios que coincidan con la busqueda");
                out.println("<h1>Proyecto: " + request.getContextPath() + "</h1>");
                out.println("<h1>El usuario es: " + request.getParameter("inputEmail") + "</h1>");            
            }                    
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
