package com.example.ricar.polacos;

import android.content.Context;

import java.util.ArrayList;

public class ControlDatos_Class {

    Context miContexto;
    private SQLite_Class controlDB;

    public ControlDatos_Class(Context elContexto){
        miContexto = elContexto;
        controlDB = new SQLite_Class(miContexto);
    }//Fin Constructor  =======================

    public void InsertaCliente(Clientes_Class Persona){
        controlDB.InsertaCliente(Persona);
    }//Fin InsertaCliente  =======================

    public void InsertaProducto(Productos_Class Producto){
        controlDB.InsertaProducto(Producto);
    }

    public void InsertaFactura(Facturas_Class Factura){
        controlDB.InsertaFactura(Factura);
    }

    public void GuardaCliente(String elnombre, String eltelefono){
        Clientes_Class Persona = new Clientes_Class();
        Persona.clienteTelefono= eltelefono;
        Persona.clienteNombre=elnombre;
        controlDB.InsertaCliente(Persona);
    }//Fin GuardaCliente  =======================

    public void GuardaProducto(String elnombre, String elprecio){
        Productos_Class Producto = new Productos_Class();
        Producto.precioProducto= elprecio;
        Producto.nombreProducto=elnombre;
        controlDB.InsertaProducto(Producto);
    }

    public void GuardaFactura(String elcliente, String elproducto,String lacantidad, String lafecha ){
        Facturas_Class Factura = new Facturas_Class();
        Factura.cliente=elcliente;
        Factura.producto=elproducto;
        Factura.cantidad=lacantidad;
        Factura.fecha=lafecha;
        controlDB.InsertaFactura(Factura);
    }

    public ArrayList<String> ConsultaClientes(){
        if (controlDB.CuentaFilas("Clientes") <= 0){
            GuardaCliente("Cliente Contado", "0000-0000");
        }
        return controlDB.ConsultaClientes();
    }//ConsultaClientes =======================

    public ArrayList<String> ConsultaProductos(){
        if (controlDB.CuentaFilas("Productos") <= 0){
            GuardaProducto("Producto Contado", "0000");
        }
        return controlDB.ConsultaProductos();
    }

    public ArrayList<String> ConsultaFactura(){
        if (controlDB.CuentaFilas("Facturas") <= 0){
            GuardaFactura("Cliente Contado", "Producto Contado", "0", "1/1/2016");
        }
        return controlDB.ConsultaFacturas();
    }
}
