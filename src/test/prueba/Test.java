package prueba;


import Modelo.Cliente;
import Modelo.ClienteDAO;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author ASUS
 */
public class Test {
    
    ClienteDAO cl = new ClienteDAO();
    
    @org.junit.Test
    public void agregarCliente(){
        Cliente c = new Cliente();
        c.setDni("1000");
        c.setNombre("Diego");
        c.setDireccion("cra 51");
        c.setTelefono("123456");    
        c.setCorreo("diego@gmail.com");
        c.setPassword("123");
        boolean resultado=cl.Agregar(c.getDni(),c.getNombre(),c.getDireccion(), c.getTelefono(), c.getCorreo(), c.getPassword());
        assertEquals(resultado,true);
    }
}
