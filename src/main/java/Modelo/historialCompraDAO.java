/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author EMANUEL ORTIZ
 */
public class historialCompraDAO {
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
    public void insertarPrincipioNodo( String referencia, String nombreProducto, int codigo, int cantidad, double precio, double monto, String fechaCompra) {
        Nodo newNodo = new Nodo(new historialCompra(referencia,nombreProducto,codigo, cantidad, precio, monto, fechaCompra));
        if (cabeza == null) {
            cabeza = newNodo;
        } else {
            newNodo.siguiente = cabeza;
            cabeza = newNodo;
        }
        size++;

    }

    //Metodo para agregar ventas a la lista creada de ventas con todos los datos.
    public void insertarFinalNodo( String referencia, String nombreProducto, int codigo, int cantidad, double precio, double monto, String fechaCompra) {
        Nodo newNodo = new Nodo(new historialCompra(referencia,nombreProducto,codigo, cantidad, precio, monto, fechaCompra));
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

    public historialCompra getHistorial(int index) {
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
        return apuntador.dato5;
    }
    
    //---Peticiones SQL----
    public historialCompra listarId(int id){
         historialCompra hc=new historialCompra();
         //Se crea la peticion sql.
         String sql="SELECT compra.NumeroSerie,producto.Nombres, producto.IdProducto, detalle_compra.Cantidad,detalle_compra.PrecioCompra,"
                 + "compra.Monto, compra.FechaCompra FROM (((detalle_compra "
                 + "INNER JOIN producto ON detalle_compra.IdProducto = producto.IdProducto) "
                 + "INNER JOIN compra ON detalle_compra.IdCompra = compra.IdCompra) "
                 + "INNER JOIN cliente ON compra.IdCliente = cliente.IdCliente)  where idEmpleado="+id;
         try{
              //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se envia peticion.
            rs=ps.executeQuery();
            while(rs.next()){
             hc.setReferencia(rs.getString("compra.NumeroSerie"));
             hc.setNombreProducto(rs.getString("producto.Nombres"));
             hc.setCodigo(rs.getInt("producto.IdProducto"));
             hc.setCantidad(rs.getInt("detalle_compra.Cantidad"));
             hc.setPrecio(rs.getDouble("detalle_compra.PrecioCompra"));
             hc.setMonto(rs.getDouble("compra.Monto"));
             hc.setFechaCompra(rs.getString("compra.FechaCompra"));
            }
         }catch(Exception e){
         }
         return hc;
     }
}
