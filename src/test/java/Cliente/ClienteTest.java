/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author ASUS
 */
public class ClienteTest {

    ClienteDAO cl = new ClienteDAO();
    
    @Test
    public void agregarCliente() {
        Cliente c = new Cliente();
        c.setDni("10");
        c.setNombre("Diego ortiz");
        c.setDireccion("cra 51");
        c.setTelefono("123456");        
        c.setCorreo("diego@gmail.com");
        c.setPassword("123");
        boolean resultado=cl.Agregar1(c);
        assertEquals(true,resultado);
    }
     
}
