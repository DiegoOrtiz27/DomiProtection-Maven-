<%-- 
    Document   : producto
    Created on : 2/09/2021, 10:00:27 a. m.
    Author     : EMANUEL ORTIZ
--%>

<%@page import="Modelo.ProductoDAO"%>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Clientes</title>
    </head>
    <body>
        <div class="d-flex justify-content-center">
            <h1>Información De Productos.</h1>
        </div>
        <div class="d-flex" style="margin:2em auto 2em 5em; ">
            <div class="card col-sm-4" style=" box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2); position:fixed;">
                <div class="card-body ">
                    <form action="Controlador1?menu=Producto" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" value="${producto.getNombres()}"  name="txtNombres" class="form-control">
                        </div>
                       <div class="form-group" >
                            <label>Foto</label>
                            <input type="file"  name="fileFoto" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Descripcion</label>
                            <input type="text" value="${producto.getDescripcion()}"  name="txtDesc" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Precio</label>
                            <input type="text" value="${producto.getPrecio()}"  name="txtPrecio" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input type="text" value="${producto.getStock()}"  name="txtStock" class="form-control">
                        </div>
                        <input class="btn btn-danger mt-2" style="margin-right: 50px; margin-left: 95px;" type="submit" name="accion" value="Agregar">
                        <input class="btn btn-success mt-2" type="submit" name="accion" value="Actualizar">
                    </form>
                </div>
            </div>
                        <div class="col-sm-6" style="margin-left: 650px">
                <table class="table table-hover">
                    <thead style="box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Foto</th>
                            <th>Descripcion</th>
                            <th>Precio</th>
                            <th>Stock</th>
                        </tr>
                    </thead>
                    <tbody style="box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
                        <%
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

                            while (rs.next()) {
                                int i = 0;
                                //Se crea el objeto cliente para que guarde los datos de cada cliente temporalmente.
                    listaP.insertarPrincipioNodo(rs.getInt("idProducto"), rs.getString("Nombres"), rs.getString("Descripcion"), rs.getDouble("Precio"), rs.getInt("Stock"));
                    listaP.insertarFinalNodo(rs.getInt("idProducto"), rs.getString("Nombres"), rs.getString("Descripcion"), rs.getDouble("Precio"), rs.getInt("Stock"));

                        %>
                        <tr>
                            <td><% out.println(listaP.getProductos(i).getId());%></td>
                            <td><% out.println(listaP.getProductos(i).getNombres());%></td>
                            <td><img src="vistas/foto.jsp?idproducto=<%=rs.getInt("idProducto")%>" class="img-responsive img-fluid imagen" width="100" height="100" ></td>
                            <td><% out.println(listaP.getProductos(i).getDescripcion());%></td>
                            <td><% out.println(listaP.getProductos(i).getPrecio());%></td>
                            <td><% out.println(listaP.getProductos(i).getStock());%></td>
 
                            <td>
                                <a class="btn btn-warning" style="width: 83px;" href="Controlador1?menu=Producto&accion=Editar&id=<%=(listaP.getProductos(i).getId())%>">Editar</a>
                                <input type="hidden" id="pos" value="<%=listaP.getProductos(i).getId()%>">
                                <a class="btn btn-danger" id="btnBorrarProducto" href="#">Eliminar</a>
                                 
                            </td>
                        </tr>
                        <%i++;
                            }%>
                    </tbody>
                </table>    

            </div>
        </div>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://code.jquery.com/jquery.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="./js/funciones.js" type="text/javascript"></script>
    </body>
    
</html>

