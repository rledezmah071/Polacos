package com.example.ricar.polacos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Producto_Activity extends Activity implements View.OnClickListener {


    Button guardaProducto;
    Button consultaProducto;
    EditText nombreProducto;
    EditText precioProducto;
    ListView listaProducto;
    private Util_TextoFechas UtileStrings = new Util_TextoFechas();
    private Util_UI UtilesUI = new Util_UI();
    private ControlDatos_Class ControlDatos_Class = new ControlDatos_Class(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        inicializaPantalla();
    }

    public void inicializaPantalla(){

        guardaProducto = (Button)findViewById(R.id.guardaProducto);
        consultaProducto=(Button)findViewById(R.id.consultaProducto);
        nombreProducto=(EditText) findViewById(R.id.nombreProducto);
        precioProducto=(EditText)findViewById(R.id.precioProducto);
        listaProducto=(ListView)findViewById(R.id.listaProducto);
        guardaProducto.setOnClickListener(this);
        consultaProducto.setOnClickListener(this);
    }

    private void ListViewClick() {
        listaProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int laPosicion = position + 1;
                String elementoSeleccionado = (String) listaProducto.getItemAtPosition(position);
                String[] TextoPartido = UtileStrings.CortaTextos(elementoSeleccionado, ",");
                precioProducto.setText(TextoPartido[1]);
                nombreProducto.setText(TextoPartido[0]);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guardaProducto:
                ControlDatos_Class.GuardaProducto(nombreProducto.getText().toString(),precioProducto.getText().toString());
                nombreProducto.setText("");
                precioProducto.setText("");
                break;
            case R.id.consultaProducto:
                listaProducto.setAdapter(UtilesUI.CargaArrayAdapter(this, ControlDatos_Class.ConsultaProductos()));
                break;
        }
    }
}
