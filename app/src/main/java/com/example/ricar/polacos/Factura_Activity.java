package com.example.ricar.polacos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Factura_Activity extends Activity implements View.OnClickListener {

    Button guardaFactura;
    Button consultaFactura;
    EditText clienteFactura;
    EditText productoFactura;
    EditText cantidadFactura;
    EditText fechaFactura;
    ListView listaFactura;
    private Util_TextoFechas UtileStrings = new Util_TextoFechas();
    private Util_UI UtilesUI = new Util_UI();
    private ControlDatos_Class ControlDatos_Class = new ControlDatos_Class(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        inicializaPantalla();

    }

    public void inicializaPantalla(){

        guardaFactura = (Button)findViewById(R.id.facturaGuardar);
        consultaFactura=(Button)findViewById(R.id.facturaConsultar);
        clienteFactura=(EditText) findViewById(R.id.facturaCliente);
        productoFactura=(EditText)findViewById(R.id.facturaProducto);
        cantidadFactura=(EditText)findViewById(R.id.facturaCantidad);
        fechaFactura=(EditText)findViewById(R.id.facturaFecha);
        listaFactura=(ListView)findViewById(R.id.facturaLista);
        guardaFactura.setOnClickListener(this);
        consultaFactura.setOnClickListener(this);
    }

    private void ListViewClick() {
        listaFactura.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int laPosicion = position + 1;
                String elementoSeleccionado = (String) listaFactura.getItemAtPosition(position);
                String[] TextoPartido = UtileStrings.CortaTextos(elementoSeleccionado, ",");
                clienteFactura.setText(TextoPartido[0]);
                productoFactura.setText(TextoPartido[1]);
                cantidadFactura.setText(TextoPartido[2]);
                fechaFactura.setText(TextoPartido[3]);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.facturaGuardar:
                ControlDatos_Class.GuardaFactura(clienteFactura.getText().toString(),productoFactura.getText().toString(),
                cantidadFactura.getText().toString(), fechaFactura.getText().toString());
                clienteFactura.setText("");
                productoFactura.setText("");
                cantidadFactura.setText("");
                fechaFactura.setText("");
                break;
            case R.id.facturaConsultar:
                listaFactura.setAdapter(UtilesUI.CargaArrayAdapter(this, ControlDatos_Class.ConsultaFactura()));
                break;
        }
    }

}
