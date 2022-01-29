<%@page import="Modelo.Carrito"%>
<%@page import="Modelo.ProductoDAO"%>
<%@page import="Config.Conexion"%>
<%@page import="java.awt.Image"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">  
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />


        <title>Productos</title>
        <style>
            .flip-card { 
                perspective: 1000px;
                width: 40rem;
                height: 27rem;
            }

            .flip-card-inner {
                position: relative;
                width: 100%;
                height: 100%;
                text-align: center;
                transition: transform 0.6s;
                transform-style: preserve-3d;

            }

            .flip-card:hover .flip-card-inner {
                transform: rotateY(180deg);
                box-shadow: 0px 5px 21px -7px rgba(0,0,0,0.57);
            }

            .flip-card-front, .flip-card-back {
                position: absolute;
                width: 100%;
                height: 100%;
                -webkit-backface-visibility: hidden;
                backface-visibility: hidden;
                border: 1px solid black;
                justify-content: center;
                border-radius: 25px;



            }

            .flip-card-front {
                background-color: white;
                color: black;
                padding-top: 4px;

            }

            .flip-card-back {
                background-color: #bff2eb;
                color: black;
                transform: rotateY(180deg);
                padding-top: 2em;
            }
        
            body{
                overflow-x: hidden;
                font-family: Times New Roman, Times, serif;
                font-size: 1em;

            }
            .btn-whatsapp {
                display:block;
                width:4em;
                height:4em;
                color:#fff;
                position: fixed;
                right:20px;
                bottom:20px;
                border-radius:50%;
                line-height:80px;
                text-align:center;
                z-index:999;
            }

        </style>
    </head>
    <!<!--Se coloca el tipo de letra desde aca, para que cuando 
    cambie por el controlador, lleve estos cambios-->
    <body  class="text-dark" style="font-family: Times New Roman">    
        <%
            HttpSession sesion = request.getSession();
            //Conectando la Base de datos
            Conexion cn = new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps;
            //Emnpezamos Listando los Datos de la Tabla producto
            Statement smt;
            ResultSet rs;
            smt = con.createStatement();
            rs = smt.executeQuery("select * from producto");
            //Creamos la Tabla:     
            ProductoDAO listaP = new ProductoDAO();
            String direccion = "";
            String id = "";
            int contador = 0;
        %>

        <! <!-- comment -->
        <nav class="navbar navbar-expand-lg" style="background-color: #bff2eb">
            <a class="navbar-brand text-dark" href="../PPI(1)/index.jsp">DomiProtection</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link text-dark" href="Controlador?accion=Carrito&id=0"><i class="fas fa-cart-plus"></i> <label>${contador}</label></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-dark" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Sesión
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="./vistas/validarCliente.jsp">Cliente</a>
                            <a class="dropdown-item" href="./vistas/registrarCliente.jsp">Registrarse</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="./vistas/validarEmpleado.jsp">Empleado</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Bienvenidos a DomiProtection</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control me-2" type="text" name="txtbuscar">
                    <input class="btn btn-outline-dark" type="submit" value="Buscar">
                </form>
            </div>
        </nav>




        <%
            //Se crea peticion para buscar el producto.
            String nombuscar = request.getParameter("txtbuscar");
            if (nombuscar != null) {
                con = cn.getConnection();
                smt = con.createStatement();
                rs = smt.executeQuery("select * from producto where Nombres LIKE" + "'%" + nombuscar + "%'");
            } else {
                System.err.print("Error");
            }
        %>
        <div class="row justify-content-center">
            <%                            while (rs.next()) {
                    int i = 0;
                    listaP.insertarPrincipioNodo(rs.getInt("idProducto"), rs.getString("Nombres"), rs.getString("Descripcion"), rs.getDouble("Precio"), rs.getInt("Stock"));
                    listaP.insertarFinalNodo(rs.getInt("idProducto"), rs.getString("Nombres"), rs.getString("Descripcion"), rs.getDouble("Precio"), rs.getInt("Stock"));
            %>
                        <div class="col-3 text-center m-2 p-4 flip-card"  >
                <div class="border-dark flip-card-inner" >
                    <div class="flip-card-front" >
                        <div>
                            <% out.println("<h3>" + listaP.getProductos(i).getNombres() + "</h3>");%>
                        </div>
                        <div>
                            <% out.println("<h4>Codigo: " + listaP.getProductos(i).getId() + "</h4>");%>

                        </div>
                        <% boolean existe = false;
                            if (request.getAttribute("existe") != null) {
                                existe = (Boolean) request.getAttribute("existe");
                            }
                            if (existe == false) {
                        %>
                        <img src="vistas/foto.jsp?idproducto=<%=rs.getInt("idProducto")%>" class="img-responsive img-fluid imagen " style="width: 13em; height: 13em" >
                        <%
                        } else {
                        %>
                        <img src="ControladorIMG?id=<%=rs.getInt("idProducto")%>" class="img-responsive img-fluid imagen "  style="width: 13em; height: 13em" >
                        <%  }%>
                        <div>
                            <% out.println("<h4>Precio: " + listaP.getProductos(i).getPrecio() + "</h4>"); %>
                        </div>

                    </div>
                    <div class=" flip-card-back">
                        <% out.println("<p>" + listaP.getProductos(i).getDescripcion() + "</p");%>

                        <div>
                            <br> 
                            <%
                                id = String.valueOf(listaP.getProductos(i).getId());
                                direccion = "Controlador?accion=AgregarCarrito&id=";
                                if (request.getAttribute("direccion") != null) {
                                    direccion = (String) request.getAttribute("direccion");
                                }
                            %>
                            <a href="<%=direccion + id + "&idc=" + 0%>"  class="btn btn-outline-dark">Agregar carrito de compra</a>
                        </div>
                    </div>
                </div>
                <%i++;
                    }%>
            </div>
        </div>
        <div class="btn-whatsapp">
            <a href="https://api.whatsapp.com/send?phone=+573504689979" target="_blank">
                <img src="./img/whatsapp.png" class="btn-whatsapp"/>
            </a>
        </div>
    </div>
    <div class="container my-5">
        <!-- Footer -->
        <footer
            class="text-center text-lg-start text-dark"
            style="background-color:  #bff2eb"
            >
            <!-- Grid container -->
            <div class="container p-2 pb-0 border border-dark">
                <!-- Section: Links -->
                <section class="">
                    <!--Grid row-->
                    <div class="row">
                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                            <h6 class="text-uppercase mb-4 font-weight-bold">
                                DomiProtection
                            </h6>
                            <p>
                                Página de ventas de equipos Bio protectores
                            </p>
                        </div>
                        <!-- Grid column -->

                        <hr class="w-100 clearfix d-md-none" />

                        <!-- Grid column -->
                        <div class="col-md-5 col-lg-5 col-xl-5 mx-auto mt-3">
                            <h6 class="text-uppercase mb-4 font-weight-bold">
                                Información del proyecto 
                            </h6>
                            <p>
                                <a class="text-black">Empresa Organización: PJIC</a>
                            </p>
                            <p>
                                <a class="text-black">Patrocinador principal: Politécnico Colombiano Jaime Isaza Cadavid</a>
                            </p>
                            <p>
                                <a class="text-black">Líder de proyecto: Diego Alejandro Muñoz Robayo - Diego Emanuel Ortiz López</a>
                            </p>

                        </div>
                        <!-- Grid column -->

                        <hr class="w-100 clearfix d-md-none" />

                        <!-- Grid column -->
                        <hr class="w-100 clearfix d-md-none" />

                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                            <h6 class="text-uppercase mb-4 font-weight-bold">Contacto</h6>
                            <p><i class="fas fa-home mr-3"></i> Medellin, Antioquia, COL</p>
                            <p><i class="fas fa-envelope mr-3"></i> info@domiprotection.com</p>
                            <p><i class="fas fa-phone mr-3"></i> + 57 3504689979</p>

                        </div>
                        <!-- Grid column -->


                    </div>
                    <!--Grid row-->
                </section>
                <!-- Section: Links -->
            </div>
            <!-- Grid container -->


        </footer>
        <!-- Footer -->
    </div>
    <!-- End of .container -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> 
</body>
</html>