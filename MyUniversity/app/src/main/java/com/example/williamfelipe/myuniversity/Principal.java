package com.example.williamfelipe.myuniversity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by WilliamFelipe on 13/07/2016.
 */
public class Principal extends AppCompatActivity {
    String direccionConsultaMaterias = "http://vawdb.freeoda.com/VAW/Consulta_materias.php";
    ListView listaDeMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.principal);

        listaDeMaterias = (ListView) findViewById(R.id.lista_de_materias);
        String tipoQuery = "consulta";
        String queHacer = "listarMaterias";

        String postExecute = "nomMateria";
        final ManejoDB manejoDB = new ManejoDB(tipoQuery,this,postExecute, listaDeMaterias);
        System.out.println("Execute");
        manejoDB.execute(queHacer,direccionConsultaMaterias);

        String postExecute2 = "idMateria";
        final ManejoDB manejoDB1 = new ManejoDB(tipoQuery,postExecute2);
        manejoDB1.execute(queHacer,direccionConsultaMaterias);

        String posExecute3 = "idMateriaMatriculada";
        final ManejoDB manejoDB2 = new ManejoDB(tipoQuery,posExecute3);
        manejoDB2.execute(queHacer,direccionConsultaMaterias);

        listaDeMaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Calcula_notas.class);
                System.out.println("intent");
                intent.putExtra("nombreMateria",manejoDB.getListaFinal().get(position));
                intent.putExtra("codigoMateria",manejoDB1.getListaFinal().get(position));
                intent.putExtra("codigoMatricula",manejoDB2.getListaFinal().get(position));
                startActivity(intent);
            }
        });

    }



}