/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Producto;

import Modelo.Producto;
import Modelo.ProductoDAO;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author emanu
 */
public class ProductoServiceTest {
    @Mock
    private ProductoDAO daoMock;
    
    @InjectMocks
    private ProductoService service;
    
    @Before
    public void setUp() throws Exception {

         MockitoAnnotations.openMocks(this);   
    }
     @Test
        public void testAddProducto() {
            Producto producto = new Producto();
            producto.setId(14);
            producto.setNombres("Prueba Producto");
            producto.setDescripcion("prueba para agregar producto al catalogo");
            producto.setPrecio(500);
            producto.setStock(65);
             assertEquals(true,service.addProducto(producto));
        }
    @Test
        public void testUpdateProducto() {
             Producto producto = new Producto();
            producto.setId(3);
            producto.setNombres("Prueba Producto Actulizado");
            producto.setDescripcion("prueba para agregar producto actualizado al catalogo");
            producto.setPrecio(005);
            producto.setStock(56);
             assertEquals(true,service.updateProducto(producto.getId(), producto));
        }
    @Test
        public void testDeleteProducto() {
            int idProducto=4;
             assertEquals(true,service.deleteProducto(idProducto));
        }
}
