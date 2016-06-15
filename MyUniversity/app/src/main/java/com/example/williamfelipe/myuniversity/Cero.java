package com.example.williamfelipe.myuniversity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by VlaX on 23/07/2015.
 */
public class Cero extends AppCompatActivity {
    private Spinner spinner1;
    private List lista;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cero);
            DatosPorDefecto();
        }
    private void DatosPorDefecto() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        lista = new ArrayList();
        spinner1 = (Spinner) this.findViewById(R.id.spinner1);
        lista.add("Panchos");
        lista.add("Choripan");
        lista.add("Hamburguesas");
        lista.add("Pollo al horno");
        lista.add("Asado");
        lista.add("Arroz");
        ArrayAdapter adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adaptador);
    }

    }


