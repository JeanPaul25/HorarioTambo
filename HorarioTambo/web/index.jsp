<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head lang="es">    
        <title> Horario Tambo </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <link rel="stylesheet" href="css/styles.css"/>          
    </head>    
    
    <body style="overflow: hidden;">    
        
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid justify-content-center">                         
                <img src="images/LogoTambo.png" class="img-fluid" alt="Logo Tambo">          
            </div>
        </nav>
        
        <main class="container my-5">
            <div class="row"> 
                <div class="d-flex justify-content-center col-12 my-3"> 
                    <svg xmlns="http://www.w3.org/2000/svg" height="20vh" fill="purple" class="bi bi-person-circle align-center" viewBox="0 0 16 16">
                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                    </svg> 
                </div> 
                
                <form class="form-control text-center font-weight-bold" action="servlet">
                    <table class="table">
                        <tr>
                            <td> <input id="fOpcion" name="opcion" type="hidden" value="login"></td>
                        </tr>
                        <tr>
                            <td> Usuario </td>                          
                        </tr>
                        <tr>
                            <td>                                
                                <input class="form-control text-center" type="text" id="fUsuario" name="fUsuario"><br>
                            </td>                            
                        </tr>
                        <tr>
                            <td> Contraseña </td>
                        </tr>
                        <tr>
                            <td> 
                                <input class="form-control text-center" type="password" id="fPassword" name="fPassword"><br></td>
                        </tr>
                        <tr>
                            <td> 
                                <input class="form-control btn btn-primary button" type="submit" value="Ingresar">  
                            </td>                            
                        </tr>                        
                    </table>                    
                </form>                                   
            </div>            
        </main>
    </body>
</html>
