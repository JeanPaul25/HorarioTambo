/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.conexionDB;

/**
 *
 * @author Benito
 */
@WebServlet(name = "servletSolicitarPermiso", urlPatterns = {"/servletSolicitarPermiso"})
public class servletSolicitarPermiso extends HttpServlet {

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
        
        HttpSession sessionOK = request.getSession();  
        
        try {
            FileItemFactory file_factory = new DiskFileItemFactory();

            ServletFileUpload servlet_up = new ServletFileUpload(file_factory);

            List items = servlet_up.parseRequest(request);

            ArrayList array = new ArrayList<>();
            String imagen = null;

            for(int i=0;i<items.size();i++){
                FileItem item = (FileItem) items.get(i);
                if (item.isFormField()){                    
                    array.add(item.getString());   
                }else{                   
                    String[] formato = item.getName().split("\\.");
                    imagen = array.get(0).toString() + "-" + array.get(2).toString() + "." + formato[1];
                    File archivo_server = new File("F:\\2022 - 1\\Curso Integrador 1\\HorarioTambo\\HorarioTambo\\web\\Pruebas\\"+imagen);
                    item.write(archivo_server);
                }
            }
            
            PreparedStatement pstaPermiso = conexionDB.getConexion().prepareStatement("insert into permisos values(?,?,?,?,?,?)");
                pstaPermiso.setInt(1, 0);
                pstaPermiso.setString(2, array.get(0).toString());                            
                pstaPermiso.setString(3, array.get(1).toString());
                pstaPermiso.setString(4, array.get(3).toString());
                pstaPermiso.setInt(5, Integer.parseInt(array.get(2).toString()));
                pstaPermiso.setString(6, imagen);

            pstaPermiso.executeUpdate();  

            conexionDB.getConexion().close();
            
            request.getRequestDispatcher("pages/dashboardEmpleado.jsp?opcion=dash").forward(request, response);
            System.out.println("asdasd");
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
