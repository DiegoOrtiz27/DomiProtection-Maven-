
package Modelo;

/**
 * Plantilla para generar una venta.
 * @author EMANUEL ORTIZ
 */
public class Venta {
    //Se declaran variables
    Integer id;
    Integer item;
    Integer idcliente;
    Integer idempleado;
    Integer idproducto;
    int cod;
    String Numserie;
    String DescripcionP;
    String fecha;
    Double precio;
    Integer cantidad;
    Double subtotal;
    Double monto;
    String estado;
    //Se crea constructor vacio para inicializar el objeto.
    public Venta(){}
    
    //Se crea contructor con todas las variables.
    public Venta(Integer id, Integer item, Integer idcliente, Integer idempleado, Integer idproducto, String Numserie, String DescripcionP, String fecha, Double precio, Integer cantidad, Double subtotal, Double monto, String estado) {
        this.id = id;
        this.item = item;
        this.idcliente = idcliente;
        this.idempleado = idempleado;
        this.idproducto = idproducto;
        this.Numserie = Numserie;
        this.DescripcionP = DescripcionP;
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.monto = monto;
        this.estado = estado;
    }
     //Se crea contructor con todas las variables.
    public Venta( Integer item, Integer idcliente, Integer idempleado, Integer idproducto, String Numserie, String DescripcionP, Double precio, Integer cantidad, Double subtotal, Double monto, String estado) {
        this.id = id;
        this.item = item;
        this.idcliente = idcliente;
        this.idempleado = idempleado;
        this.idproducto = idproducto;
        this.Numserie = Numserie;
        this.DescripcionP = DescripcionP;
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.monto = monto;
        this.estado = estado;
    }
    
    //Obtener id.
    public Integer getId() {
        return id;
    }
    //Establecer id.
    public void setId(Integer id) {
        this.id = id;
    }
    //Obtener item.
    public Integer getItem() {
        return item;
    }
    //Establecer item.
    public void setItem(Integer item) {
        this.item = item;
    }
    //Obtener id del cliente.
    public Integer getIdcliente() {
        return idcliente;
    }
    //Establecer id del cliente.
    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }
    //Obtener el id del empleado.
    public Integer getIdempleado() {
        return idempleado;
    }
    //Establecer el id del empleado.
    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }
    //Obtener el id del producto.
    public Integer getIdproducto() {
        return idproducto;
    }
    //Establecer el id del producto.
    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }
    //Obtener numero de serie de la venta.
    public String getNumserie() {
        return Numserie;
    }
   //Establecer el numero de serie de la venta.
    public void setNumserie(String Numserie) {
        this.Numserie = Numserie;
    }
   //Obtener la descripcion del producto.
    public String getDescripcionP() {
        return DescripcionP;
    }
    //Establecer descripcion del producto.
    public void setDescripcionP(String DescripcionP) {
        this.DescripcionP = DescripcionP;
    }
    //Obtener fecha de venta.
    public String getFecha() {
        return fecha;
    }
    //Establecer fecha de venta.
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    //Obtener precio de venta.
    public Double getPrecio() {
        return precio;
    }
    //Establecer precio de venta.
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    //Obtener la cantidad de productos selecionados.
    public Integer getCantidad() {
        return cantidad;
    }
    //Establecer la cantidad de productos selecionados.
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    //Obtner el subtotal de la venta.
    public Double getSubtotal() {
        return subtotal;
    }
    //Establecer el subtotal de la venta.
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    //Obtener el monto.
    public Double getMonto() {
        return monto;
    }
    //Establecer el monto.
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    //Obtener el estado de la venta.
    public String getEstado() {
        return estado;
    }
    //Establecer el estado de la venta.
    public void setEstado(String estado) {
        this.estado = estado;
    }
    //Se obtiene el codigo.
    public int getCod() {
        return cod;
    }
    //Se establece el codigo.
    public void setCod(int cod) {
        this.cod = cod;
    }
    
    
}
