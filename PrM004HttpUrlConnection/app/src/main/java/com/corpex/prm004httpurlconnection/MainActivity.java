package com.corpex.prm004httpurlconnection;

import android.app.Application;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements Callbacks {
    Button btnPeticion;
    TextView tvTexto;
    final String myURL = "https://dl.dropboxusercontent.com/u/67422/Android/json/datos.json";
    TareaSecundaria tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPeticion = (Button) findViewById(R.id.btnPeticion);
        tvTexto = (TextView) findViewById(R.id.tvTexto);

        btnPeticion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();

            }
        });
    }

    private void iniciar() {
        btnPeticion.setEnabled(false);
        tarea = new TareaSecundaria(this);
        tarea.execute();
    }

    /**
    @Override
    public String proceso() {
        String contenido = "";
        HttpURLConnection conexion = null;
        try{
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
                BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                // Se lee línea a línea y se agrega a la cadena contenido.
                String linea = lector.readLine();
                while (linea != null) {
                    contenido += linea;
                    linea = lector.readLine();
                }
                lector.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Se retorna el contenido.
        return contenido;
    }
**/
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
        tvTexto.setText(result);
    }













    public class TareaSecundaria extends AsyncTask<Void, Void, String> {
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
