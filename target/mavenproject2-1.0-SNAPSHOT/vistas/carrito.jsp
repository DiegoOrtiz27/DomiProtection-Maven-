<%-- 
    Document   : carrito.jsp
    Created on : 13/08/2021, 2:49:52 p. m.
    Author     : EMANUEL ORTIZ
--%>


<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.CarritoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">  
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Comprar Producto</title>
             <style>
            @media print{
                .parte01, .btn, .accion{
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            Cliente cliente = new Cliente();
            if (request.getAttribute("cliente") != null) {
                cliente = (Cliente) request.getAttribute("cliente");
            }

        %>
        <nav class="navbar navbar-expand-lg parte01" style="background:  linear-gradient(rgb(18,111,232) 70%, rgb(9,56,116)); border: solid 1px black;">
            <div class="container-fluid"> 

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <%if (cliente.getId() == 0) {%>
                            <a class="nav-link active text-light" aria-current="page" id="headerdomiprotection" href="../PPI(1)/index.jsp">DomiProtection</a>
                            <%} else {%>
                            <a class="nav-link active text-light" aria-current="page" href="Controlador?accion=Default&id=${cliente.getId()}">DomiProtection</a>
                            <%}%>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="Controlador?accion=Carrito"><i class="fas fa-cart-plus"></i> <label>${contador}</label></a>
                        </li>
                        <li class="nav-item">
                            <%if (cliente.getId() == 0) {%>
                            <%} else {%>
                            <a class="nav-link text-light" href="Controlador?accion=HistorialCompra&id=${cliente.getId()}"><i class="fas fa-history"></i></a>
                                <%}%>
                        </li>
                    </ul>
                
                </div>
                            <%if (cliente.getId() == 0) {%>

                    <%} else {%>
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
                    <%}%>
            </div>
        </nav>
        <div class="container mt-4">
            <h3 class="parte01">Carrito</h3>
            <br>
            <div class="row">
                <div class="col-sm-8">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>ITEM</th>
                                <th>NOMBRES</th>
                                <th>DESCRIPCION</th>
                                <th>PRECIO</th>
                                <th>CANTIDAD</th>
                                <th>SUBTOTAL</th>
                                <th class="accion">ACCION</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                CarritoDAO cdao = new CarritoDAO();

                                if (request.getAttribute("cdao") != null) {
                                    cdao = (CarritoDAO) request.getAttribute("cdao");
                                }
                                int i = 0;
                                while (i < cdao.getSize()) {
                                    int item = cdao.getCarrito(i).getItem();
                                    int cantidad = 0;

                            %>
                            <tr>
                                <td><% out.println(item);%></td>
                                <td><% out.println(cdao.getCarrito(i).getNombre());%></td>
                                <td><% out.println(cdao.getCarrito(i).getDescripcion());%>
                                    <img src="ControladorIMG?id=<%=cdao.getCarrito(i).getIdProducto()%>" width="100" height="100"> 
                                </td>
                                <td><% out.println(cdao.getCarrito(i).getPrecioCompra());%></td>
                                <td>
                                    <input type="hidden" id="id" value="${cliente.getId()}">
                                    <input type="hidden" id="idpro" value="<%=cdao.getCarrito(i).getIdProducto()%>">
                                    <input type="number" id="Cantidad" value="<%=cdao.getCarrito(i).getCantidad()%>"  min="1" max="100" step="1" class="form-control text-center "/>
                                    
                                </td>
                                <td><% out.println(cdao.getCarrito(i).getSubtotal());%></td>
                                <td class="parte01">
                                    
                                    <input type="hidden" id="pos" value="<%=item%>">
                                    <a href="#" class="btn btn-danger btn-block" id="btnDelete">Eliminar</a>

                                </td>
                            </tr>
                            <% i++;
                            }%>
                        </tbody>
                    </table>

                </div>
                <div class="col-sm-4 parte01">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="parte01">Generar Comprar</h3>
                        </div>
                        <div class="card-body parte01">
                            <label>Subtotal:</label>
                            <input type="text" value="$. ${totalPagar}0" readonly="" class="form-control">
                            <label>Descuento:</label>
                            <input type="text" readonly="" class="form-control">
                            <label>Total Pagar:</label>
                            <input type="text" value="$. ${totalPagar}0" readonly="" class="form-control">
                        </div>
                        <%

                            if (cliente.getId() == 0) {

                        %>
                        <div class="card-footer">
                            <a href="./vistas/registrarCliente.jsp" target="_blank" class="btn btn-danger btn-block">Generar Compra</a>
                        </div>
                        <%} else {%>
                        <div class="card-footer">
                            <a href="Controlador?accion=GenerarCompra" onclick="print()" class="btn btn-danger btn-block">Generar Compra</a>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> 
      
     <script src="./js/funciones.js" type="text/javascript"></script>
    
    </body>
</html>
