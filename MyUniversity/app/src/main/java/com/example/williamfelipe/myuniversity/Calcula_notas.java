package com.example.williamfelipe.myuniversity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by WilliamFelipe on 28/06/2016.
 */
public class Calcula_notas extends AppCompatActivity {

    TextView tituloMateria, notaMateria;
    EditText porcentaje1, porcentaje2, porcentaje3;
    EditText notasCorte1, notasCorte2, notasCorte3;
    String direccionConsultaPorcentajes = "http://vawdb.freeoda.com/VAW/Consulta_porcentaje_corte.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcula_notas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tituloMateria = (TextView) findViewById(R.id.txtAbTitulo);
        notaMateria = (TextView) findViewById(R.id.txtAbSubTitulo);

        //Datos que paso de la clase Main por el OnclickItemList
        final String materia = getIntent().getStringExtra("nombreMateria"); //nombre de la materia
        String codigo = getIntent().getStringExtra("codigoMateria"); //codigo de la materia
        final String matriculada = getIntent().getStringExtra("codigoMatricula");//codigo de materiamatriculada

        String tipoQuery = "consulta";
        String postExecute = "porcenCorte";
        String queHacer = "porcentajesCorte";

        //Cambio el nombre del titulo del toolbar por el de la materia a la que ingreso
        tituloMateria.setText(materia);

        porcentaje1 = (EditText) findViewById(R.id.Porcentaje1);
        porcentaje2 = (EditText) findViewById(R.id.Porcentaje2);
        porcentaje3 = (EditText) findViewById(R.id.Porcentaje3);

        //Consultar los porcentajes de cada corte para la materia seleccionada
        final ManejoDB manejoDB = new ManejoDB(tipoQuery,postExecute, porcentaje1, porcentaje2, porcentaje3);
        manejoDB.execute(queHacer,direccionConsultaPorcentajes,codigo);

        //Objeto para cambiar los porcentajes
        String tipoQuery2 = "update";
        final ManejoDB manejoDB1 = new ManejoDB(tipoQuery2);

        porcentaje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                PopupPorcentaje popupPorcentaje = new PopupPorcentaje();
                popupPorcentaje.setQuePorcentaje("primero");
                popupPorcentaje.setIdCorte("1");
                popupPorcentaje.setIdMateriaMatricula(matriculada);
                popupPorcentaje.setManejoDB(manejoDB1);
                popupPorcentaje.show(fragmentManager,"tagPersonalizado");
            }
        });

        porcentaje2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                PopupPorcentaje popupPorcentaje = new PopupPorcentaje();
                popupPorcentaje.setQuePorcentaje("segundo");
                popupPorcentaje.setIdCorte("2");
                popupPorcentaje.setIdMateriaMatricula(matriculada);
                popupPorcentaje.show(fragmentManager,"tagPersonalizado");
            }
        });

        porcentaje3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                PopupPorcentaje popupPorcentaje = new PopupPorcentaje();
                popupPorcentaje.setQuePorcentaje("tercero");
                popupPorcentaje.setIdCorte("3");
                popupPorcentaje.setIdMateriaMatricula(matriculada);
                popupPorcentaje.show(fragmentManager,"tagPersonalizado");
            }
        });


        notasCorte1 = (EditText) findViewById(R.id.Nota1);
        notasCorte2 = (EditText) findViewById(R.id.Nota2);
        notasCorte3 = (EditText) findViewById(R.id.Nota3);

        notasCorte1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Calcula_notas.this,PopupListaNotas.class));
            }
        });

        notasCorte2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Calcula_notas.this,PopupListaNotas.class));
            }
        });

        notasCorte3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Calcula_notas.this,PopupListaNotas.class));
            }
        });




    }




}
