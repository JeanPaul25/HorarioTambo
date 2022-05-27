<%@page import="beans.permisoBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Solicitudes de Permiso </title>
        <link rel="stylesheet" href="../css/bootstrap.css"/>
        <link rel="stylesheet" href="../css/styles.css"/>   
    </head>
    <body>        
        <%@include file="../templates/navbar.jsp"%>
        
        <div class="container d-flex bg-info my-5"> 
            <div class="col-2 bg-primary"> 
            <table class="bg-danger"> 
                 
    <%
        ArrayList<permisoBeans> arrayPermisos = (ArrayList<permisoBeans>)request.getAttribute("listaPermisos");
           
        for(int i = 0; i < arrayPermisos.size(); i++){
            permisoBeans permiso = arrayPermisos.get(i);
            System.out.println(permiso.getIdPermiso() + "-" + permiso.getIdUsuario()+ "-" + permiso.getTipo() + "-" + permiso.getExp() + "-" + permiso.getIdTurno());
        %>
    
                <tr>
                    <td class="py-3">
                        <form action="servlet">
                            <input type="hidden" name="opcion" value="turno">
                            <input type="hidden" name="fPermisoT" value="<%=permiso.getIdPermiso()%>">
                            <input type="hidden" name="fUsuarioT" value="<%=permiso.getIdUsuario()%>">
                            <input type="hidden" name="fTipoT" value="<%=permiso.getTipo()%>">
                            <input type="hidden" name="fExpT" value="<%=permiso.getExp()%>">
                            <input type="hidden" name="fTurnoT" value="<%=permiso.getIdTurno()%>">
                            <input class="form-control btn btn-primary" type="submit" value="Permiso: <%=permiso.getIdPermiso()%> ">  
                        </form>                        
                    </td>
                </tr>  
    <%
        }
        %>
                
            </table>
        </div>
        <div class="col-4"> 
            //  
            
            
            
            //
        </div> 
        <div class="col-6"> 
              A
        </div>   
            
        
        </div>
        
    </body>
</html>
