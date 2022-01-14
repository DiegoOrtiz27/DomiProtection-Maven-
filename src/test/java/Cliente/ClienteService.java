/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cliente;

import Modelo.Cliente;
import Modelo.ClienteDAO;

/**
 *
 * @author ASUS
 */
public class ClienteService {
    public boolean addCliente(Cliente cliente){
        ClienteDAO clienteDao = new ClienteDAO();
        //Verificacion de la existencia del empleado
        boolean Existe=clienteDao.clienteExistente(cliente.getId(),1);
         System.out.println(Existe);
        boolean agregado=false;
        if(Existe==false){
            agregado=clienteDao.Agregar1(cliente);
   
        }else{
            System.out.println("El cliente ya esta agregado(Agregar)");
        }
        return agregado;
    }
        public boolean updateCliente(int idCliente, Cliente cliente){
       ClienteDAO clienteDao = new ClienteDAO();
        //Verificacion de la existencia del producto
        boolean Existe=clienteDao.clienteExistente(idCliente,2);
        boolean actualizado=false;
        if(Existe==false){
            System.out.println("Cliente no existe(Update)");
            
        }else{
           actualizado= clienteDao.Actualizar1(cliente);
            
        }
        return actualizado;
    }
     public boolean deleteCliente(int idCliente){
        ClienteDAO clienteDao = new ClienteDAO();
        //Verificacion de la existencia del producto
        boolean Existe=clienteDao.clienteExistente(idCliente,2);
        boolean eliminado=false;
        if(Existe==false){
            System.out.println("Cliente no existe(Delete)");
            
        }else{
            eliminado=clienteDao.delete1(idCliente);
        }
        return eliminado;
    }
}
