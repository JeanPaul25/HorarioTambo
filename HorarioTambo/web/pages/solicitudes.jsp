<%@page import="beans.turnoBeans"%>
<%@page import="beans.permisoBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Solicitudes de Permiso </title>
        <link rel="stylesheet" href="../css/bootstrap.css"/>
        <link rel="stylesheet" href="../css/styles.css"/>   
    </head>
    <body>        
        <%@include file="../templates/navbar.jsp"%>
        
        <div class="container d-flex my-5"> 
            <div class="col-2" style="overflow: scroll; height: 75vh; overflow-x: hidden"> 
            <table> 
              
    <%!
        String ceros(int idPermiso){
            String ceros = null;
            if(idPermiso <10){
                ceros = "000";
            }else if(idPermiso <100){
                ceros = "00";
            }else{
                ceros = "0";
            }
        
            return "P"+ceros;
        }
        %>
                
    <%
        ArrayList<permisoBeans> arrayPermisos = (ArrayList<permisoBeans>)request.getAttribute("listaPermisos");
        for(int i = 0; i < arrayPermisos.size(); i++){
            permisoBeans permiso = arrayPermisos.get(i);
        %>
    
                <tr>
                    <td class="py-3">
                        <form action="servletSolicitudes" method="post">                               
                            <label> Usuario: <%=permiso.getIdUsuario()%> </label>
                            <label> Permiso: <%=ceros(permiso.getIdPermiso())%><%=permiso.getIdPermiso()%> </label>
                            <input type="hidden" name="fPermiso" value="<%=permiso.getIdPermiso()%>">
                            <input type="hidden" name="fIdTurno" value="<%=permiso.getIdTurno()%>">
                            <input type="hidden" name="listaPermisos" value="<%=arrayPermisos%>">
                            <input class="form-control btn btn-primary" type="submit" value="Consultar">  
                        </form>    
                    </td>
                </tr>  
                            
    <%
        }
        %>
            </table>
        </div>   
            
        <%
            if(request.getAttribute("permiso") == null){
                System.out.println("asdasds2123d");
            %>
            
            
            
        <%                
            }else{
                System.out.println("123123asdsd");
                ArrayList<permisoBeans> arrayPermisoRevisar = (ArrayList<permisoBeans>)request.getAttribute("permiso");
                for (int i = 0; i < arrayPermisoRevisar.size(); i++) {
                    permisoBeans permisoRevisar = arrayPermisoRevisar.get(i);
            %>
            
        <div class="col-8"> 
            <table class="table tableSoli">
                <tr class="form-control">
                    <td> Empleado: </td>
                    <td> <%=permisoRevisar.getIdUsuario()%> </td>
                
                    <td> Tipo: </td>
                    <td> <%=permisoRevisar.getTipo()%> </td>
                </tr>
                <tr class="form-control">
                    <td> Explicación: </td>
                    <td colspan="3"> <%=permisoRevisar.getExp()%> </td>
                </tr>
                <tr class="form-control-file" style="height: 75%">
                    <td> Prueba: </td>
                    <td colspan="3"> <img src="Pruebas/<%=permisoRevisar.getPrueba()%>" alt="Prueba" class="img-fluid" style="height: 100%; width: auto"> </td>
                </tr>
            </table>
        </div> 
        <div class="col-2"> 
            <table>
                <tr>                    
                    <td> 
                        <label> Turno </label>
                    <%
                        String dias[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado"};
                        
                        ArrayList<turnoBeans> lista = (ArrayList<turnoBeans>)request.getAttribute("listaTurnos");
                        int dia = 0, horaI = 0, horaF = 0, h = 0;                        
                        
                        int horario[] = new int[16];
                        for(int d = 0; d < lista.size(); d++){
                            turnoBeans turnos = lista.get(i);
                            
                            horaI = turnos.getHorarioI();
                            horaF = turnos.getHorarioF();
                            dia = turnos.getDia();
                            System.out.println("OOOOOOOOOOOOO: " + horaI + " " + horaF + " " + dia);
                            while(horaI <= horaF){
                                horario[h] = horaI;
                                horaI++;
                                h++;
                            }
                        }
                        %>
                        <div class="container-fluid my-5"> 
                            <table class="bg-light w-100 text-center table-bordered">
                                <thead>
                                    <tr class="bgpurple text-light">
                                        <th> HORA </th>
                                        <th> <%=dias[dia]%> </th>
                                    </tr>
                                </thead>
                                     
                    <%              
                        int horaCom = 0;
                        for(int t=0 ; t<=15 ; t++){                            
                        %>
                        
                        <tr> 
                            <td class="font-weight-bold bgpurple text-light"> <%=t+7%> </td>
                            
                    <%
                            if(horario[horaCom] == t+7){
                                horaCom++;
                                
                        %>
                        
                            <td class="bgyellow"> </td>                        
                        
                    <%
                            }else{
                        %>
                        
                            <td class="bg-info">  </td>
                        
                    <%                        
                        }
                        %>
                        
                        </tr>
                        
                    <%                         
                        }        
                        %>                       
                        
                            </table>                                           
                        </div>
                    </td>
                </tr>
            </table>
            <form action="#">
                <table>
                    <tr>
                        <td> <input type="button" value="Rechazar"> </td>
                        <td> <input type="button" value="Aceptar"> </td>
                    </tr>
                </table>
            </form>
        </div>       
            
        <%
                }
            }
            %>
        
        </div>        
    </body>
</html>
