package com.example.williamfelipe.myuniversity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by VlaX on 23/07/2015.
 */
public class Cerouno extends Activity {

    Button bcerodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cerouno);

        //////////////////ACCION BOTON PARA SIGUIENTE PAGINA ///////////////////////////

        bcerodos = (Button)findViewById(R.id.bcerodos);
        bcerodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bcerodos = new Intent(Cerouno.this, Cerodos.class);
                startActivity(bcerodos);
            }
        });

        //////////////////ACCION BOTON PARA SIGUIENTE PAGINA //////////////////////////////
    }

}
