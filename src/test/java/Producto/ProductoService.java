/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Producto;

import Modelo.Producto;
import Modelo.ProductoDAO;



public class ProductoService {
    
    
    
    public boolean addProducto(Producto producto){
        ProductoDAO productoDao = new ProductoDAO();
        //Verificacion de la existencia del producto
        boolean Existe=productoDao.productoExistente(producto.getId(),1);
        boolean agregado=false;
        if(Existe==false){
            agregado=productoDao.agregarProducto1(producto);
            
        }else{
            System.out.println("El producto ya esta agregado(Add)");
        }
        return agregado;
    }
    public boolean updateProducto(int idProducto, Producto producto){
        ProductoDAO productoDao = new ProductoDAO();
        //Verificacion de la existencia del producto
        boolean Existe=productoDao.productoExistente(idProducto,2);
        boolean actualizado=false;
        if(Existe==false){
            System.out.println("Producto no existe(Update)");
            
        }else{
           actualizado= productoDao.actualizarProducto1(producto);
            
        }
        return actualizado;
    }
        public boolean deleteProducto(int idProducto){
        ProductoDAO productoDao = new ProductoDAO();
        //Verificacion de la existencia del producto
        boolean Existe=productoDao.productoExistente(idProducto,2);
        boolean eliminado=false;
        if(Existe==false){
            System.out.println("Producto no existe(Delete)");
            
        }else{

            System.out.println("Producto Eliminado");

            eliminado=productoDao.borrarProducto(idProducto);
        }
        return eliminado;
    }
    
}
