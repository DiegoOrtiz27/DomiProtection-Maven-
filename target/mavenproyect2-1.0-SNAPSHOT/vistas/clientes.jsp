<%-- 
    Document   : clientes
    Created on : 2/09/2021, 10:01:41 a. m.
    Author     : EMANUEL ORTIZ
--%>

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
            <h1>Información De Clientes.</h1>
        </div>
        <div class="d-flex" style="margin:2em auto 2em 5em; ">
            <div class="card col-sm-4" style=" margin-right: 50px;box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
                <div class="card-body">
                    <form action="Controlador1?menu=Cliente" method="POST">
                        <div class="form-group">
                            <label>Identificacion</label>
                            <input type="text" value="${cliente.getDni()}" name="txtDni" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" value="${cliente.getNombre()}"  name="txtNombres" class="form-control">
                        </div>
                       <div class="form-group">
                            <label>Direccion</label>
                            <input type="text" value="${cliente.getDireccion()}"  name="txtDireccion" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Telefono</label>
                            <input type="text" value="${cliente.getTelefono()}"  name="txtTel" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Correo</label>
                            <input type="text" value="${cliente.getCorreo()}"  name="txtCorreo" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Contraseña</label>
                            <input type="text" value="${cliente.getPassword()}"  name="txtPass" class="form-control">
                        </div>
                        <input class="btn btn-danger mt-2" style="margin-right: 50px; margin-left: 95px;" type="submit" name="accion" value="Agregar">
                        <input class="btn btn-success mt-2" type="submit" name="accion" value="Actualizar">
                    </form>
                </div>
            </div>
            <div class="col-sm-6" >
                <table class="table table-hover">
                    <thead style="box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Identificacion</th>
                            <th>Direccion</th>
                            <th>Telefono</th>
                            <th>Correo</th>
                            <th>Contraseña</th>
                            <th>Acciones</th>
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
                            rs = smt.executeQuery("select * from cliente");

                            //Creamos la Tabla:     
                            ClienteDAO listaE = new ClienteDAO();

                            while (rs.next()) {
                                int i = 0;
                                //Se crea el objeto cliente para que guarde los datos de cada cliente temporalmente.
                                Cliente cl = new Cliente();
                                cl.setId(rs.getInt(1));
                                cl.setDni(rs.getString(2));
                                cl.setNombre(rs.getString(3));
                                cl.setDireccion(rs.getString(4));
                                cl.setTelefono(rs.getString(5));
                                cl.setCorreo(rs.getString(6));
                                cl.setPassword(rs.getString(7));
                                listaE.insertarPrincipioNodo(cl.getId(), cl.getDni(),cl.getNombre(),cl.getDireccion() ,cl.getTelefono(), cl.getCorreo(),cl.getPassword());
                                listaE.insertarFinalNodo(cl.getId(), cl.getDni(),cl.getNombre(),cl.getDireccion() ,cl.getTelefono(), cl.getCorreo(),cl.getPassword());

                        %>
                        <tr>
                            <td><% out.println(listaE.getClientes(i).getId());%></td>
                            <td><% out.println(listaE.getClientes(i).getNombre());%></td>
                            <td><% out.println(listaE.getClientes(i).getDni());%></td>
                            <td><% out.println(listaE.getClientes(i).getDireccion());%></td>
                            <td><% out.println(listaE.getClientes(i).getTelefono());%></td>
                            <td><% out.println(listaE.getClientes(i).getCorreo());%></td>
                            <td><% out.println(listaE.getClientes(i).getPassword());%></td>
                            <td>
                                <a class="btn btn-warning" style="width: 83px;" href="Controlador1?menu=Cliente&accion=Editar&id=<%=(listaE.getClientes(i).getId())%>">Editar</a>
                                <input type="hidden" id="pos" value="<%=listaE.getClientes(i).getId()%>">
                                <a class="btn btn-danger" id="btnBorrarCliente" href="#">Eliminar</a>
                                 
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