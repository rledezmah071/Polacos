package com.example.ricar.polacos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLite_Class extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Polacos.db";

    private static final String CREA_TABLA_CLIENTES =
            "CREATE TABLE IF NOT EXISTS Clientes (id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    "nombre TEXT, telefono TEXT )";

    private static final String CREA_TABLA_PRODUCTOS =
            "CREATE TABLE IF NOT EXISTS Productos (id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    "nombre TEXT, precio TEXT )";

    private static final String CREA_TABLA_FACTURAS =
            "CREATE TABLE IF NOT EXISTS Facturas (id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    "cliente TEXT, producto TEXT, cantidad TEXT, fecha TEXT )";

    public SQLite_Class(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }//Fin Constructor  =======================

    public SQLite_Class(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }//Fin Constructor  =======================

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREA_TABLA_CLIENTES);
        db.execSQL(CREA_TABLA_PRODUCTOS);
        db.execSQL(CREA_TABLA_FACTURAS);
    }//Fin onCreate  =======================

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Borra las tablas si existen (Es un requisito)
        db.execSQL("DROP TABLE IF EXISTS Clientes");
        db.execSQL("DROP TABLE IF EXISTS Productos");
        db.execSQL("DROP TABLE IF EXISTS Facturas");
        onCreate(db);// Crea las tablas otra vez
    }//Fin onUpgrade =======================

    public int InsertaCliente(Clientes_Class persona) {
        ContentValues values = new ContentValues();
        values.put("nombre", persona.clienteNombre);
        values.put("telefono", persona.clienteTelefono);

        SQLiteDatabase db = this.getWritableDatabase();
        long Cliente_Id = db.insert("Clientes", null, values);
        db.close();// Cierra la conexión con la BD
        return (int) Cliente_Id;
    }// Fin InsertaCliente =======================

    public int InsertaProducto(Productos_Class producto) {
        ContentValues values = new ContentValues();
        values.put("nombre", producto.nombreProducto);
        values.put("precio", producto.precioProducto);

        SQLiteDatabase db = this.getWritableDatabase();
        long Producto_Id = db.insert("Productos", null, values);
        db.close();// Cierra la conexión con la BD
        return (int) Producto_Id;
    }

    public int InsertaFactura(Facturas_Class factura) {
        ContentValues values = new ContentValues();
        values.put("cliente", factura.cliente);
        values.put("producto", factura.producto);
        values.put("cantidad", factura.cantidad);
        values.put("fecha", factura.fecha);

        SQLiteDatabase db = this.getWritableDatabase();
        long Factura_Id = db.insert("Facturas", null, values);
        db.close();// Cierra la conexión con la BD
        return (int) Factura_Id;
    }

    public int CuentaFilas(String Tabla){
        int CantElementos = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =  "SELECT Count(id) as Cantidad FROM "+Tabla;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CantElementos = cursor.getInt(cursor.getColumnIndex("Cantidad"));
            } while (cursor.moveToNext());
        }//if
        cursor.close();
        db.close();
        return CantElementos;
    }//Fin CuentaFilas =======================

    public ArrayList<String> ConsultaClientes() {
        String Nombre = "";
        String Telefono = "";
        ArrayList<String> ListaPersonas = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor elCursor = db.rawQuery("SELECT * FROM Clientes", null);
        while(elCursor.moveToNext())
        {
            Telefono = elCursor.getString(elCursor.getColumnIndex("telefono"));
            Nombre = elCursor.getString(elCursor.getColumnIndex("nombre"));
            ListaPersonas.add(Nombre + ", " + Telefono);
        }//Fin while
        elCursor.close();
        db.close();
        return ListaPersonas;
    } //Fin ConsultaClientes =======================

    public ArrayList<String> ConsultaProductos() {
        String Nombre = "";
        String Precio = "";
        ArrayList<String> ListaProductos = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor elCursor = db.rawQuery("SELECT * FROM Productos", null);
        while(elCursor.moveToNext())
        {
            Precio = elCursor.getString(elCursor.getColumnIndex("precio"));
            Nombre = elCursor.getString(elCursor.getColumnIndex("nombre"));
            ListaProductos.add(Nombre + ", " + "₡"+Precio);
        }//Fin while
        elCursor.close();
        db.close();
        return ListaProductos;
    }

    public ArrayList<String> ConsultaFacturas() {
        String Cliente = "";
        String Producto = "";
        String Cantidad = "";
        String Fecha = "";
        ArrayList<String> ListaFacturas = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor elCursor = db.rawQuery("SELECT * FROM Facturas", null);
        while(elCursor.moveToNext())
        {
            Cliente = elCursor.getString(elCursor.getColumnIndex("cliente"));
            Producto = elCursor.getString(elCursor.getColumnIndex("producto"));
            Cantidad = elCursor.getString(elCursor.getColumnIndex("cantidad"));
            Fecha = elCursor.getString(elCursor.getColumnIndex("fecha"));
            ListaFacturas.add(Cliente + ", " + Producto + ", " + Cantidad + " unidades, " + Fecha);
        }//Fin while
        elCursor.close();
        db.close();
        return ListaFacturas;
    }

}
