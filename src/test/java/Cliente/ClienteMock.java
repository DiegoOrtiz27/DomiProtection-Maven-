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
        c.setDni("10");
        c.setNombre("Diego ortiz");
        c.setDireccion("cra 51");
        c.setTelefono("123456");
        c.setCorreo("diego@gmail.com");
        c.setPassword("123");
        boolean resultado = clienteDao.Agregar1(c);
        assertEquals(true, resultado);
    }

    @Test
    public void verCliente() {
        boolean resultado = clienteDao.buscar1("10236229034");
        assertEquals(resultado, true);
    }

    @Test
    public void eliminarCliente() {
        int id = 4;
        assertEquals(true, clienteDao.delete1(id));
    }

}
