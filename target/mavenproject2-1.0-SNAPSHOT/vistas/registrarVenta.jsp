<%-- 
    Document   : registrarVenta
    Created on : 2/09/2021, 10:02:50 a. m.
    Author     : EMANUEL ORTIZ
--%>

<%@page import="Modelo.VentaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Ventas</title>
        <style>
            @media print{
                .parte01, .btn, .accion{
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-sm-5 parte01">
                <!-- DATOS CLIENTE -->
                <div class="card" style="width: 35rem; box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
                    <form action="Controlador1?menu=NuevaVenta" method="POST">
                        <div class="card-body container">
                            <div class="form-group">
                                <label>Datos del Cliente</label>
                            </div>

                            <div class="form-group d-flex row align-items-center">
                                <div class=" d-flex mt-2 col-sm-6" >
                                    <input type="text" name="codigocliente" value="${cl.getDni()}" class="form-control" placeholder="Codigo">
                                    <input type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-info">
                                </div>
                                <div class="col-sm-6 mt-2">
                                    <input type="text" name="nombreCliente" value="${cl.getNombre()}" class="form-control" placeholder="Datos Cliente"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <!-- DATOS PRODUCTO -->
                                <label>Datos Producto</label>
                            </div>
                            <div class="form-group d-flex row align-items-center">
                                <div class=" d-flex mt-2 col-sm-6" >
                                    <input type="text" name="codigoproducto" value="${producto.getId()}" class="form-control" placeholder="Codigo">
                                    <button type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info">Buscar</button>
                                </div>
                                <div class="col-sm-6 mt-2">
                                    <input type="text" name="nombreproducto" value="${producto.getNombres()}" class="form-control" placeholder="Datos Producto"> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Datos Producto</label>
                            </div>
                            <div class="form-group d-flex row align-items-center">
                                <div class=" d-flex mt-2 col-sm-6" >
                                    <input type="text" name="precio" value="${producto.getPrecio()}" class="form-control" placeholder="/.0.00">

                                </div>
                                <div class="col-sm-3 mt-2">
                                    <input type="number" name="cant" value="1" class="form-control" > 
                                </div>
                                <div class="col-sm-3 mt-2">
                                    <input type="text" name="stock" value="${producto.getStock()}" class="form-control" placeholder="Stock"> 
                                </div>
                            </div>
                                <!-- BOTON AGREGAR PRODUCTO AL REGISTRO -->
                            <div class="form-group">
                                <button type="submit" name="accion" value="Agregar" class="btn btn-outline-info">Agregar Producto</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div> 
                                
                <div class="col-sm-7">
                    <div class="card" style="box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
                        <div class="card-body">
                            <div class="d-flex ml-auto col-sm-6">
                                <label>NumeroSerie</label>
                                <input type="text" name="NroSerie" value="${nserie}" class="form-control">
                            </div>
                            <br/>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Nro</th>
                                        <th>Codigo</th>
                                        <th>Descripcion</th>
                                        <th>Precio</th>
                                        <th>Cantidad</th>
                                        <th>Subtotal</th>
                                        <th class="accion">Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                     <%
                                HttpSession sesion = request.getSession();
                                VentaDAO vdao = new VentaDAO();

                                if (request.getAttribute("vdao") != null) {
                                    vdao = (VentaDAO) request.getAttribute("vdao");
                                }
                                int i = 0;
                                while (i < vdao.getSize()) {

                            %>
                                    <tr>
                                        <td><% out.println(vdao.getVentas(i).getItem());%></td>
                                        <td><% out.println(vdao.getVentas(i).getIdproducto());%></td>
                                        <td><% out.println(vdao.getVentas(i).getDescripcionP());%></td>
                                        <td><% out.println(vdao.getVentas(i).getPrecio());%></td>
                                        <td><% out.println(vdao.getVentas(i).getCantidad());%></td>
                                        <td><% out.println(vdao.getVentas(i).getSubtotal());%></td>
                                        <td>
                                            <a href="#" class="btn btn-warning">Editar</a>
                                            <a href="#" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                     <%i++;}%>
                                </tbody>
                            </table>

                        </div>
                        <div class="card-footer d-flex">
                            <div class="col-sm-6">
                                <a href="Controlador1?menu=NuevaVenta&accion=GenerarVenta" id="btnGenerarVenta" onclick="print()" class="btn btn-success">Generar Venta</a>
                               
                                <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
                            </div>
                            <div class="col-sm-4 d-flex">
                                <label>Total a pagar</label>
                                <input type="text" name="txtTotal" value="s/. ${totalpagar}" class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://code.jquery.com/jquery.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="./js/funciones.js" type="text/javascript"></script>
    </body>
</html>
