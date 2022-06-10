<%@page import="java.util.ArrayList"%>
<%@page import="beans.turnoBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Horario Empleado </title>        
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/styles.css"/>   
    </head>
    <body>
        <%@include file="../templates/navbar.jsp"%>
        <div class="container-fluid my-5"> 
            <table class="bg-light w-100 text-center table-bordered">
                <thead>
                    <tr class="bgpurple text-light">
                        <th> HORA </th>
                        <th> LUNES </th>
                        <th> MARTES </th>
                        <th> MIERCOLES </th>
                        <th> JUEVES </th>
                        <th> VIERNES</th>
                        <th> SÁBADO </th>
                    </tr>
                </thead>
                <%
                    int lunes[] = new int[16];
                    int martes[] = new int[16];
                    int miercoles[] = new int[16];
                    int jueves[] = new int[16];
                    int viernes[] = new int[16];
                    int sabado[] = new int[16];
                    
                    ArrayList<turnoBeans> lista = (ArrayList<turnoBeans>)request.getAttribute("listaTurnos");
                    
                    for(int i = 0; i < lista.size(); i++){
                        int t=0, horaI, horaF;
                        turnoBeans turnos = lista.get(i);
                        
                        switch (turnos.getDia()){
                            case 0:
                                horaI = turnos.getHorarioI();
                                horaF = turnos.getHorarioF();
                                while(horaI <= horaF){
                                    lunes[t] = horaI;
                                    horaI++;
                                    t++;
                                }
                                break;
                            case 1:
                                horaI = turnos.getHorarioI();
                                horaF = turnos.getHorarioF();
                                while(horaI <= horaF){
                                    martes[t] = horaI;
                                    horaI++;
                                    t++;
                                }
                                break;
                            case 2:
                                horaI = turnos.getHorarioI();
                                horaF = turnos.getHorarioF();
                                while(horaI <= horaF){
                                    miercoles[t] = horaI;
                                    horaI++;
                                    t++;
                                }
                                break;
                            case 3:
                                horaI = turnos.getHorarioI();
                                horaF = turnos.getHorarioF();
                                while(horaI <= horaF){
                                    jueves[t] = horaI;
                                    horaI++;
                                    t++;
                                }
                                break;
                            case 4:
                                horaI = turnos.getHorarioI();
                                horaF = turnos.getHorarioF();
                                while(horaI <= horaF){
                                    viernes[t] = horaI;
                                    horaI++;
                                    t++;
                                }
                                break;
                            case 5:
                                horaI = turnos.getHorarioI();
                                horaF = turnos.getHorarioF();
                                while(horaI <= horaF){
                                    sabado[t] = horaI;
                                    horaI++;
                                    t++;
                                }
                                break;                                
                        }
                        
                    }    
                    
                    int contDias[] = {0,0,0,0,0,0};
                    for(int i=0 ; i<=15 ; i++){
                        %>
                    
                        <tr>                            
                            <td class="font-weight-bold bgpurple text-light"> <%=i+7%> </td>
                    <% 
                        //Lunes
                        if(lunes[contDias[0]]==i+7){       
                        contDias[0]++;
                        %>
                        
                            <td class="bgyellow"> </td>
                            
                    <%
                        }else{
                        %>
                    
                            <td class="bg-info">  </td>
                        
                    <%
                        }
                        //Martes
                        if(martes[contDias[1]]==i+7){
                        contDias[1]++;
                        %>  
                        
                            <td class="bgyellow"> </td> 
                            
                    <%
                        }else{
                        %>
                    
                            <td class="bg-info">  </td>
                            
                    <%
                        }
                        //Miercoles
                        if(miercoles[contDias[2]]==i+7){
                        contDias[2]++;
                        %>  
                        
                            <td class="bgyellow"> </td> 
                            
                    <%
                        }else{
                        %>
                    
                            <td class="bg-info">  </td>
                            
                    <%
                        }
                        //Jueves
                        if(jueves[contDias[3]]==i+7){
                        contDias[3]++;
                        %>  
                        
                            <td class="bgyellow"> </td> 
                            
                    <%
                        }else{
                        %>
                    
                            <td class="bg-info">  </td>
                            
                    <%
                        }
                        //Viernes
                        if(viernes[contDias[4]]==i+7){
                        contDias[4]++;
                        %>  
                        
                            <td class="bgyellow"> </td> 
                            
                    <%
                        }else{
                        %>
                    
                            <td class="bg-info">  </td>
                            
                    <%
                        }
                        //Sábadp
                        if(sabado[contDias[5]]==i+7){
                        contDias[5]++;
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
    </body>
</html>
