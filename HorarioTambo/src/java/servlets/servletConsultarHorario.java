/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.turnoBeans;
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
@WebServlet(name = "servletConsultarHorario", urlPatterns = {"/servletConsultarHorario"})
public class servletConsultarHorario extends HttpServlet {

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
        
        System.out.println("Conexión Servlet Consultar Horario");
        
        ArrayList<turnoBeans> lista = new ArrayList<>();   
        try {
            String idUsuario = request.getParameter("idUsuario");
            

            PreparedStatement pstaSemanas = conexionDB.getConexion().prepareStatement("select * from semanas where idUsuario = ?");
            pstaSemanas.setString(1, idUsuario);                            
            ResultSet rs2 = pstaSemanas.executeQuery();

            while(rs2.next()){                            
                int idSemana = rs2.getInt(1);
                int diaI = rs2.getInt(2);
                int diaF = rs2.getInt(3);
                int mes = rs2.getInt(4);
                conexionDB.getConexion().close();

                PreparedStatement pstaTurnos = conexionDB.getConexion().prepareStatement("select * from turnos where idSemana = ?");
                pstaTurnos.setInt(1, idSemana);
                ResultSet rs3 = pstaTurnos.executeQuery();

                while(rs3.next()){       
                    turnoBeans turnos = new turnoBeans(rs3.getInt(1), rs3.getInt(2), rs3.getInt(3), rs3.getInt(4));
                    lista.add(turnos);
                }

                conexionDB.getConexion().close();
                
                request.setAttribute("listaTurnos", lista);
                request.setAttribute("idUsuario", idUsuario);

                request.getRequestDispatcher("pages/horarioEmpleado.jsp?opcion=horarioEmpleado").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error Login: " + e);
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
