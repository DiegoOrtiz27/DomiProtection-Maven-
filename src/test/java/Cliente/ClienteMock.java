/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import static junit.runner.Version.id;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import org.mockito.Mock;


import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author ASUS
 */
public class ClienteMock {

    @Mock
    private Cliente cliente;

    @InjectMocks
    private ClienteDAO clienteDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void agregarCliente() {
        Cliente c = new Cliente();
        c.setDni("71731661");
        c.setNombre("nuevi");
        c.setDireccion("cra 51");
        c.setTelefono("6543256");
        c.setCorreo("ossman@gmail.com");
        c.setPassword("1333");
        boolean resultado = clienteDao.Agregar1(c);
        assertEquals(true, resultado);
    }
   @Test
    public void actualizarCliente() {
        Cliente c = new Cliente();
        c.setId(30);
        c.setDni("71731661");
        c.setNombre("Ossman Munoz actualizado");
        c.setDireccion("cra 51");
        c.setTelefono("6543256");
        c.setCorreo("ossman@gmail.com");
        c.setPassword("3211234");
        boolean resultado = clienteDao.Actualizar1(c);
        assertEquals(true, resultado);
    }
    
   

    @Test
    public void verCliente() {
        boolean resultado = clienteDao.buscar1("1023622903");
        assertEquals(resultado, true);
    }

    @Test
    public void eliminarCliente() {
        int id = 31;

        assertEquals(true, clienteDao.delete1(id));
    }

}
