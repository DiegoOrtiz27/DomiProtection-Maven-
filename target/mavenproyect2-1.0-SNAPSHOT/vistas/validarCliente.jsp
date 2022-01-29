<%-- 
    Document   : ValidarEmpleado
    Created on : 2/09/2021, 9:39:43 a. m.
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
        <nav class="navbar navbar-expand-lg " style="background:  linear-gradient(rgb(18,111,232) 70%, rgb(9,56,116)); border: solid 1px black;">
            <div class="container-fluid"> 

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active text-light" aria-current="page" id="headerdomiprotection" href="/PPI(1)/index.jsp">DomiProtection</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
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
        <div class="cotainer col-lg-4" style="margin:8% auto;">
            <div class="card">
                <div class="card-body">
                    <form class="form-sign" action="<%=validar%>" method="POST">
                        <div class="form-group text-center">
                            <h3>Iniciar sesion</h3>
                            <img style="width:4em;height:4em" class="img-responsive" src="<%=direccion%>" alt="login"/>
                            <label>Bienvenid@s al Sistema</label>
                        </div>
                        <div class="form-group">
                            <label>Correo: </label>
                            <input type="text" name="textcorreo" class="form-control">
                        </div>
                        <div class="form-group">
                             <label>Contraseña: </label>
                            <input type="password" name="textpass" class="form-control">
                        </div>
                        <input type="submit" name="menu" value="Ingresar" class="btn btn-primary btn-block">
                    </form>
                </div>
            </div>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js" integrity="sha512-Tn2m0TIpgVyTzzvmxLNuqbSJH3JP8jm+Cy3hvHrW7ndTDcJ1w5mBiksqDBb8GpE2ksktFvDB/ykZ0mDpsZj20w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    </body>
</html>
