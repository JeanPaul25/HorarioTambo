<%@page import="beans.empleadoBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title> Empleados </title>
        <link rel="stylesheet" href="../css/bootstrap.css"/>
        <link rel="stylesheet" href="../css/styles.css"/>   
    </head>
    <body>        
        <%@include file="../templates/navbar.jsp"%>
        
        <%
            ArrayList<empleadoBeans> arrayEmpleados = (ArrayList<empleadoBeans>)request.getAttribute("listaEmpleados");
            %>
            
            <main>
                
                <table class="table ">
                    <thead class=" thead-dark">
                        <tr>
                            <th> IdEmpleado</th>
                            <th> DNI </th>
                            <th> Tipo de Empleado </th>
                            <th> Contraseña </th>
                            <th> Nombres </th>
                            <th> Apellidos </th>
                            <th> HORARIO   </th>
                        </tr>
                    </thead>
            <%
                for (int i = 0; i < arrayEmpleados.size(); i++) {
                    empleadoBeans empleado = arrayEmpleados.get(i);
                    
                %>
                
                    <tr>
                        <td> <%=empleado.getIdEmpleado()%> </td>
                        <td> <%=empleado.getDni()%> </td>
                        
                        <%
                            String tipoEmpleado = null;
                            if(empleado.getTipo().equals("E")){
                                tipoEmpleado = "Empleado";
                            }else{
                                tipoEmpleado = "Administrador";
                            }
                            %>
                            
                        <td> <%=tipoEmpleado%> </td>
                        <td> <%=empleado.getPassword()%> </td>
                        <td> <%=empleado.getNombres()%> </td>
                        <td> <%=empleado.getApellidos()%> </td>
                        <td>
                            <a href="servletConsultarHorario?idUsuario=<%=empleado.getIdEmpleado()%>">
                                <button type="button" class="btn btn-primary"> 
                                    <img src="images/Horario.png" alt="Horario" style="height: 3vh; width: auto"> 
                                </button> 
                            </a>                            
                        </td>
                    </tr>
                
                    
            <%
                }
                %>
                
                </table>
            </main>
    </body>
</html>
