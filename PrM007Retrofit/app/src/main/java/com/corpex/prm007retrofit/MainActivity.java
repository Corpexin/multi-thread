package com.corpex.prm007retrofit;

//Crear un alumno internamente y crear un boton que cuando se le de a enviar haga un post de ese alumno (no usar dropbosx entonces)
//Hacer otro boton que lo elimine
//10 0 3 2 3000 localhost desde genimotion?
//Que devuelva un String con lo de dropbox (o del localhost) y se transforme con gson en lista de alumnos
//ahora mismo esta configurado el manifest para que instituto extienda de application

//Que los datos se devuelvan en un recyclerView
//nab para agregar
//no hace falta meter todos los datos
//hacer alumno parcelable
//nueva actividad que sirva para agregar y modificar (en modificar le tienes que pasar los datos del alumno a la actividad)
//que se actualice con activityforresult (mandandole algo por parametro para indicar si ha habido cambios y si los hay reacargar)

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements AlumnosAdapter.OnItemClickListener,
        AlumnosAdapter.OnItemLongClickListener {

    private static final String STATE_LISTA = "estadoLista";
    static ArrayList<Alumno> alumnos;
    private RecyclerView lstAlumnos;
    private AlumnosAdapter mAdaptador;
    private LinearLayoutManager mLayoutManager;
    private Parcelable mEstadoLista;

    private Instituto.InstitutoInterface mApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVistas();
    }

    // Obtiene e inicializa las vistas.
    private void initVistas() {
        alumnos = new ArrayList<>();

        configToolbar();
        configRecyclerView();
        configFab();
        obtenerDatos();
    }



    // Configura la Toolbar.
    private void configToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    // Configura el FAB.
    private void configFab() {
        FloatingActionButton fabAccion = (FloatingActionButton) findViewById(R.id.fabAccion);
        /**fabAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarAlumno(getAlumno());
            }
        });**/
    }

    // Configura el RecyclerView.
    private void configRecyclerView() {
        lstAlumnos = (RecyclerView) findViewById(R.id.lstAlumnos);
        lstAlumnos.setHasFixedSize(true);
        mAdaptador = new AlumnosAdapter(alumnos);
        //mAdaptador = new AlumnosAdapter(DB.getAlumnos());
        mAdaptador.setOnItemClickListener(this);
        mAdaptador.setOnItemLongClickListener(this);
        lstAlumnos.setAdapter(mAdaptador);
        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        lstAlumnos.setLayoutManager(mLayoutManager);
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
    }


    // Obtiene los datos JSON de la lista de fotos de Instagram.
    private void obtenerDatos() {
        mApiClient = Instituto.getApiInterface();
        Call<List<Alumno>> llamada = mApiClient.listAlumnos();
        llamada.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Response<List<Alumno>> response) {
                mAdaptador.addItems(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                mAdaptador.addItem(new Alumno("La cagaste " + t.getMessage()));
            }
        });
    }









    // Agrega un alumno a la lista.
    private void agregarAlumno(Alumno alumno) {
        // Se agrega el alumno.
        mAdaptador.addItem(alumno);
        lstAlumnos.scrollToPosition(mAdaptador.getItemCount() - 1);
    }

    // Cuando se hace click sobre un elemento de la lista.
    @Override
    public void onItemClick(View view, Alumno alumno, int position) {
        Snackbar.make(lstAlumnos, "ha pulsado sobre "+alumno.getNombre(), Snackbar.LENGTH_SHORT).show();
    }

    // Cuando se hace long click sobre un elemento de la lista.
    @Override
    public void onItemLongClick(View view, Alumno alumno, int position) {
        // Se elimina el alumno.
        mAdaptador.removeItem(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Se salva el estado del RecyclerView.
        mEstadoLista = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(STATE_LISTA, mEstadoLista);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Se obtiene el estado anterior de la lista.
        mEstadoLista = savedInstanceState.getParcelable(STATE_LISTA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Se retaura el estado de la lista.
        if (mEstadoLista != null) {
            mLayoutManager.onRestoreInstanceState(mEstadoLista);
        }
    }

}
