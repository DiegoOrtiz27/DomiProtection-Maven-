package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Crea la lista de la venta para ser conectada con la base de datos.
 *
 * @author EMANUEL ORTIZ
 */
public class VentaDAO {
    //Conectando la Base de datos
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    //Emnpezamos Listando los Datos de la Tabla producto
    Statement smt;
    ResultSet rs;
    int r = 0;
    //Declaracion de nodos base.
    private Nodo apuntador = null;
    private Nodo cabeza = null;
    private int size = 0;

    //Se obtiene el apuntador.
    public Nodo getApuntador() {
        return apuntador;
    }

    //Se establece el apuntador.
    public void setApuntador(Nodo apuntador) {
        this.apuntador = apuntador;
    }

    //Se obtiene la cabeza de la lista.
    public Nodo getCabeza() {
        return cabeza;
    }

    //Se establece la cabeza de la lista.
    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
    //Se obtiene el tamaño de la lista.

    public int getSize() {
        return size;
    }

    //Se establece el tamaño de la lista.
    public void setSize(int size) {
        this.size = size;
    }


    //Metodo para insertar el primer nodo de la lista de ventas con todos los datos.
    public void insertarPrincipioNodo( Integer item, Integer idcliente, Integer idempleado, Integer idproducto, String Numserie, String DescripcionP, Double precio, Integer cantidad, Double subtotal, Double monto, String estado) {
        Nodo newNodo = new Nodo(new Venta(item, idcliente, idempleado, idproducto, Numserie, DescripcionP,precio,cantidad, subtotal, monto,estado));
        if (cabeza == null) {
            cabeza = newNodo;
        } else {
            newNodo.siguiente = cabeza;
            cabeza = newNodo;
        }
        size++;

    }

    //Metodo para agregar ventas a la lista creada de ventas con todos los datos.
    public void insertarFinalNodo( Integer item, Integer idcliente, Integer idempleado, Integer idproducto, String Numserie, String DescripcionP, Double precio, Integer cantidad, Double subtotal, Double monto, String estado) {
        Nodo newNodo = new Nodo(new Venta( item, idcliente, idempleado, idproducto, Numserie, DescripcionP,precio,cantidad, subtotal, monto,estado));
        apuntador = null;
        if (cabeza == null) {
            newNodo = cabeza;
        } else {
            apuntador = cabeza;
            while (apuntador.siguiente != null) {
                apuntador = apuntador.siguiente;
            }
            apuntador.siguiente = newNodo;
            size++;
        }
    }


    //Metodo para eliminar la lista.
    public void destruir() {
        cabeza = null;
        apuntador = null;
    }
//Se busca por el id de cada ventas para obtener un dato en especifico.

    public Venta getVentas(int index) {
        apuntador = null;
        if (cabeza != null) {
            int contador = 0;
            apuntador = cabeza;
            while (contador < index && apuntador.siguiente != null) {
                apuntador = apuntador.siguiente;
                contador++;
            }
        } else {
            System.out.println("La lista esta vacia");

        }
        return apuntador.dato4;
    }
    
    //---Peticiones SQL----
    
    //Metodo para traer el numero de serie de la venta de la bd.
     public String GenerarSerie() {
        String numeroserie="";
        //Se crea peticion sql.
        String sql = "select max(NumeroSerie) from venta";
        try {
             //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se envia peticion.
            rs = ps.executeQuery();
            //Se guarda el dato en la variable declarada anteriormente.
            while(rs.next()){
                numeroserie=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return numeroserie;
    }
     //Metodo para capturar el maximo del id ventas.
     public String IdVenta(){
          String idVenta="";
        //Se crea peticion sql.
        String sql = "select max(IdVenta) from venta";
        try {
             //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se envia peticion.
            rs = ps.executeQuery();
            //Se guarda el dato en la variable declarada anteriormente.
            while(rs.next()){
                idVenta=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return idVenta;
     }
     
      //Metodo para guardar venta.
     public int guardarVenta(Venta ve){ 
        //Se crea peticion sql.
        String sql = "insert into venta(IdCliente,IdEmpleado,NumeroSerie,Monto,Estado,FechaVentas) values(?,?,?,?,?,CURDATE())";
        try {
             //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            ps.setInt(1, ve.getIdcliente());
            ps.setInt(2, ve.getIdempleado());
            ps.setString(3, ve.getNumserie());
            ps.setDouble(4, ve.getMonto());
            ps.setString(5, ve.getEstado());
            //Se envia peticion.
             ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
     }
     
     //Metodo para guardar el detalle de la venta.
      public int guardarDetalleVenta(Venta ve){ 
        //Se crea peticion sql.
        String sql = "insert into detalle_venta(IdVenta,IdProducto,Cantidad,PrecioVenta) values(?,?,?,?)";
        try {
             //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            ps.setInt(1, ve.getId());
            ps.setInt(2, ve.getIdproducto());
            ps.setInt(3, ve.getCantidad());
            ps.setDouble(4, ve.getPrecio());
            //Se envia peticion.
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
     }

    
}
