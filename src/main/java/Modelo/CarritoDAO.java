/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static java.lang.System.out;

/**
 *
 * @author EMANUEL ORTIZ
 */
public class CarritoDAO {
    private Nodo apuntador = null;
   private Nodo cabeza = null;
    private int size=0;
     

    public Nodo getApuntador() {
        return apuntador;
    }

    public void setApuntador(Nodo apuntador) {
        this.apuntador = apuntador;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

      public void insertarPrincipioNodo(int item, int idProducto, String descripcion, String nombre, double precioCompra, int cantidad, double subtotal) {
        Nodo newNodo = new Nodo(new Carrito(item,idProducto,descripcion, nombre,precioCompra, cantidad, subtotal));
        if (cabeza == null) {
            cabeza = newNodo;
        } else {
            newNodo.siguiente = cabeza;
            cabeza = newNodo;
        }
        size++;
      }
        public void insertarFinalNodo(int item, int idProducto, String descripcion, String nombre, double precioCompra, int cantidad, double subtotal) {
        Nodo newNodo = new Nodo(new Carrito(item,idProducto,descripcion, nombre,precioCompra, cantidad, subtotal));
      apuntador=null;
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
        
     public Carrito getCarrito(int index) {
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
        return apuntador.dato1;
    }
     public void destruir(){
cabeza=null;
apuntador=null;
}
   //Metodo para eliminar objeto del carrito.
   public boolean Eliminar(int pos){
   Nodo actual =cabeza;
   Nodo anterior =null;
  //Se crea boolean para reducir tamaño del item.  
  boolean carritoVacio=false;
     
     while(actual != null){
         if(actual.dato1.item==pos){
             if(actual==cabeza){
                cabeza=cabeza.siguiente;  
             }else{
                 anterior.siguiente=actual.siguiente;
             }
             size--;
             
             if(size==0){
                 carritoVacio=true;
             }
         
         }
         anterior=actual;
         actual=actual.siguiente;
         
         
     }
     return carritoVacio;

     
   }
    
}
