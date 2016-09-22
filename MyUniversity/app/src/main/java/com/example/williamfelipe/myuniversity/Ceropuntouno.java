package com.example.williamfelipe.myuniversity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by VlaX on 23/07/2015.
 */

public class Ceropuntouno extends AppCompatActivity implements ManejoDB.OnPostExecute{
    Spinner lista;
    Spinner listauno;
    Spinner listados;

    Button bcerouno;

    ArrayList<String>  arrayListaUniversidades = new ArrayList<>();
    ArrayList<String>  arrayListaSedes = new ArrayList<>();
    int queSpinner;

    /*String[] universidades = {"Selecciona tu Universidad","Universidad Cooperativa","Universidad Catolica","Universidad Autonoma"};
    String[] sedes = {"Selecciona tu Sede"};
    String[] programas = {"Selecciona el Programa"};

    String[] sedeucc = {"Selecciona tu Sede","Cali","Bogota"};
    String[] sedecuc = {"Selecciona tu Sede","Cali","Popayan"};
    String[] sedeuao = {"Selecciona tu Sede","Cali","Pasto"};

    String[] programacaliucc = {"Selecciona el Programa","Ingenieria Sistemas","Arquitectura","Psicologia"};
    String[] programabogotaucc = {"Selecciona el Programa","Ingenieria Industrial","Psicologia"};

    String[] programacalicuc = {"Selecciona el Programa","Diseño grafico","gastronomia"};
    String[] programapopayancuc = {"Selecciona el Programa","Ingenieria Industrial","Diseño de modas"};
    String[] programapastocuc = {"Selecciona el Programa","Licenciatura Matematica","Psicologia"};

    String[] programacaliuao = {"Selecciona el Programa","Ingenieria Multimedia","Gastronomia"};
    String[] programapastouao = {"Selecciona el Programa","Medicina","Odontologia","Salud Ocupacional"};*/

    String direccionConsultaUniversidades = "http://vawdb.freeoda.com/VAW/Consulta_universidades.php";
    ManejoDB nombreUniversidades = new ManejoDB("CONSULTA","nomUniversidad",direccionConsultaUniversidades,"");
    ManejoDB idUniversidades = new ManejoDB("CONSULTA","idUniversidad",direccionConsultaUniversidades,"");

    String direccionConsultaSedes = "http://vawdb.freeoda.com/VAW/Consulta_sedes_universidades.php";
    ManejoDB nombreSedes = new ManejoDB("CONSULTA","descUniversidad",direccionConsultaSedes,"POST");

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cero);

        lista = (Spinner)findViewById(R.id.universidad);
        listauno = (Spinner)findViewById(R.id.sede);
        listados = (Spinner)findViewById(R.id.programa);

        listauno.setEnabled(false);
        listados.setEnabled(false);

        //////////////////ACCION BOTON PARA SIGUIENTE PAGINA ///////////////////////////

        bcerouno = (Button)findViewById(R.id.bcerouno);
        bcerouno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bcerouno = new Intent(Ceropuntouno.this, Cerouno.class);
                startActivity(bcerouno);
            }
        });

        //////////////////ACCION BOTON PARA SIGUIENTE PAGINA //////////////////////////////

        queSpinner = 1;
        nombreUniversidades.delegate = this;
        nombreUniversidades.execute();

        idUniversidades.execute();

        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(i != 0){
                    listauno.setEnabled(true);
                    listados.setEnabled(false);


                    //ArrayList<String> codigos = idUniversidades.getListaFinal();
                    //String codigoUniversidad = codigos.get(i);



                    String[] variablesPOST = {"idUniversi"};
                    String[] valoresPOST = {idUniversidades.getListaFinal().get(i)};

                    queSpinner = 2;
                    nombreSedes.delegate = nombreUniversidades.delegate;
                    nombreSedes.execute(variablesPOST,valoresPOST);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


       /* ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, universidades);

        final ArrayAdapter<String> adaptadorsede = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sedes);
        final ArrayAdapter<String> adaptadorsedeucc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sedeucc);
        final ArrayAdapter<String> adaptadorsedecuc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sedecuc);
        final ArrayAdapter<String> adaptadorsedeuao = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sedeuao);

        final ArrayAdapter<String> adaptadorprograma = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, programas);
        final ArrayAdapter<String> adaptadorprogramacaliucc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, programacaliucc);
        final ArrayAdapter<String> adaptadorprogramabogotaucc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, programabogotaucc);
        final ArrayAdapter<String> adaptadorprogramacalicuc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, programacalicuc);
        final ArrayAdapter<String> adaptadorprogramapopayancuc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, programapopayancuc);
        final ArrayAdapter<String> adaptadorprogramapastocuc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, programapastocuc);

        lista.setAdapter(adaptador);
        listauno.setAdapter(adaptadorsede);
        listados.setAdapter(adaptadorprograma);

        listauno.setEnabled(false);
        listados.setEnabled(false);

        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                switch (i) {
                    case 1:

                        listauno.setAdapter(adaptadorsedeucc);
                        listauno.setEnabled(true);
                        listados.setEnabled(false);

                        break;

                    case 2:
                        listauno.setAdapter(adaptadorsedecuc);
                        listauno.setEnabled(true);
                        listados.setEnabled(false);

                        break;

                    case 3:
                        listauno.setAdapter(adaptadorsedeuao);
                        listauno.setEnabled(true);
                        listados.setEnabled(false);

                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listauno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {


                    switch (i) {
                        case 1:
                            listados.setEnabled(true);
                            switch (1) {
                                case 1:
                                listados.setAdapter(adaptadorprogramacaliucc);
                            }
                            break;

                        case 2:
                            listados.setEnabled(true);
                            listados.setAdapter(adaptadorprogramabogotaucc);
                            break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                switch (i) {
                    case 1:

                        break;

                    case 2:

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    @Override
    public void Execute() {
        switch (queSpinner) {
            case 1:
                arrayListaUniversidades = nombreUniversidades.getListaFinal();
                ArrayAdapter adapterNombres;
                adapterNombres = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrayListaUniversidades);
                lista.setAdapter(adapterNombres);
                break;

            case 2:
                arrayListaSedes = nombreSedes.getListaFinal();
                ArrayAdapter adapterSedes;
                adapterSedes = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrayListaSedes);
                listauno.setAdapter(adapterSedes);
                break;
        }

    }




}