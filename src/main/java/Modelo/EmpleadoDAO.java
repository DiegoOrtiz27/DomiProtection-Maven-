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
import java.sql.SQLException;

/**
 *
 * @author EMANUEL ORTIZ
 */
public class EmpleadoDAO {

    //Se crean variables para la conexion a la base de datos.
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r=0;

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

    //Metodo para insertar el primer nodo del empleado.
    public void insertarPrincipioNodo(int id, String dni, String nom, String tel, String estado, String user) {
        Nodo newNodo = new Nodo(new Empleado(id, dni, nom, tel, estado, user));
        if (cabeza == null) {
            cabeza = newNodo;
        } else {
            newNodo.siguiente = cabeza;
            cabeza = newNodo;
        }
        size++;

    }

    //Metodo para agregar productos a la lista creada de empleados.
    public void insertarFinalNodo(int id, String dni, String nom, String tel, String estado, String user) {
        Nodo newNodo = new Nodo(new Empleado(id, dni, nom, tel, estado, user));
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
    //Se busca por el id de cada empleado para obtener un dato en especifico.
    public  Empleado getEmpleados(int index) {
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
        return apuntador.dato2;
    }
    
    //Se valida la informacion del empleado con el usuario y la identificacion.
    public Empleado validar(String user, String dni) throws SQLException {
        Empleado em = new Empleado();
        String sql = "select * from empleado where User=? and Dni=?";
        try {
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            //Se envia peticion.
            rs = ps.executeQuery();
            while (rs.next()) {
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setDni(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
            }
        } catch (Exception e) {

        }
        return em;
    }
    //Operaciones CRUD
    
    //Metodo para agregar empleado a la bd.
     public int Agregar(Empleado em) {
        //Se crea la peticion sql.
        String sql="insert into empleado(Dni,Nombres,Telefono,Estado,User)values(?,?,?,?,?)";
        try{
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se obtienen los datos del empleado que recibe el metodo en los parametros.
            ps.setString(1,em.getDni());
            ps.setString(2,em.getNom());
            ps.setString(3,em.getTel());
            ps.setString(4,em.getEstado());
            ps.setString(5,em.getUser());
            //Se envia peticion.
            ps.executeUpdate();     
        }catch(Exception e){
        }
        return r;
    }
     //Se hace peticion a la base de datos para obtener toda la informacion de cada empleado.
     public Empleado listarId(int id){
         Empleado em=new Empleado();
         //Se crea la peticion sql.
         String sql="select * from empleado where idEmpleado="+id;
         try{
              //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se envia peticion.
            rs=ps.executeQuery();
            while(rs.next()){
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setTel(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));  
            }
         }catch(Exception e){
         }
         return em;
     }
     //Metodo para actualizar la informacion de un empleado.
     public int Actualizar(Empleado em){
          //Se crea la peticion sql.
        String sql="update empleado set Dni=?,Nombres=?,Telefono=?,Estado=?,User=? where IdEmpleado=?";
        try{
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se obtienen los datos del empleado que recibe el metodo en los parametros.
            ps.setString(1,em.getDni());
            ps.setString(2,em.getNom());
            ps.setString(3,em.getTel());
            ps.setString(4,em.getEstado());
            ps.setString(5, em.getUser());
            ps.setInt(6, em.getId());
            //Se envia peticion.
            ps.executeUpdate();     
        }catch(Exception e){
        }
        return r;
     }
     
     //Metodo para eliminar empleado de la db.
     public void delete(int id){
         //Se crea la peticion sql.
         String sql="delete from empleado where idEmpleado="+id;
         try{
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se envia peticion.
            ps.executeUpdate();
         }catch(Exception e){
         }
     }
   
}
