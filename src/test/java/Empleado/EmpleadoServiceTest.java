/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Empleado;


import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author emanu
 */
public class EmpleadoServiceTest {
        @Mock
    private EmpleadoDAO daoMock;
    
    @InjectMocks
    private EmpleadoService service;
    
    @Before
    public void setUp() throws Exception {

         MockitoAnnotations.openMocks(this);   
    }
     @Test
        public void testAddEmpleado() {
            Empleado empleado = new Empleado();
            empleado.setId(5);
            empleado.setDni("123_prueba");
            empleado.setNom("Empleado Prueba");
            empleado.setTel("3186867");
            empleado.setEstado("1");
            empleado.setUser("PruebaEm");
             assertEquals(true,service.addEmpleado(empleado));
        }
     @Test
        public void testUpdateEmpleado() {
             Empleado empleado = new Empleado();
             empleado.setId(3);
            empleado.setDni("123_prueba");
            empleado.setNom("Empleado Prueba");
            empleado.setTel("3186867");
            empleado.setEstado("1");
            empleado.setUser("PruebaEm");
             assertEquals(true,service.updateEmpleado(empleado.getId(), empleado));
        }
    @Test
        public void testDeleteEmpleado() {
            int idProducto=4;
             assertEquals(true,service.deleteEmpleado(idProducto));
        }
}
