/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Config.Conexion;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.io.OutputStream;
import Config.Conexion;

import java.io.OutputStream;
import java.sql.Blob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletResponse;

/**
 * Crea la lista simple de los productos para poder ser mostrados a los
 * clientes.
 *
 * @author diego
 */
public class ProductoDAO {

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

    //Metodo para insertar el primer nodo del producto.
    public void insertarPrincipioNodo(int id, String nombres, String descripcion, double precio, int stock) {
        Nodo newNodo = new Nodo(new Producto(id, nombres, descripcion, precio, stock));
        if (cabeza == null) {
            cabeza = newNodo;
        } else {
            newNodo.siguiente = cabeza;
            cabeza = newNodo;
        }
        size++;

    }

    //Metodo para agregar productos a la lista creada de productos.
    public void insertarFinalNodo(int id, String nombres, String descripcion, double precio, int stock) {
        Nodo newNodo = new Nodo(new Producto(id, nombres, descripcion, precio, stock));
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
//Se busca por el id de cada producto para obtener un dato en especifico.

    public Producto getProductos(int index) {
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
        return apuntador.dato;
    }

    //Se hace peticion desde el frontend para agregar prodcuto.
    public void agregarProducto(Producto pd) {
        String sql = "insert into producto(Nombres,Foto,Descripcion,Precio,Stock) values(?,?,?,?,?)";
        try {
            //Se crea conexion a la db.
            con = cn.getConnection();
            //Preparacion de peticion.
            ps = con.prepareStatement(sql);
            ps.setString(1, pd.getNombres());
            ps.setBlob(2, pd.getFoto());
            ps.setString(3, pd.getDescripcion());
            ps.setDouble(4, pd.getPrecio());
            ps.setInt(5, pd.getStock());
            //Se actualiza stock
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    

    //Se hace peticion a la base de datos para obtener toda la informacion de cada producto.
    public Producto listarId(int id) {
        String sql = "select * from producto where IdProducto=" + id;
        Producto p = new Producto();
        try {
            con = cn.getConnection();
            smt = con.createStatement();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
            }
        } catch (Exception e) {
        }

        return p;
    }
    //Actualizar Producto desde frontend

    public int Actualizar(Producto p) {
        //Se declara peticion sql.
        String sql = "update producto set Nombres=?,Foto=?,Descripcion=?,Precio=?,Stock=? where IdProducto=?";
        try {
            //Se crea conexion a la db.
            con = cn.getConnection();
            //Preparacion de peticion.
            ps = con.prepareStatement(sql);
            ps.setString(1, p.nombres);
            ps.setBlob(2, p.getFoto());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getId());
            //Se actualiza stock
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    

    //Actualizar Stock
    public int actualizarstock(int id, int stock) {
        //Se declara peticion sql.
        String sql = "update producto set Stock=? where IdProducto=?";
        try {
            //Se crea conexion a la db.
            con = cn.getConnection();
            //Preparacion de peticion.
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            //Se actualiza stock
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    //Metodo para eliminar cliente de la db.

    public void delete(int id) {
        //Se crea la peticion sql.
        String sql = "delete from producto where IdProducto=" + id;
        try {
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps = con.prepareStatement(sql);
            //Se envia peticion.
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
     //Se hace peticion a la base de datos para obtener la imagen.
    public void listarIMG(int idProducto, HttpServletResponse response) throws SQLException {
        Conexion cn = new Conexion();

        Connection con = cn.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ps = con.prepareStatement("SELECT foto FROM producto where idProducto=?");
        OutputStream oImage;
        try {

            ps.setInt(1, idProducto);

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
    }

    //--METODOS PARA TEST--
    
    //Se hace peticion desde el test para agregar prodcuto.
    public boolean agregarProducto1(Producto pd) {
        String sql = "insert into producto(Nombres,Descripcion,Precio,Stock) values(?,?,?,?)";
        boolean pasa = false;
        try {
            //Se crea conexion a la db.
            con = cn.getConnection();
            //Preparacion de peticion.
            ps = con.prepareStatement(sql);
            ps.setString(1, pd.getNombres());
            ps.setString(2, pd.getDescripcion());
            ps.setDouble(3, pd.getPrecio());
            ps.setInt(4, pd.getStock());
            //Se actualiza stock
            ps.executeUpdate();
            pasa = true;

        } catch (Exception e) {
            pasa = false;
        }
        return pasa;
    }
    
    //Metodo para borrar producto
    public boolean borrarProducto(int id) {
        //Se crea la peticion sql.
        String sql = "delete from producto where IdProducto=" + id;
        boolean pasa;
        try {
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps = con.prepareStatement(sql);
            //Se envia peticion.
            ps.executeUpdate();
            pasa = true;
        } catch (Exception e) {
            pasa = false;
        }
        return pasa;
    }
    
    
    //Metodo para actualizar producto
    public boolean actualizarProducto1(Producto p) {
        //Se declara peticion sql.
        String sql = "update producto set Nombres=?,Descripcion=?,Precio=?,Stock=? where IdProducto=?";
        boolean pasa = false;
        try {
            //Se crea conexion a la db.
            con = cn.getConnection();
            //Preparacion de peticion.
            ps = con.prepareStatement(sql);
            ps.setString(1, p.nombres);
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getId());
            //Se actualiza stock
            ps.executeUpdate();
            pasa = true;
        } catch (Exception e) {
            pasa = false;
        }
        return pasa;
    }

    
    
    //Metodo para buscar la exitesn
    public boolean productoExistente(int x, int y) {
        //Se crea la peticion sql.
        String sql = "select max(IdProducto) from producto";
        boolean productoExiste = false;
        int tamano = 0;
        Producto p = new Producto();
        try {
            con = cn.getConnection();
            smt = con.createStatement();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                tamano = rs.getInt("max(IdProducto)");
            }
        } catch (Exception e) {
        }
        for (int i = 0; i <= tamano; i++) {
            //Se trae cada producto de la db para verificar existencia.
            p = listarId(i);
            //Aagregar
            switch (y) {
                case 1:
                    if (x <= p.getId()) {
                        System.out.println("Entro en metodo de verificacion de existencia");
                        productoExiste = true;
                        return productoExiste;
                    }
                    break; // break es opcional
            //Borrar y actualizar
                case 2:
                    if (x == p.getId()) {
                        System.out.println("Entro en metodo de verificacion de existencia");
                        productoExiste = true;
                        return productoExiste;
                    }
                    break; // break es opcional
                default:
                // Declaraciones
            }

        }
        return productoExiste;
    }

   
}
