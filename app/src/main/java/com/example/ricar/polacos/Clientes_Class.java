package com.example.ricar.polacos;

public class Clientes_Class {

    public String clienteNombre;
    public String clienteTelefono;

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    public Clientes_Class() {
    }

    public Clientes_Class(String clienteNombre, String clienteTelefono) {
        this.clienteNombre = clienteNombre;
        this.clienteTelefono = clienteTelefono;
    }
}
