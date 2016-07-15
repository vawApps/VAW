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

    EditText corteIngresado; // Valor del corte que ingrese
    String quePorcentaje; // Saber que corte esta modificando
    String idMateriaMatricula; //Cual es el id de la matricula de la materia
    String idCorte; // Cual es el id del corte
    String direccionCambioPorcentaje = "http://vawdb.freeoda.com/VAW/Cambiar_porcentaje_corte.php";

    ManejoDB manejoDB = new ManejoDB("",direccionCambioPorcentaje,"POST"); //Objeto que me permite hacer la modificacion del porcentaje


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

                corteIngresado = (EditText) getDialog().findViewById(R.id.PorcentajeCorte); // cast del valor del corte que ingrese

                String[] variablePOST = {"corteId","porcenCorte","materiaMatriculadaId"}; // Array con los nombres de las variables POST que estan en el archivo php
                String[] valoresPOST= {getIdCorte(),corteIngresado.getText().toString(),getIdMateriaMatricula()}; // Array con los valores que ingresaran por POST


                if(quePorcentaje.equals("primero")){
                    manejoDB.execute(variablePOST,valoresPOST);

                }else if(quePorcentaje.equals("segundo")){
                    manejoDB.execute(variablePOST,valoresPOST);

                }else if(quePorcentaje.equals("tercero")){
                    manejoDB.execute(variablePOST,valoresPOST);

                }




            }
        });
        return builder.create();
    }

    public String getQuePorcentaje() {
        return quePorcentaje;
    }

    public void setQuePorcentaje(String quePorcentaje) {
        this.quePorcentaje = quePorcentaje;
    }

    public String getIdMateriaMatricula() {
        return idMateriaMatricula;
    }

    public void setIdMateriaMatricula(String idMateriaMatricula) {
        this.idMateriaMatricula = idMateriaMatricula;
    }

    public String getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(String idCorte) {
        this.idCorte = idCorte;
    }
}
