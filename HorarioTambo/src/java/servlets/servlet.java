package servlets;

import beans.turnoBeans;
import utils.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  


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
        
        switch (opc){
            case "login":
                try {
                    int usuario = Integer.parseInt(request.getParameter("fUsuario"));
                    String password = request.getParameter("fPassword");
                    
                    PreparedStatement psta = conexionDB.getConexion().prepareStatement("select * from empleado where idEmpleado = ? and contraseña = ?");
                    psta.setInt(1, usuario);
                    psta.setString(2, password);                    
                    ResultSet rs = psta.executeQuery();
                                        
                    if(rs.next()){                   
                        String us = rs.getString(2).toString() + " " + rs.getString(3).toString();
                        conexionDB.getConexion().close();

                        PreparedStatement psta2 = conexionDB.getConexion().prepareStatement("select * from semana where idEmpleado = ?");
                        psta2.setInt(1, usuario);                            
                        ResultSet rs2 = psta2.executeQuery();

                        while(rs2.next()){                            
                            int idSemana = rs2.getInt(1);
                            int diaI = rs2.getInt(2);
                            int diaF = rs2.getInt(3);
                            int mes = rs2.getInt(4);
                            conexionDB.getConexion().close();

                            PreparedStatement psta3 = conexionDB.getConexion().prepareStatement("select * from turno where idSemana = ?");
                            psta3.setInt(1, idSemana);
                            ResultSet rs3 = psta3.executeQuery();

                            ArrayList<turnoBeans> lista = new ArrayList<>();                    

                            while(rs3.next()){       
                                turnoBeans turnos = new turnoBeans(rs3.getInt(1), rs3.getInt(2), rs3.getInt(3));

                                lista.add(turnos);
                            }

                            conexionDB.getConexion().close();

                            request.setAttribute("lista", lista);   

                            sessionOK.setAttribute("usuarioNA",us);  

                            request.getRequestDispatcher("pages/dashboardEmpleado.jsp?opcion=dashEmp").forward(request, response);
                        }
                    }else{
                        String msg = "Usuario o Password Incorrecto";
                        request.setAttribute("msg", msg);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                } catch (IOException | NumberFormatException | SQLException | ServletException e) {
                    System.out.println("Error Login: " + e);
                }
                break;
            case "logout":
                HttpSession sesionOK = request.getSession();
                sesionOK.invalidate();

                request.setAttribute("msg", "Acaba de cerrar sesión");
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
