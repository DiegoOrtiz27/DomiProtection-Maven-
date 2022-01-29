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


        <link href="css/cssProducto.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">  
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Productos</title>

    </head>
    <!<!--Se coloca el tipo de letra desde aca, para que cuando 
    cambie por el controlador, lleve estos cambios-->
    <body  class="text-dark" style="font-size:1.2em; font-family: Garamond,serif">    

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
        <nav class="navbar navbar-expand-lg " style="background:  linear-gradient(rgb(18,111,232) 70%, rgb(9,56,116)); border: solid 1px black;">
            <div class="container-fluid"> 

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active text-light" aria-current="page" id="headerdomiprotection" href="#footerdomiprotection">DomiProtection</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="Controlador?accion=Carrito&id=${cliente.getId()}"><i class="fas fa-cart-plus"></i> <label>${contador}</label></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="Controlador?accion=HistorialCompra&id=${cliente.getId()}"><i class="fas fa-history"></i></a>
                        </li>
                    </ul>
                </div>
                <a data-toggle="dropdown" href="#">                    

                    <div class="btn-group" style="margin-right: 205px">

                        <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false" >
                            <span class="visually-hidden"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">${cliente.getNombre()}</a></li>
                            <li><a class="dropdown-item" href="#">${cliente.getCorreo()}</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="./vistas/validarCliente.jsp">Cerrar Sesion</a></li>
                        </ul>
                     </div>
                </a>
            
        </div>
    </nav>
    <div>
        <h3 class="text-center">Bienvenido a DomiProtection</h3>
    </div>
    <div class="container mt-2 ">
        <form class="d-flex">
            <input class="form-control me-2" type="text" name="txtbuscar">
            <input class="btn btn-outline-dark" type="submit" value="Buscar">
        </form>
        <%
            //Se crea peticion para buscar el producto.
            String nombuscar=request.getParameter("txtbuscar");
            if(nombuscar!=null){
            con = cn.getConnection();
            smt=con.createStatement();
            rs=smt.executeQuery("select * from producto where Nombres LIKE"+"'%"+nombuscar+"%'");
            }else{
            System.err.print("Error");
            }
        %>
        <div class="row justify-content-center" >
            <%                            while (rs.next()) {
                    int i = 0;
                    listaP.insertarPrincipioNodo(rs.getInt("idProducto"), rs.getString("Nombres"), rs.getString("Descripcion"), rs.getDouble("Precio"), rs.getInt("Stock"));
                    listaP.insertarFinalNodo(rs.getInt("idProducto"), rs.getString("Nombres"), rs.getString("Descripcion"), rs.getDouble("Precio"), rs.getInt("Stock"));
            %>
            <div class="col-sm-4 card-group text-center mt-2 "  >
                <div class="card border-dark">
                    <div class="card-header">
                        <% out.println("<label>" + listaP.getProductos(i).getNombres() + "</label>");%>

                    </div>
                    <div class="card-body"  >
                        <% out.println("<label>Codigo: " + listaP.getProductos(i).getId() + "</label>");%>

                        <% boolean existe = false;
                            if (request.getAttribute("existe") != null) {
                                existe = (Boolean) request.getAttribute("existe");
                            }
                            if (existe == false) {
                        %>
                        <img src="vistas/foto.jsp?idproducto=<%=rs.getInt("idProducto")%>" class="img-responsive img-fluid imagen" style="width: 10em; height: 10em" >
                        <%
                        } else {
                        %>
                        <img src="ControladorIMG?id=<%=rs.getInt("idProducto")%>" class="img-responsive img-fluid imagen"  style="width: 10em; height: 10em" >
                        <%  }%>
                        <% out.println("<i>Precio: " + listaP.getProductos(i).getPrecio() + "</i>"); %>
                    </div>
                    <div class="card-footer">
                        <% out.println("<p>" + listaP.getProductos(i).getDescripcion() + "</p");%>
                        <div>
                            <%
                                id = String.valueOf(listaP.getProductos(i).getId());
                                direccion = "Controlador?accion=AgregarCarrito&id=";
                                if (request.getAttribute("direccion") != null) {
                                    direccion = (String) request.getAttribute("direccion");
                                }
                            %>
                            <a href="<%=direccion + id+"&idc="%>${cliente.getId()}"  class="btn btn-outline-info">Agregar carrito de compra</a>
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
    <footer class="text-white text-center text-lg-start mt-4" style="background:  linear-gradient(rgb(18,111,232) 70%, rgb(9,56,116)); border: solid 1px black;" id="footerdomiprotection">
        <!-- Grid container -->
        <div class="container p-4">
            <!--Grid row-->
            <div class="row">
                <!--Grid column-->
                <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
                    <h5 class="text-uppercase">Nosotros</h5>

                    <p class="text-dark">
                        DomiProtection, es una página de catálogos de productos de bioseguridad, 
                        hacemos domicilios garantizados y tenemos productos de alta calidad
                    </p>
                </div>
                <!--Grid column-->

                <!--Grid column-->
                <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                    <h5 class="text-uppercase">Informacion</h5>

                    <ul class="list-unstyled mb-0">
                        <li>
                            <a href="#!" class="text-dark">© Aviso Legal</a>
                        </li>
                        <li>
                            <a href="#!" class="text-dark">© Politica de Privacidad</a>
                        </li>
                    </ul>
                </div>
                <!--Grid column-->

                <!--Grid column-->
                <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                    <h5 class="text-uppercase mb-0">Contacto</h5>

                    <ul class="list-unstyled">
                        <li>
                            <a href="mailto:diego_munoz23211@elpoli.edu.co" class="text-white"><img src="./img/correo.png" style="width: 2em; height: 2em;"/></a> 
                        </li>
                    </ul>
                </div>
                <!--Grid column-->
            </div>
            <!--Grid row-->
            <a href="principalCliente.jsp" class="text-white"><i class="fas fa-redo-alt"></i></a>
        </div>
        <!-- Grid container -->

    </footer>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> 
</body>
</html>