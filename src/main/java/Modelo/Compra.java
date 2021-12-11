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
public class Compra {
    private int id;
    private Cliente cliente;
    private String numeroSerie;
    private String fecha;
    private Double monto;
    private String estado;
    
    private CarritoDAO detallecompras = new CarritoDAO();

    public Compra(Cliente cliente, String numeroSerie, String fecha, Double monto, String estado, CarritoDAO detallecompras) {
        this.cliente = cliente;
        this.numeroSerie = numeroSerie;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.detallecompras=detallecompras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CarritoDAO getDetallecompras() {
        return detallecompras;
    }

    public void setDetallecompras(CarritoDAO detallecompras) {
        this.detallecompras = detallecompras;
    }
    
    
}
