package com.example.williamfelipe.myuniversity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VlaX on 23/07/2015.
 */
public class Cerodos extends Activity {

    private Spinner cortecerodos;
    private Spinner materiascerodos;

    private List lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cerodos);
        DatosPorDefecto();
        DatosPorDefecto2();

    }

    private void DatosPorDefecto() {
        cortecerodos = (Spinner)findViewById(R.id.cortecerodos);
        lista = new ArrayList();
        cortecerodos = (Spinner) this.findViewById(R.id.cortecerodos);
        lista.add("Selecciona número de cortes");
        lista.add("Dos cortes");
        lista.add("Tres cortes");
        lista.add("Cuatro cortes");
        lista.add("Cinco cortes");
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cortecerodos.setAdapter(adaptador);
    }
    private void DatosPorDefecto2() {
        materiascerodos = (Spinner)findViewById(R.id.materiascerodos);
        lista = new ArrayList();
        materiascerodos = (Spinner) this.findViewById(R.id.materiascerodos);
        lista.add("Seleccione las materias matriculadas");
        lista.add("Materia 1");
        lista.add("Materia 2");
        lista.add("Materia 3");
        lista.add("Materia 4");
        lista.add("Materia 5");
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materiascerodos.setAdapter(adaptador);
    }



}
