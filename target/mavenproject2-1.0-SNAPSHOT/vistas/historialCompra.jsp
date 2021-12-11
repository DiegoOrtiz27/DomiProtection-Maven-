<%-- 
    Document   : historialCompra
    Created on : 8/09/2021, 2:29:40 a. m.
    Author     : EMANUEL ORTIZ
--%>

<%@page import="Modelo.historialCompra"%>
<%@page import="Modelo.historialCompraDAO"%>
<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.ClienteDAO"%>
<%@page import="Modelo.Empleado"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Config.Conexion"%>
<%@page import="Modelo.EmpleadoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/cssProducto.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">  
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Clientes</title>
    </head>
    <body>
         <nav class="navbar navbar-expand-lg " style="background:  linear-gradient(rgb(18,111,232) 70%, rgb(9,56,116)); border: solid 1px black;">
            <div class="container-fluid"> 

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active text-light" aria-current="page" href="Controlador?accion=Default&id=${cliente.getId()}">DomiProtection</a>
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
                    <div class="btn-group" style="margin-right: 185px">

                        <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
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
        <div class="d-flex justify-content-center mt-4 mb-4">
            <h1>Historial De Compras</h1>
        </div>
        <div class="d-flex justify-content-center mt-4">
            <div class="col-sm-6" >
                <table class="table table-hover">
                    <thead style="box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
                        <tr>
                            <th>Referenica</th>
                            <th>Producto</th>
                            <th>Codigo</th>
                            <th>Cantidad</th>
                            <th>Precio</th>
                            <th>Monto</th>
                            <th>Fecha</th>
                        </tr>
                    </thead>
                    <tbody style="box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
                        <%
                            HttpSession sesion = request.getSession();
                            Cliente cliente = new Cliente();
                                if (request.getAttribute("cliente") != null) {
                                    cliente = (Cliente) request.getAttribute("cliente");
                                }
                            //Conectando la Base de datos
                            Conexion cn = new Conexion();
                            Connection con = cn.getConnection();
                            PreparedStatement ps;
                            //Emnpezamos Listando los Datos de la Tabla producto
                            Statement smt;
                            ResultSet rs;
                            smt = con.createStatement();
                            rs = smt.executeQuery("SELECT compra.NumeroSerie,producto.Nombres, producto.IdProducto, detalle_compra.Cantidad,detalle_compra.PrecioCompra,"
                 + "compra.Monto, compra.FechaCompra FROM (((detalle_compra "
                 + "INNER JOIN producto ON detalle_compra.IdProducto = producto.IdProducto) "
                 + "INNER JOIN compra ON detalle_compra.IdCompra = compra.IdCompra) "
                 + "INNER JOIN cliente ON compra.IdCliente = cliente.IdCliente)  where cliente.IdCliente="+cliente.getId());

                            //Creamos la Tabla:     
                            historialCompraDAO listaE = new historialCompraDAO();

                            while (rs.next()) {
                                int i = 0;
                                //Se crea el objeto cliente para que guarde los datos de cada cliente temporalmente.
                                historialCompra hc = new historialCompra();
                                hc.setReferencia(rs.getString(1));
                                hc.setNombreProducto(rs.getString(2));
                                hc.setCodigo(rs.getInt(3));
                                hc.setCantidad(rs.getInt(4));
                                hc.setPrecio(rs.getDouble(5));
                                hc.setMonto(rs.getDouble(6));
                                hc.setFechaCompra(rs.getString(7));
                                listaE.insertarPrincipioNodo(hc.getReferencia(), hc.getNombreProducto(),hc.getCodigo(),hc.getCantidad() ,hc.getPrecio(), hc.getMonto(),hc.getFechaCompra());
                                listaE.insertarFinalNodo(hc.getReferencia(), hc.getNombreProducto(),hc.getCodigo(),hc.getCantidad() ,hc.getPrecio(), hc.getMonto(),hc.getFechaCompra());

                        %>
                        <tr>
                            <td><% out.println(listaE.getHistorial(i).getReferencia());%></td>
                            <td><% out.println(listaE.getHistorial(i).getNombreProducto());%></td>
                            <td><% out.println(listaE.getHistorial(i).getCodigo());%></td>
                            <td><% out.println(listaE.getHistorial(i).getCantidad());%></td>
                            <td><% out.println(listaE.getHistorial(i).getPrecio());%></td>
                            <td><% out.println(listaE.getHistorial(i).getMonto());%></td>
                            <td><% out.println(listaE.getHistorial(i).getFechaCompra());%></td>
                        </tr>
                        <%i++;
                            }%>
                    </tbody>
                </table>    

            </div>
        </div>
        
          <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> 
    </body>
    
</html>
