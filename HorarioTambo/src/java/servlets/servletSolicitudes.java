/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.permisoBeans;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.conexionDB;

/**
 *
 * @author Benito
 */
@WebServlet(name = "servletSolicitudes", urlPatterns = {"/servletSolicitudes"})
public class servletSolicitudes extends HttpServlet {

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
        try {
            String idPermiso = request.getParameter("fPermiso").toString();
            PreparedStatement pstaSolicitud = conexionDB.getConexion().prepareStatement("select * from permisos");
            ResultSet rsPermisos = pstaSolicitud.executeQuery();

            ArrayList<permisoBeans> arrayPermisos = new ArrayList<>(); 
            ArrayList<permisoBeans> arrayPermisoRevisar = new ArrayList<>(); 
            
            while(rsPermisos.next()){
                permisoBeans permiso = new permisoBeans(rsPermisos.getInt(1),rsPermisos.getString(2),rsPermisos.getString(3),rsPermisos.getString(4),rsPermisos.getInt(5),rsPermisos.getString(6));
                arrayPermisos.add(permiso);
                if(rsPermisos.getInt(1) == Integer.parseInt(request.getParameter("fPermiso"))){
                    System.out.println("IGUALES: " + rsPermisos.getInt(1));
                    arrayPermisoRevisar.add(permiso);
                }
            }               
            conexionDB.getConexion().close();         

            request.setAttribute("permiso", arrayPermisoRevisar);
            request.setAttribute("listaPermisos", arrayPermisos);
            request.getRequestDispatcher("pages/solicitudes.jsp?opcion=soli").forward(request, response);                    
        } catch (Exception e) {
            System.out.println("Error Solicitar Permiso: " + e);
        }
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
