<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%
    String usuarioNA = "";
    HttpSession sessionOK = request.getSession();
    if(sessionOK.getAttribute("usuarioNA") != null){
        usuarioNA = (String)sessionOK.getAttribute("usuarioNA");
    }
    %>
<!DOCTYPE html>
<html>      
    <head>        
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/styles.css"/>  
    </head>    
    <body>
        
        <nav class="navbar navbar-expand-lg">
        <% 
            String opc = request.getParameter("opcion");
            if(opc.equals("dashEmp")){
            %>
            <div class="container-fluid justify-content-center col-2">                         
                <img src="images/LogoTambo.png" class="img-fluid w-100" alt="Logo Tambo">          
            </div>
            <div class="col-4 text-center"> Permisos</div>
            <div class="col-4 text-center"> AAAA </div>
            <div class="col-2">
                <div>
                    <button type="button" class="btn bgyellow">
                        <svg xmlns="http://www.w3.org/2000/svg" height="5vh" fill="purple" class="bi bi-person-circle align-center" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                        </svg>   
                        <a class="d-flex"> <%=usuarioNA%> </a>
                    </button>
                    <a class="btn bgyellow align-self-center " href="servlet?opcion=logout"> 
                        <svg xmlns="http://www.w3.org/2000/svg" height="3vh" fill="purple" class="bi bi-door-closed" viewBox="0 0 16 16">
                            <path d="M3 2a1 1 0 0 1 1-1h8a1 1 0 0 1 1 1v13h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V2zm1 13h8V2H4v13z"/>
                            <path d="M9 9a1 1 0 1 0 2 0 1 1 0 0 0-2 0z"/>
                        </svg> 
                    </a>
                </div>
            </div>
        <%
            }
            %>
        </nav>
    </body>
    
</html>
