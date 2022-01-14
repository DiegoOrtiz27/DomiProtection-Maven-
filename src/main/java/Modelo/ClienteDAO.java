
package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author EMANUEL ORTIZ
 */
public class ClienteDAO {
     //Se crean variables para la conexion a la base de datos.
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r=0;
    boolean client=false;

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

    //Metodo para insertar el primer nodo del cliente.
    public void insertarPrincipioNodo(int id, String Dni, String nombre, String direccion, String telefono, String correo, String password) {
        Nodo newNodo = new Nodo(new Cliente(id, Dni,nombre, direccion, telefono, correo, password));
        if (cabeza == null) {
            cabeza = newNodo;
        } else {
            newNodo.siguiente = cabeza;
            cabeza = newNodo;
        }
        size++;

    }

    //Metodo para agregar productos a la lista creada de clientes.
    public void insertarFinalNodo(int id, String Dni, String nombre, String direccion, String telefono, String correo, String password) {
        Nodo newNodo = new Nodo(new Cliente(id, Dni,nombre, direccion, telefono, correo, password));
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
    //Se busca por el id de cada cliente para obtener un dato en especifico.
    public  Cliente getClientes(int index) {
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
        return apuntador.dato3;
    }
    public boolean buscar1(String dni){
        
        Cliente cl=new Cliente();
        String sql="select * from cliente where Dni="+dni;
        try{
            
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se envia peticion.
            rs = ps.executeQuery();
            //Se llenan los datos para ser enviados al controlador.
            while(rs.next()){
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNombre(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setTelefono(rs.getString(5));
                cl.setCorreo(rs.getString(6));
                cl.setPassword(rs.getString(7));
                cl.setEstado(rs.getString(8));
            }
        }catch(Exception e){
        }
        return client= true;
        
    }
    //Metodo para bucar a un cliente.
    public Cliente buscar(String dni){
        
        Cliente cl=new Cliente();
        String sql="select * from cliente where Dni="+dni;
        try{
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se envia peticion.
            rs = ps.executeQuery();
            //Se llenan los datos para ser enviados al controlador.
            while(rs.next()){
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNombre(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setTelefono(rs.getString(5));
                cl.setCorreo(rs.getString(6));
                cl.setPassword(rs.getString(7));
                cl.setEstado(rs.getString(8));
            }
        }catch(Exception e){
        }
        return cl;
    }
    
    //Se valida la informacion del cliente con el correo y la contraseña.
    public Cliente validar(String Email, String Password) throws SQLException {
        Cliente cl = new Cliente();
        String sql = "select * from cliente where Email=? and Password=?";
        try {
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            ps.setString(1, Email);
            ps.setString(2, Password);
            //Se envia peticion.
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.setId(rs.getInt("idCliente"));
                cl.setDni(rs.getString("Dni"));
                cl.setNombre(rs.getString("Nombres"));
                cl.setDireccion(rs.getString("Direccion"));
                cl.setTelefono(rs.getString("Telefono"));
                cl.setCorreo(rs.getString("Email"));
                cl.setPassword(rs.getString("Password"));
            }
        } catch (Exception e) {

        }
        return cl;
    }
    //Operaciones CRUD
    
    //Metodo para agregar cliente a la bd.
     public boolean Agregar1(Cliente cl) {
         boolean pasa = false;
        //Se crea la peticion sql.
        String sql="insert into cliente(Dni,Nombres,Direccion,Telefono,Email,Password)values(?,?,?,?,?,?)";
        try{
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se obtienen los datos del empleado que recibe el metodo en los parametros.
            ps.setString(1,cl.getDni());
            ps.setString(2,cl.getNombre());
            ps.setString(3,cl.getDireccion());
            ps.setString(4,cl.getTelefono());
            ps.setString(5,cl.getCorreo());
            ps.setString(6,cl.getPassword());
            //Se envia peticion.
            ps.executeUpdate(); 
            pasa=true;
        }catch(Exception e){
            pasa=false;
        }
        return pasa;
    }
      public int Agregar(Cliente cl) {
        //Se crea la peticion sql.
        String sql="insert into cliente(Dni,Nombres,Direccion,Telefono,Email,Password)values(?,?,?,?,?,?)";
        try{
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se obtienen los datos del empleado que recibe el metodo en los parametros.
            ps.setString(1,cl.getDni());
            ps.setString(2,cl.getNombre());
            ps.setString(3,cl.getDireccion());
            ps.setString(4,cl.getTelefono());
            ps.setString(5,cl.getCorreo());
            ps.setString(6,cl.getPassword());
            //Se envia peticion.
            ps.executeUpdate();     
        }catch(Exception e){
        }
        return r;
    }
     //Se hace peticion a la base de datos para obtener toda la informacion de cada cliente.
     public Cliente listarId(int id){
         Cliente cl = new Cliente();
         //Se crea la peticion sql.
         String sql="select * from cliente where idCliente="+id;
         try{
              //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se envia peticion.
            rs=ps.executeQuery();
            while(rs.next()){
                cl.setDni(rs.getString(2));
                cl.setNombre(rs.getString(3));
                cl.setDireccion(rs.getString(4));
                cl.setTelefono(rs.getString(5));
                cl.setCorreo(rs.getString(6));
                cl.setPassword(rs.getString(7));
                
            }
         }catch(Exception e){
         }
         return cl;
     }
     //Metodo para actualizar la informacion de un cliente.
     public int Actualizar(Cliente cl){
          //Se crea la peticion sql.
        String sql="update cliente set Dni=?,Nombres=?,Direccion=?,Telefono=?,Email=?,Password=? where IdCliente=?";
        try{
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se obtienen los datos del cliente que recibe el metodo en los parametros.
            ps.setString(1,cl.getDni());
            ps.setString(2,cl.getNombre());
            ps.setString(3,cl.getDireccion());
            ps.setString(4,cl.getTelefono());
            ps.setString(5,cl.getCorreo());
            ps.setString(6,cl.getPassword());
            ps.setInt(7,cl.getId());
            //Se envia peticion.
            ps.executeUpdate();     
        }catch(Exception e){
        }
        return r;
     }
       public boolean Actualizar1(Cliente cl){
          //Se crea la peticion sql.
          boolean pasa = false;
        String sql="update cliente set Dni=?,Nombres=?,Direccion=?,Telefono=?,Email=?,Password=? where IdCliente=?";
        try{
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se obtienen los datos del cliente que recibe el metodo en los parametros.
            ps.setString(1,cl.getDni());
            ps.setString(2,cl.getNombre());
            ps.setString(3,cl.getDireccion());
            ps.setString(4,cl.getTelefono());
            ps.setString(5,cl.getCorreo());
            ps.setString(6,cl.getPassword());
            ps.setInt(7,cl.getId());
            //Se envia peticion.
            ps.executeUpdate();
            pasa= true;
        }catch(Exception e){
            pasa=false;
        }
        return pasa;
     }
      
     
         //Metodo para eliminar cliente de la db.
     public boolean delete1(int id){
         //Se crea la peticion sql.
         boolean pasa = false;
         String sql="delete from cliente where idCliente="+id;
         try{
            //Conexion a la db.
            con = cn.getConnection();
            //Se prepara peticion.
            ps=con.prepareStatement(sql);
            //Se envia peticion.
            ps.executeUpdate();
            pasa = true;
         }catch(Exception e){
             pasa = false;
         }
         return pasa;
     }
      public boolean clienteExistente(int x, int y) {
        //Se crea la peticion sql.
        String sql = "select max(IdCliente) from cliente";
        boolean productoExiste = false;
        int tamano = 0;
        Cliente cl = new Cliente();
        try {
            con = cn.getConnection();
            Statement smt = con.createStatement();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                tamano = rs.getInt("max(IdCliente)");
            }
        } catch (Exception e) {
        }
        for (int i = 0; i <= tamano; i++) {
            //Se trae cada producto de la db para verificar existencia.
            cl = listarId(i);
            //Aagregar
            switch (y) {
                case 1:
                    if (x <= cl.getId()) {
                        System.out.println("Entro en metodo de verificacion de existencia");
                        productoExiste = true;
                        return productoExiste;
                    }
                    break; // break es opcional
            //Borrar y actualizar
                case 2:
                    if (x == cl.getId()) {
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
     //Metodo para eliminar cliente de la db.
     public void delete(int id){
         //Se crea la peticion sql.
         String sql="delete from cliente where idCliente="+id;
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

      //Metodo para buscar la exitesn
    public boolean clienteExistente(int x, int y) {
        //Se crea la peticion sql.
        String sql = "select max(IdProducto) from producto";
        boolean productoExiste = false;
        int tamano = 0;
        Cliente c = new Cliente();
        try {
            con = cn.getConnection();
            Statement smt = con.createStatement();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                tamano = rs.getInt("max(IdCliente)");
            }
        } catch (Exception e) {
        }
        for (int i = 0; i <= tamano; i++) {
            //Se trae cada producto de la db para verificar existencia.
            c = listarId(i);
            //Aagregar
            switch (y) {
                case 1:
                    if (x <= c.getId()) {
                        System.out.println("Entro en metodo de verificacion de existencia");
                        productoExiste = true;
                        return productoExiste;
                    }
                    break; // break es opcional
            //Borrar y actualizar
                case 2:
                    if (x == c.getId()) {
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
