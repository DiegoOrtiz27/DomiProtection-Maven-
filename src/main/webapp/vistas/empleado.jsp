<%-- 
    Document   : empleado
    Created on : 2/09/2021, 10:02:16 a. m.
    Author     : EMANUEL ORTIZ
--%>

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
        <title>Empleado</title>
    </head>
    <body>
        <div class="d-flex justify-content-center">
            <h1>Información De Empleados.</h1>
        </div>
        <div class="d-flex justify-content-center" style="margin-top: 4em; ">
            <div class="card col-sm-4" style="margin-right: 50px; box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);">
                <div class="card-body">
                    <form action="Controlador1?menu=Empleado" method="POST">
                        <div class="form-group">
                            <label>Identificacion</label>
                            <input type="text" value="${empleado.getDni()}" name="txtDni" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" value="${empleado.getNom()}"  name="txtNombres" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Telefono</label>
                            <input type="text" value="${empleado.getTel()}"  name="txtTel" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <input type="text" value="${empleado.getEstado()}"  name="txtEstado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Usuario</label>
                            <input type="text" value="${empleado.getUser()}"  name="txtUsuario" class="form-control">
                        </div>
                        <input class="btn btn-danger mt-2" style="margin-right: 50px; margin-left: 100px;" type="submit" name="accion" value="Agregar">
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
                            <th>Telefono</th>
                            <th>Estado</th>
                            <th>Usuario</th>
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
                            rs = smt.executeQuery("select * from empleado");

                            //Creamos la Tabla:     
                            EmpleadoDAO listaE = new EmpleadoDAO();

                            while (rs.next()) {
                                int i = 0;
                                //Se crea el objeto empleado para que guarde los datos de cada empleado temporalmente.
                                Empleado em = new Empleado();
                                em.setId(rs.getInt(1));
                                em.setDni(rs.getString(2));
                                em.setNom(rs.getString(3));
                                em.setTel(rs.getString(4));
                                em.setEstado(rs.getString(5));
                                em.setUser(rs.getString(6));
                                listaE.insertarPrincipioNodo(em.getId(), em.getDni(), em.getNom(), em.getTel(), em.getEstado(), em.getUser());
                                listaE.insertarFinalNodo(em.getId(), em.getDni(), em.getNom(), em.getTel(), em.getEstado(), em.getUser());

                        %>
                        <tr>
                            <td><% out.println(listaE.getEmpleados(i).getId());%></td>
                            <td><% out.println(listaE.getEmpleados(i).getNom());%></td>
                            <td><% out.println(listaE.getEmpleados(i).getDni());%></td>
                            <td><% out.println(listaE.getEmpleados(i).getTel());%></td>
                            <td><% out.println(listaE.getEmpleados(i).getEstado());%></td>
                            <td><% out.println(listaE.getEmpleados(i).getUser());%></td>
                            <td>
                                <a class="btn btn-warning" style="width: 83px;" href="Controlador1?menu=Empleado&accion=Editar&id=<%=(listaE.getEmpleados(i).getId())%>">Editar</a>
                                <input type="hidden" id="pos" value="<%=listaE.getEmpleados(i).getId()%>">
                                <a class="btn btn-danger" id="btnBorrar" href="#">Eliminar</a>
                                 
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
