package com.example.williamfelipe.myuniversity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;


/**
 * Created by WilliamFelipe on 2/07/2016.
 */
public class PopupPorcentaje extends DialogFragment {

    String quePorcentaje;
    String idMateriaMatricula;
    String idCorte;
    String direccionCambioPorcentaje = "http://vawdb.freeoda.com/VAW/Cambiar_porcentaje_corte.php";
    ManejoDB manejoDB;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.popup_porcentaje, null))
                .setTitle("Porcentaje")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText corteIngresado = (EditText) getDialog().findViewById(R.id.PorcentajeCorte);
                String valorCorte = corteIngresado.getText().toString();


               //String tipoQuery = "update";
               String queHacer = "cambiarPorcentaje";
               //ManejoDB manejoDB = new ManejoDB(tipoQuery);

                if(quePorcentaje.equals("primero")){
                    manejoDB.execute(queHacer,direccionCambioPorcentaje,idCorte,valorCorte,idMateriaMatricula);

                }else if(quePorcentaje.equals("segundo")){
                    manejoDB.execute(queHacer,direccionCambioPorcentaje,idCorte,valorCorte,idMateriaMatricula);

                }else if(quePorcentaje.equals("tercero")){
                    manejoDB.execute(queHacer,direccionCambioPorcentaje,idCorte,valorCorte,idMateriaMatricula);

                }




            }
        });
        return builder.create();
    }

    public void setQuePorcentaje(String quePorcentaje) {
        this.quePorcentaje = quePorcentaje;
    }

    public void setIdMateriaMatricula(String idMateriaMatricula) {
        this.idMateriaMatricula = idMateriaMatricula;
    }

    public void setIdCorte(String idCorte) {
        this.idCorte = idCorte;
    }

    public void setManejoDB(ManejoDB manejoDB) {
        this.manejoDB = manejoDB;
    }
}
