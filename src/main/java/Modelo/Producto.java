
package Modelo;


import java.io.InputStream;
import java.sql.Blob;


public class Producto {
    InputStream foto;
    int id;
    String nombres;
    String descripcion;
    double precio;
    int stock;
    public Producto(){
        
    }
    public  Producto(int id, String nombres,String descripcion, double precio, int stock){
        this.id=id;
        this.nombres=nombres;
        this.descripcion=descripcion;
        this.precio=precio;
        this.stock=stock;
    }

    public Producto(InputStream foto, String nombres, String descripcion, double precio, int stock) {
        this.foto = foto;
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
    

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

 

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
