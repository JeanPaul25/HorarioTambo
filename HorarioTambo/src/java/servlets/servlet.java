package servlets;

import beans.*;
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
            case "solPer":
                String tipoP = request.getParameter("fTipo");
                String exp = request.getParameter("fExp");
                int idTurno = Integer.parseInt(request.getParameter("fTurno"));                
                String usuarioP = null;
                
                if(sessionOK.getAttribute("usuarioNA") != null){
                        usuarioP = sessionOK.getAttribute("id").toString();
                    }
                System.out.println(usuarioP + " --- " + tipoP + " --- " + exp + " --- " + idTurno);
                try {
                    PreparedStatement pstaPermiso = conexionDB.getConexion().prepareStatement("insert into permisos values(?,?,?,?,?)");
                    pstaPermiso.setInt(1, 0);
                    pstaPermiso.setString(2, usuarioP);                            
                    pstaPermiso.setString(3, tipoP);
                    pstaPermiso.setString(4, exp);
                    pstaPermiso.setInt(5, idTurno);
                    
                    pstaPermiso.executeUpdate();  
                    
                    conexionDB.getConexion().close();
                    
                    request.getRequestDispatcher("servlet?opcion=dashboard").forward(request, response);                    
                } catch (Exception e) {
                    System.out.println("Error Solicitar Permiso: " + e);
                }
                break;
            case "soli":
                try {
                    PreparedStatement pstaSoli = conexionDB.getConexion().prepareStatement("select * from permisos");
                    ResultSet rsPermisos = pstaSoli.executeQuery();
                    
                    ArrayList<permisoBeans> arrayPermisos = new ArrayList<>(); 
                    while(rsPermisos.next()){
                        permisoBeans permisosB = new permisoBeans(rsPermisos.getInt(1),rsPermisos.getString(2),rsPermisos.getString(3),rsPermisos.getString(4),rsPermisos.getInt(5));
                        arrayPermisos.add(permisosB);
                    }               
                    conexionDB.getConexion().close();         
                     
                    request.setAttribute("listaPermisos", arrayPermisos);
                    request.getRequestDispatcher("pages/solicitudes.jsp?opcion=soli").forward(request, response);                    
                } catch (Exception e) {
                    System.out.println("Error Solicitar Permiso: " + e);
                }
                break; 
            case "turno":
                try {
                    String usuarioName = null;
                    PreparedStatement pstaTurnoU = conexionDB.getConexion().prepareStatement("select * from turnos where idTurno=?");
                    pstaTurnoU.setInt(1, Integer.parseInt(request.getParameter("fTurnoT")));
                    ResultSet rsTurnoF = pstaTurnoU.executeQuery();
                    ArrayList<turnoBeans> arrayTurno = new ArrayList<>(); 
                    
                    while(rsTurnoF.next()){
                        turnoBeans turnosT = new turnoBeans(rsTurnoF.getInt(1), rsTurnoF.getInt(2), rsTurnoF.getInt(3), rsTurnoF.getInt(4));
                        arrayTurno.add(turnosT);
                    }       
                    
                    conexionDB.getConexion().close(); 
                    
                    PreparedStatement pstaUsuarioT = conexionDB.getConexion().prepareStatement("select * from usuarios where idUsuario=?");
                    System.out.println("USUARIO: " + request.getParameter("fUsuarioT"));
                    pstaUsuarioT.setString(1, request.getParameter("fUsuarioT"));
                    ResultSet rsUsuarioT = pstaUsuarioT.executeQuery();
                       
                    while(rsUsuarioT.next()){
                        usuarioName = rsUsuarioT.getString(5) + " " + rsUsuarioT.getString(6);
                    }                                  
                    conexionDB.getConexion().close();
                     
                    request.setAttribute("opcion", "soli");
                    request.setAttribute("operacion", "soliTurno");
                    
                    request.setAttribute("listaTurnos", arrayTurno);
                    request.setAttribute("usuarioName", usuarioName);
                    request.setAttribute("tipo", request.getParameter("fTipoT"));
                    request.setAttribute("exp", request.getParameter("fExpT"));
                    request.setAttribute("permiso", request.getParameter("fPermisoT"));
                    
                    
                        System.out.println("Final Servlet Turno : " + request.getAttribute("opcion"));
                    request.getRequestDispatcher("pages/solicitudes.jsp").forward(request, response);
                } catch (Exception e) {
                    System.out.println("Error Turno: " + e);
                }
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
