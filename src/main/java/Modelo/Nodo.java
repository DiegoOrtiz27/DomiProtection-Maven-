/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;



/**
 * Crea la base de todas la listas simples utilizadas.
 * @author diego
 */
public class Nodo {

    //Se crea el nodo de tipo Producto.
    public Nodo(Producto dato) {
        this.dato = dato;
    }
    //Se crea el nodo de tipo Carrito.
    public Nodo(Carrito dato) {
        this.dato1 = dato;
    }
    //Se crea el nodo de tipo Empleado.
    public Nodo(Empleado dato) {
        this.dato2 = dato;
    }
    //Se crea el nodo de tipo Cliente.
    public Nodo(Cliente dato) {
        this.dato3 = dato;
    }
    //Se crea el nodo de tipo Venta.
    public Nodo(Venta dato) {
        this.dato4 = dato;
    }
    //Se crea el nodo de tipo historial de compra.
    public Nodo(historialCompra dato) {
        this.dato5 = dato;
    }
    public Nodo siguiente=null;

    public Nodo() {
    }
  //Se declaran obejetos de cada tipo para guardar las propiedades de las mismas.
   public Producto dato;
   public Carrito dato1;
   public Empleado dato2;
   public Cliente dato3;
   public Venta dato4;
   public historialCompra dato5;
   
}
