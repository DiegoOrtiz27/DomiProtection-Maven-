/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author EMANUEL ORTIZ
 */
public class historialCompra {
    private String referencia;
    private String nombreProducto;
    private int codigo;
    private int cantidad;
    private double precio;
    private double monto;
    private String fechaCompra;

    public historialCompra(String referencia, String nombreProducto, int codigo, int cantidad, double precio, double monto, String fechaCompra) {
        this.referencia = referencia;
        this.nombreProducto = nombreProducto;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.monto = monto;
        this.fechaCompra = fechaCompra;
    }
    

    public historialCompra() {
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
}
