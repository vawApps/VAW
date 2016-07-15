package com.example.williamfelipe.myuniversity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by WilliamFelipe on 13/07/2016.
 */
public class Principal extends AppCompatActivity implements ManejoDB.OnPostExecute{

    String direccionConsultaMaterias = "http://vawdb.freeoda.com/VAW/Consulta_materias.php"; // direccion del los datos en JSON
    ListView listaDeMaterias; // lista donde se mostraran los datos de la consulta
    ArrayList arrayList = new ArrayList(); // array donde se depositaran los datos de la consulta

    ManejoDB manejoDB = new ManejoDB("CONSULTA","nomMateria",direccionConsultaMaterias,"");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.principal);

        listaDeMaterias = (ListView) findViewById(R.id.lista_de_materias);//cast de la lista donde mostraremos la consulta

        //trae los nombres de las materias matriculaas y las muestra en el listView
        manejoDB.delegate = this;
        manejoDB.execute();

        //Traer los id de cada materia
        final ManejoDB manejoDB1 = new ManejoDB("CONSULTA","idMateria",direccionConsultaMaterias,"");
        manejoDB1.execute();

        //el id que tiene la matriculada la materia
        final ManejoDB manejoDB2 = new ManejoDB("CONSULTA","idMateriaMatriculada",direccionConsultaMaterias,"");
        manejoDB2.execute();

        listaDeMaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Calcula_notas.class);
                intent.putExtra("nombreMateria",manejoDB.getListaFinal().get(position));
                intent.putExtra("codigoMateria",manejoDB1.getListaFinal().get(position));
                intent.putExtra("codigoMatricula",manejoDB2.getListaFinal().get(position));
                startActivity(intent);
            }
        });


    }


    @Override
    public void Execute() {
        arrayList = manejoDB.getListaFinal();
        ArrayAdapter arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrayList);
        listaDeMaterias.setAdapter(arrayAdapter);
    }
}