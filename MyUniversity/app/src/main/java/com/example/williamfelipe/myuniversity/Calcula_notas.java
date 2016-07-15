package com.example.williamfelipe.myuniversity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by WilliamFelipe on 28/06/2016.
 */
public class Calcula_notas extends AppCompatActivity implements ManejoDB.OnPostExecute{

    ArrayList<String> listaPorcentajes = new ArrayList<>();

    TextView tituloMateria, notaMateria; // titulo y nota de la materia que se muestra en el toolbar
    EditText porcentaje1, porcentaje2, porcentaje3; // porcentajes de cada corte que se muestran
    EditText notasCorte1, notasCorte2, notasCorte3; // notas par cada corte

    String direccionConsultaPorcentajes = "http://vawdb.freeoda.com/VAW/Consulta_porcentaje_corte.php"; //direccion de la consulta de los porcentajes de cada corte

    ManejoDB manejoDB = new ManejoDB("CONSULTA","porcenCorte",direccionConsultaPorcentajes,"POST"); // Objeto que permite hacer las consultas de los porcentajes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcula_notas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tituloMateria = (TextView) findViewById(R.id.txtAbTitulo); //cast del titulo del toolbar
        notaMateria = (TextView) findViewById(R.id.txtAbSubTitulo); //cast del subtitulo del toolbar

        porcentaje1 = (EditText) findViewById(R.id.Porcentaje1); //cast para el primer porcentaje del corte
        porcentaje2 = (EditText) findViewById(R.id.Porcentaje2); //cast para el segundo porcentaje del corte
        porcentaje3 = (EditText) findViewById(R.id.Porcentaje3); //cast para el tercer porcentaje del corte

        notasCorte1 = (EditText) findViewById(R.id.Nota1); //cast de las notas del primer corte
        notasCorte2 = (EditText) findViewById(R.id.Nota2); //cast de las notas del segundo corte
        notasCorte3 = (EditText) findViewById(R.id.Nota3); //cast de las notas del tercer corte

        String nombreMateria = getIntent().getStringExtra("nombreMateria"); //nombre de la materia que paso por intent desde la clase Principal
        String codigoMateria = getIntent().getStringExtra("codigoMateria"); //codigo de la materia que paso por intent desde la clase Principal
        final String codigoMatricula = getIntent().getStringExtra("codigoMatricula"); //codigo de materia matriculada que paso por intent desde la clase Principal

        tituloMateria.setText(nombreMateria);//Cambio el nombre del titulo del toolbar por el de la materia a la que ingreso
        notaMateria.setText("Perdiste Loco"); // cambio el nombre del subtitulo del toolbar por la nota de la materia a la que ingreso

        String[] variablePOST = {"materiaId"}; // Array con los nombres de las variables POST que estan en el archivo php
        String[] valoresPOST= {codigoMateria}; // Array con los valores que ingresaran por POST

        manejoDB.delegate = this;
        manejoDB.execute(variablePOST,valoresPOST);

        porcentaje1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                PopupPorcentaje popupPorcentaje = new PopupPorcentaje();
                popupPorcentaje.setQuePorcentaje("primero");
                popupPorcentaje.setIdCorte("1");
                popupPorcentaje.setIdMateriaMatricula(codigoMatricula);
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
                popupPorcentaje.setIdMateriaMatricula(codigoMatricula);
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
                popupPorcentaje.setIdMateriaMatricula(codigoMatricula);
                popupPorcentaje.show(fragmentManager,"tagPersonalizado");
            }
        });

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


    @Override
    public void Execute() {
        listaPorcentajes = manejoDB.getListaFinal();
        porcentaje1.setText(listaPorcentajes.get(0));
        porcentaje2.setText(listaPorcentajes.get(1));
        porcentaje3.setText(listaPorcentajes.get(2));
    }
}
