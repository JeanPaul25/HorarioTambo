package servlets;

import beans.*;
import java.io.File;
import utils.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


@WebServlet(name = "servlet", urlPatterns = {"/servlet"})
public class servlet extends HttpServlet {

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
        
        HttpSession sessionOK = request.getSession();  
        
        String opc = request.getParameter("opcion");        
        System.out.println("Conexión Servlet: " + opc);
        
        String us = null;
        String tipo = null;
        ArrayList<turnoBeans> lista = new ArrayList<>();   
        
        switch (opc){
            case "login":
                try {
                    String usuario = request.getParameter("fUsuario");
                    String password = request.getParameter("fPassword");
                    
                    sessionOK.setAttribute("id", usuario);
                    
                    PreparedStatement psta = conexionDB.getConexion().prepareStatement("select * from usuarios where idUsuario = ? and contraseña = ?");
                    psta.setString(1, usuario);
                    psta.setString(2, password);                    
                    ResultSet rs = psta.executeQuery();
                                        
                    if(rs.next()){                   
                        us = rs.getString(5).toString() + " " + rs.getString(6).toString();
                        tipo = rs.getString(3).toString();
                        conexionDB.getConexion().close();

                        PreparedStatement psta2 = conexionDB.getConexion().prepareStatement("select * from semanas where idUsuario = ?");
                        psta2.setString(1, usuario);                            
                        ResultSet rs2 = psta2.executeQuery();

                        while(rs2.next()){                            
                            int idSemana = rs2.getInt(1);
                            int diaI = rs2.getInt(2);
                            int diaF = rs2.getInt(3);
                            int mes = rs2.getInt(4);
                            conexionDB.getConexion().close();

                            PreparedStatement psta3 = conexionDB.getConexion().prepareStatement("select * from turnos where idSemana = ?");
                            psta3.setInt(1, idSemana);
                            ResultSet rs3 = psta3.executeQuery();

                            while(rs3.next()){       
                                turnoBeans turnos = new turnoBeans(rs3.getInt(1), rs3.getInt(2), rs3.getInt(3), rs3.getInt(4));
                                lista.add(turnos);
                            }

                            conexionDB.getConexion().close();
                            
                            sessionOK.setAttribute("tipo", tipo);
                            sessionOK.setAttribute("usuarioNA",us); 
                            sessionOK.setAttribute("lista", lista);
                            
                            request.getRequestDispatcher("pages/dashboardEmpleado.jsp?opcion=dash").forward(request, response);
                        }
                    }else{
                        String msg = "Usuario o Password Incorrecto";
                        request.setAttribute("msg", msg);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    System.out.println("Error Login: " + e);
                }
                break;
            case "logout":
                HttpSession sesionOK = request.getSession();
                sesionOK.invalidate();
                request.setAttribute("msg", "Acaba de cerrar sesión");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "dashboard":  
                request.getRequestDispatcher("pages/dashboardEmpleado.jsp?opcion=dash").forward(request, response);
                break;
        }        
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
