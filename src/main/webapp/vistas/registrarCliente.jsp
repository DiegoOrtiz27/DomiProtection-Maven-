<%-- 
    Document   : registrarCliente
    Created on : 7/09/2021, 3:01:33 p. m.
    Author     : EMANUEL ORTIZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">  
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Iniciar sesion</title>
    </head>
    <body>
         <%
             //Para traer la direccion correcta para la imagen al momento de cambiar de pagina y volver a esta misma.
                             HttpSession sesion = request.getSession();
                             String direccion = "../img/login.jpeg";
                             String validar="../Validar";;
                                if (request.getAttribute("redirigir") != null) {
                                    direccion = (String) request.getAttribute("redirigir");
                                    validar=(String)request.getAttribute("validar");
                                }
                            %>
        <div class="cotainer col-lg-4" style="margin:0 auto;">
            <div class="card">
                <div class="card-body">
                    <form>
                        <div class="form-group text-center">
                            <h3>Registrarse</h3>
                            <img style="width:4em;height:4em" class="img-responsive" src="<%=direccion%>" alt="login"/>
                            <label>Bienvenid@s al Sistema</label>
                        </div>
                        <div class="form-group">
                            <label>Identificacion:</label>
                            <input type="text" id="textdni" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombres: </label>
                            <input type="text" id="textnom" class="form-control">
                        </div>
                       <div class="form-group">
                            <label>Direccion:</label>
                        <input type="text" id="textdireccion" class="form-control">
                        </div>
                         <div class="form-group">
                            <label>Telefono:</label>
                            <input type="text" id="texttelefono" class="form-control">
                        </div> 
                        <div class="form-group">
                            <label>Correo: </label>
                            <input type="text" id="textcorreo" class="form-control">
                        </div>
                        <div class="form-group">
                             <label>Contraseña: </label>
                            <input type="password" id="textpass" class="form-control">
                        </div>
                            <a href="#" id="btnRegistrar" class="btn btn-primary">Registrar</a>
                        <a class="btn btn-primary " href="./validarCliente.jsp">Iniciar Sesion</a>                  
                    </form>
                </div>
            </div>
        </div>
        
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> 
    <script src="../js/funciones.js" type="text/javascript"></script>
    </body>
</html>
