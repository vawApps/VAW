package com.example.williamfelipe.myuniversity;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by WilliamFelipe on 30/06/2016.
 * Los parametros ingresados en la posicion cero y uno
 * para esta clase estan reservados para el 'que ejecutar' y la url que contiene el json
 */

public class ManejoDB extends AsyncTask<String, Void, String> {
    ArrayList<String> listaFinal = new ArrayList<>();
    String datosBuffer;

    //Campos de constructor
    String tipoQuery; //saber si es un select, un update o un delete
    String campoJson; // que datos quiere en el array - cada constructor necesita tenerlo
    ListView listView; //lista donde mostrar los datos
    Context context;
    EditText editText1, editText2, editText3; // textos donde van los porcentajes

    public ManejoDB(String tipoQuery) {
        this.tipoQuery = tipoQuery;
    }

    public ManejoDB(String tipoQuery, String campoJson) {
        this.tipoQuery = tipoQuery;
        this.campoJson = campoJson;
    }

    //constructor que se usa para mostrar en una lista los valores de la consulta
    public ManejoDB(String tipoQuery, Context context, String campoJson,ListView listView) {
        this.tipoQuery = tipoQuery;
        this.context = context;
        this.campoJson = campoJson;
        this.listView = listView;
    }

    //constructor que se usa para mostrara en los edittext de los porcentajes
    public ManejoDB(String tipoQuery, String campoJson, EditText editText1, EditText editText2, EditText editText3) {
        this.tipoQuery = tipoQuery;
        this.campoJson = campoJson;
        this.editText1 = editText1;
        this.editText2 = editText2;
        this.editText3 = editText3;
    }

    @Override
    protected String doInBackground(String... params) {
        //Estos dos primeros parametros eimpre van hacer para eso RESERVADS utilizar del 2 en adelante
        String queHacer = params[0];//que ejecutar
        String jsonPhp = params[1];//direccion del json

        if(queHacer.equals("listarMaterias")){  //procedimiento que trae los datos de la consulta hecha a la tabla materias 'Consulta_materias.php'
            try {
                String line = null; //Guarda la linea que lee del json
                URL url = new URL(jsonPhp);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();

                if(bufferedReader != null){
                    while((line = bufferedReader.readLine()) != null){
                        stringBuffer.append(line+" 'n");
                    }
                }else{
                    return null;
                }

                return stringBuffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(queHacer.equals("porcentajesCorte")){ //procedimiento para mostrar los datos de la materia en layout 'calcula_nota' seleccionada en el layout 'lista_materias'
            try {
                String codigoMateria = params[2];
                String line = null; //Guarda la linea que lee del json
                URL url = new URL(jsonPhp);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                //el primer encode el nombre de la variable tiene q ser igual al del php que pasa por el metodo POST
                //el segundo encode el nombre de la variable son los declarados que contienen los valores de los parametros
                String postData = URLEncoder.encode("materiaId","UTF-8")+"="+URLEncoder.encode(codigoMateria,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();

                if(bufferedReader != null){
                    while((line = bufferedReader.readLine()) != null){
                        stringBuffer.append(line+" 'n");
                        System.out.println(line);
                    }
                }else{
                    return null;
                }

                return stringBuffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(queHacer.equals("cambiarPorcentaje")){
            try {
                String idCorte = params[2];
                String pocentajeCorte = params[3];
                String idMateriaMatricula = params[4];
                String line = null; //Guarda la linea que lee del json
                URL url = new URL(jsonPhp);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                //el primer encode el nombre de la variable tiene q ser igual al del php que pasa por el metodo POST
                //el segundo encode el nombre de la variable son los declarados que contienen los valores de los parametros
                String postData = URLEncoder.encode("corteId","UTF-8")+"="+URLEncoder.encode(idCorte,"UTF-8")+"&"
                        +URLEncoder.encode("porcenCorte","UTF-8")+"="+URLEncoder.encode(pocentajeCorte,"UTF-8")+"&"
                        +URLEncoder.encode("materiaMatriculadaId","UTF-8")+"="+URLEncoder.encode(idMateriaMatricula,"UTF-8");
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();

                if(bufferedReader != null){
                    while((line = bufferedReader.readLine()) != null){
                        stringBuffer.append(line+" 'n");
                        System.out.println(line);
                    }
                }else{
                    return null;
                }

                return stringBuffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void arrayMaterias(){
        try {
            JSONArray jsonArray = new JSONArray(getDatosBuffer());
            JSONObject jsonObject = null;
            listaFinal.clear();

            for(int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                String datosLista = jsonObject.getString(campoJson.toString());
                listaFinal.add(datosLista);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        setDatosBuffer(s);

        //si es una consulta ejecuta el metodo que llena un array con los datos de la consulta
        if(tipoQuery.equals("consulta")){
            arrayMaterias();

            if(getCampoJson().equals("nomMateria")){
                adaptarLista();
            }

            if(getCampoJson().equals("porcenCorte")){
                adaptarPorcentajes();
            }
        }



    }

    public void adaptarLista(){
        ArrayAdapter arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_expandable_list_item_1,listaFinal);
        listView.setAdapter(arrayAdapter);
    }

    public void adaptarPorcentajes(){
        editText1.setText(listaFinal.get(0));
        editText2.setText(listaFinal.get(1));
        editText3.setText(listaFinal.get(2));
    }

    public String getCampoJson() {
        return campoJson;
    }

    public void setDatosBuffer(String datosBuffer) {
        this.datosBuffer = datosBuffer;
    }

    public String getDatosBuffer() {
        return datosBuffer;
    }

    public ArrayList<String> getListaFinal() {
        return listaFinal;
    }




}
