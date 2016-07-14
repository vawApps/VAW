package com.example.williamfelipe.myuniversity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * Created by WilliamFelipe on 4/07/2016.
 */
public class PopupListaNotas extends AppCompatActivity {

    Button agregarNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_notas);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int heigth = displayMetrics.heightPixels;

        agregarNota = (Button) findViewById(R.id.agrega_nota);
        agregarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                PopupAnadirNota popupAnadirNota = new PopupAnadirNota();
                popupAnadirNota.show(fragmentManager,"tagPersonalizado");
            }
        });

        getWindow().setLayout((int)(width*.8),(int)(heigth*.9));
    }
}
