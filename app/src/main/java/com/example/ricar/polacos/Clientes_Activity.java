package com.example.ricar.polacos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Clientes_Activity extends Activity implements View.OnClickListener{

    Button guardaCliente;
    Button consultaCliente;
    EditText nombreCliente;
    EditText telefonoCliente;
    ListView listaCliente;
    private Util_TextoFechas UtileStrings = new Util_TextoFechas();
    private Util_UI UtilesUI = new Util_UI();
    private ControlDatos_Class ControlDatos_Class = new ControlDatos_Class(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_);
        inicializaPantalla();
    }

    public void inicializaPantalla(){

        guardaCliente = (Button)findViewById(R.id.clienteGuardar);
        consultaCliente=(Button)findViewById(R.id.clienteConsultar);
        nombreCliente=(EditText) findViewById(R.id.nombreCliente);
        telefonoCliente=(EditText)findViewById(R.id.telefonoCliente);
        listaCliente=(ListView)findViewById(R.id.clienteLista);
        guardaCliente.setOnClickListener(this);
        consultaCliente.setOnClickListener(this);
    }

    private void ListViewClick() {
        listaCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int laPosicion = position + 1;
                String elementoSeleccionado = (String) listaCliente.getItemAtPosition(position);
                String[] TextoPartido = UtileStrings.CortaTextos(elementoSeleccionado, ",");
                telefonoCliente.setText(TextoPartido[1]);
                nombreCliente.setText(TextoPartido[0]);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clienteGuardar:
                ControlDatos_Class.GuardaCliente(nombreCliente.getText().toString(),telefonoCliente.getText().toString());
                nombreCliente.setText("");
                telefonoCliente.setText("");
                break;
            case R.id.clienteConsultar:
                listaCliente.setAdapter(UtilesUI.CargaArrayAdapter(this, ControlDatos_Class.ConsultaClientes()));
                break;
        }
    }
}
