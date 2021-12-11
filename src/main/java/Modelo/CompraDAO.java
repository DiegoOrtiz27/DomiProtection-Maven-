package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CompraDAO {

    //Conectando la Base de datos
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    //Emnpezamos Listando los Datos de la Tabla producto
    Statement smt;
    ResultSet rs;
    int r=0;
    public int GenerarCompra(Compra compra) {
        int idcompras;
        String sql="insert into compra(IdCliente,NumeroSerie,Monto,Estado,FechaCompra)values(?,?,?,?,CURDATE())";
        try{
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, compra.getCliente().getId());
            ps.setString(2, compra.getNumeroSerie());
            ps.setDouble(3, compra.getMonto());
            ps.setString(4, compra.getEstado());
       
            ps.executeUpdate();
            sql="Select @@IDENTITY AS IdCompra";
            rs=ps.executeQuery(sql);
            rs.next();
            idcompras=rs.getInt("IdCompra");
            rs.close();
            for(int i=0;i<compra.getDetallecompras().getSize();i++){
                sql="insert into detalle_compra(IdProducto,IdCompra,Cantidad,PrecioCompra)values(?,?,?,?)";
                ps=con.prepareStatement(sql);
                ps.setInt(1,compra.getDetallecompras().getCarrito(i).getIdProducto());
                ps.setInt(2,idcompras);
                ps.setInt(3,compra.getDetallecompras().getCarrito(i).getCantidad());
                ps.setDouble(4,compra.getDetallecompras().getCarrito(i).getPrecioCompra());
                r=ps.executeUpdate();
            }
            
           
        }catch(Exception e){
        }
        return r;
    }
}
