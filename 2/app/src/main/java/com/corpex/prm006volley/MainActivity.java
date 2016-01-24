package com.corpex.prm006volley;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Callbacks{
    static ArrayList<Alumno> alumnos = new ArrayList<>();;
    ListView lvAlumnos;
    TareaSecundaria tarea;
    final String myURL = "https://dl.dropboxusercontent.com/u/67422/Android/json/datos.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVistas();
        iniciarGet();
    }

    private void iniciarGet() {
        tarea = new TareaSecundaria(this);
        tarea.execute();
    }

    private void initVistas() {
        lvAlumnos = (ListView) findViewById(R.id.lvAlumnos);
        lvAlumnos.setEmptyView(findViewById(R.id.lblEmpty)); //?
    }



    @Override
    public String proceso() throws Exception {
        String contenido = "";
        HttpURLConnection conexion=null;
        try {
            // Se obtiene el objeto URL.
            URL url = new URL(myURL);
            // Se abre la conexión.
            conexion = (HttpURLConnection) url.openConnection();
            // Se establece un tiempo máximo de lectura y de intento de conexión.
            conexion.setReadTimeout(10000); // milisegundos.
            conexion.setConnectTimeout(15000);
            // Se establece el método de conexión.
            conexion.setRequestMethod("GET");
            // Se indica que pretendemos leer del flujo de entrada.
            conexion.setDoInput(true);
            // Se realiza la conexión.
            conexion.connect();
            // Si el código de respuesta es correcto.
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Se crea un lector que lee del flujo de entrada de la conexión.
                BufferedReader lector = new BufferedReader(new
                        InputStreamReader(conexion.getInputStream()));
                // Se lee línea a línea y se agrega a la cadena contenido.
                String linea = lector.readLine();
                while (linea != null) {
                    contenido += linea;
                    linea = lector.readLine();
                }
                lector.close();
            }
            // Se retorna el contenido.
            return contenido;
        } finally {
            // Tanto si se produce una excepción como si no.
            if (conexion != null) {
                // Se cierra la conexión.
                conexion.disconnect();
            }
        }
    }

    @Override
    public void onPostExecute(String result) {
        alumnos = getDatos(result);
        //Adaptador
        lvAlumnos.setAdapter(new AlumnosAdapter(this, alumnos));

    }

    private ArrayList<Alumno> getDatos(String result) {
        Gson gson = new Gson();
        Type tipoListaAlumnos = new TypeToken<List<Alumno>>() {
        }.getType();
        // Se procesa la cadena JSON y se retorna.
        return gson.fromJson(result, tipoListaAlumnos);
    }


    //TAREA ASINCRONA
    private class TareaSecundaria extends AsyncTask<Void, Void, String>{
        Callbacks mListener;

        public TareaSecundaria(Callbacks mListener) {
            this.mListener = mListener;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = "Error Nulo";
            try {
                result =  mListener.proceso();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        // Se llama en vez de onPostExecute si ha sido cancelada.
        @Override
        protected void onCancelled() {
            // Se libera el mListener.
            mListener = null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (mListener != null) {
                mListener.onPostExecute(result);
            }
        }
    }
}