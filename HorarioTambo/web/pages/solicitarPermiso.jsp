<%@page import="java.util.ArrayList"%>
<%@page import="beans.turnoBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Solicitar Permiso </title>
        <link rel="stylesheet" href="../css/bootstrap.css"/>
        <link rel="stylesheet" href="../css/styles.css"/>   
    </head>
    <body>        
        <%@include file="../templates/navbar.jsp"%>
        
        
        <div class="container"> 
            <form class="form-control text-center font-weight-bold" action="../servlet">
                <table class="table">
                    <tr>
                        <td colspan="2"> <input id="fOpcion" name="opcion" type="hidden" value="solPer"></td>
                    </tr>
                    <tr>
                        <td> Tipo </td> 
                        <td> Seleccionar Horario</td>
                    </tr>
                    <tr>
                        <td>                                
                            <input type="radio" id="M" name="fTipo" value="M">
                            <label> Médico </label><br>
                            <input type="radio" id="E" name="fTipo" value="E">
                            <label> Estudios </label><br>
                            <input type="radio" id="P" name="fTipo" value="P">
                            <label> Personal </label>
                        </td>     
                        <td rowspan="4">
                            
                        <table>  
                <%
                    ArrayList<turnoBeans> lista = (ArrayList<turnoBeans>)sessionOK.getAttribute("lista");
                    String dias[] = {"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};
                    String turno = null;
                    for(int i = 0; i < lista.size(); i++){
                        turnoBeans turnos = lista.get(i);
                        turno = dias[turnos.getDia()] + " " + turnos.getHorarioI() + " - " + turnos.getHorarioF();
                        
                    %>
                       
                            <tr>
                                <td> 
                                    <input type="radio" name="fTurno" value="<%=turnos.getIdTurno()%>">
                                        <label> <%=turno%> </label><br>    
                                </td>
                            </tr>
                                
                <%
                    }

                    %>
                        </table>
                        </td>
                        
                    </tr>
                    <tr>
                        <td> Explicación </td> 
                    </tr>
                    <tr>
                        <td> <textarea name="fExp" style="resize:none" rows="5" cols="50"> </textarea> </td>
                    </tr>
                    <tr>
                        <td> 
                            <button type="button" class="btn disabled"><a href=""> Pruebas </a></button>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"> 
                            <input class="form-control btn btn-primary" type="submit" value="Solicitar">  
                        </td>                            
                    </tr>                        
                </table>                    
            </form>         
        </div>
    </body>    
</html>
