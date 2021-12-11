<%-- 
    Document   : Principal
    Created on : 30/08/2021, 2:01:04 a. m.
    Author     : EMANUEL ORTIZ
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet">   
        <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Principal</title>
    </head>
    <body>
       <nav class="navbar navbar-expand-lg " style="background:  linear-gradient(rgb(18,111,232) 70%, rgb(9,56,116)); border: solid 1px black;">
            <div class="container-fluid"> 

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active text-light" aria-current="page" id="headerdomiprotection" href="Controlador1?menu=Principal">Principal</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active text-light" aria-current="page" href="Controlador1?menu=Producto&accion=Listar" target="myFrame">Producto</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active text-light" aria-current="page" href="Controlador1?menu=Empleado&accion=Listar" target="myFrame">Empleado</a>
                        </li>
                         <li class="nav-item">
                            <a class="nav-link active text-light" aria-current="page" href="Controlador1?menu=Cliente&accion=Listar" target="myFrame">Clientes</a>
                        </li>
                          <li class="nav-item">
                            <a class="nav-link active text-light" aria-current="page" href="Controlador1?menu=NuevaVenta&accion=default&id=${usuario.getId()}" target="myFrame">Nueva Venta</a>
                        </li>
                    </ul>
                    <div class="dropdown text-center">
                    <button class="btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${usuario.getNom()}
                    </button>
                    <div class="dropdown-menu mr-4" aria-labelledby="dropdownMenuButton">
                        
                       
                        <a class="dropdown-item" href="#">
                             <img style="width:6em;height:6em" class="img-responsive" src="./img/user.jpeg" alt=""/>
                        </a>
                        <a class="dropdown-item" href="#">${usuario.getUser()}</a>
                        <a class="dropdown-item" href="#">usuario@gmail.com</a>
                        <div class="dropdown-driver"></div>
                        <form accion="../Validar" method="POST">
                            <button name="menu" value="Salir" class="dropdown-item" >Salir</button>
                        </form>
                    </div>
                </div>
                </div>
            </div>
        </nav> 
                   <!-- Se utiliza para mostrar las pantallas que se piden en el navbar  -->
                        <div class="m-4" style="height: 550px;">
                            <iframe name="myFrame" style="height: 100%; width: 100%"></iframe>
                        </div>              
        <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>