package com.corpex.prm006volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    static ArrayList<Alumno> alumnos = new ArrayList<>();
    ListView lvAlumnos;
    final String myURL = "https://dl.dropboxusercontent.com/u/67422/Android/json/datos.json";
    private RequestQueue colaPeticiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVistas();
        realizarPeticionGson();
    }


    private void initVistas() {
        lvAlumnos = (ListView) findViewById(R.id.lvAlumnos);
        lvAlumnos.setEmptyView(findViewById(R.id.lblEmpty)); //?
        colaPeticiones = App.getRequestQueue();
    }



    private ArrayList<Alumno> procesarGSON(String result) {
        Gson gson = new Gson();
        Type tipoListaAlumnos = new TypeToken<List<Alumno>>() {
        }.getType();
        // Se procesa la cadena JSON y se retorna.
        return gson.fromJson(result, tipoListaAlumnos);
    }



    private void realizarPeticionGson() {

        Response.Listener<ArrayList<Alumno>> listener = new Response.Listener<ArrayList<Alumno>>() {
            @Override
            public void onResponse(ArrayList<Alumno> response) {
                // Se cargan los alumnos en la lista.
                alumnos = response;
                lvAlumnos.setAdapter(new AlumnosAdapter(getApplicationContext(), alumnos));
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        };
        Gson gson = new Gson();
        Type tipo = new TypeToken<List<Alumno>>() {
        }.getType();

        GsonArrayRequest<ArrayList<Alumno>> peticion = new GsonArrayRequest<>(
                Request.Method.GET, myURL, tipo, listener, errorListener,
                gson);
        // Se añade la petición a la cola de Volley.
        colaPeticiones.add(peticion);
    }




}
