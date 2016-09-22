package com.example.williamfelipe.myuniversity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by VlaX on 23/07/2015.
 */

public class Ceropuntouno extends Activity {

    Button bceropuntouno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ceropuntouno);

        //////////////////ACCION BOTON PARA SIGUIENTE PAGINA ///////////////////////////

        bceropuntouno = (Button)findViewById(R.id.bceropuntouno);
        bceropuntouno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bceropuntouno = new Intent(Ceropuntouno.this, Cerouno.class);
                startActivity(bceropuntouno);
            }
        });

        //////////////////ACCION BOTON PARA SIGUIENTE PAGINA //////////////////////////////
    }

}
