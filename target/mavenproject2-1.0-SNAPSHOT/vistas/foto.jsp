<%-- 
    Document   : foto
    Created on : 4/08/2021, 06:46:58 AM
    Author     : ASUS
--%>

<%@page import="Config.Conexion"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>

<%@page import="java.io.OutputStream"%>
<%@page contentType="image/jpeg" pageEncoding="UTF-8"%>
<%
 Conexion cn= new Conexion();
            
            Connection con=cn.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    ps = con.prepareStatement("SELECT foto FROM producto where idProducto=?" );
    OutputStream oImage;
    try {
        int idProducto = Integer.parseInt(request.getParameter("idproducto"));
        
        ps.setInt(1,idProducto);
        
        rs = ps.executeQuery();
        if (rs.next()) {
            byte barray[] = rs.getBytes(1);
            response.setContentType("image/jpeg");
            oImage = response.getOutputStream();
            oImage.write(barray);
            oImage.flush();
            oImage.close();
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception ex) {
             ex.printStackTrace();
        }
    }

%>
