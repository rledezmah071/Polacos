package com.example.ricar.polacos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    Button botonCliente;
    Button botonProducto;
    Button botonSalir;
    Button botonFactura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaPantalla();
    }


    public void inicializaPantalla(){
        botonCliente = (Button) findViewById(R.id.botonCliente);
        botonCliente.setOnClickListener(this);
        botonSalir = (Button) findViewById(R.id.botonSalir);
        botonSalir.setOnClickListener(this);
        botonProducto = (Button) findViewById(R.id.botonProducto);
        botonProducto.setOnClickListener(this);
        botonFactura = (Button) findViewById(R.id.botonFactura);
        botonFactura.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.botonCliente:
                startActivity(new Intent(MainActivity.this, Clientes_Activity.class));
                break;
            case R.id.botonProducto:
                startActivity(new Intent(MainActivity.this, Producto_Activity.class));
                break;
            case R.id.botonFactura:
                startActivity(new Intent(MainActivity.this, Factura_Activity.class));
                break;
            case R.id.botonSalir:
                Intent elIntent = new Intent(Intent.ACTION_MAIN);
                elIntent.addCategory(Intent.CATEGORY_HOME);
                elIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(elIntent);
                break;
        }
    }
}
