/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Empleado;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.ProductoDAO;

/**
 *
 * @author emanu
 */
public class EmpleadoService {
     public boolean addEmpleado(Empleado empleado){
        EmpleadoDAO empleadoDao = new EmpleadoDAO();
        //Verificacion de la existencia del empleado
        boolean Existe=empleadoDao.empleadoExistente(empleado.getId(),1);
         System.out.println(Existe);
        boolean agregado=false;
        if(Existe==false){
            agregado=empleadoDao.agregarEmpleado(empleado);
   
        }else{
            System.out.println("El empleado ya esta agregado(Agregar)");
        }
        return agregado;
    }
        public boolean updateEmpleado(int idEmpleado, Empleado empleado){
        EmpleadoDAO empleadoDao = new EmpleadoDAO();
        //Verificacion de la existencia del producto
        boolean Existe=empleadoDao.empleadoExistente(idEmpleado,2);
        boolean actualizado=false;
        if(Existe==false){
            System.out.println("Empleado no existe(Update)");
            
        }else{
           actualizado= empleadoDao.actualizarEmpleado(empleado);
            
        }
        return actualizado;
    }
     public boolean deleteEmpleado(int idEmpleado){
        EmpleadoDAO empleadoDao = new EmpleadoDAO();
        //Verificacion de la existencia del producto
        boolean Existe=empleadoDao.empleadoExistente(idEmpleado,2);
        boolean eliminado=false;
        if(Existe==false){
            System.out.println("Empleado no existe(Delete)");
            
        }else{
            eliminado=empleadoDao.borrarEmpleado(idEmpleado);
        }
        return eliminado;
    }
    
}
