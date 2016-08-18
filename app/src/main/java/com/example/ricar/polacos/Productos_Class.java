package com.example.ricar.polacos;

public class Productos_Class {
    public String nombreProducto;
    public String precioProducto;

    public Productos_Class() {
    }

    public Productos_Class(String nombreProducto, String precioProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }
}
