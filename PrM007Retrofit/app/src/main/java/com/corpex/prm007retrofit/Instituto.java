package com.corpex.prm007retrofit;


import android.app.Application;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Cristina on 28/01/2016.
 */
public class Instituto extends Application{
    private static final String BASE_URL = "http://192.168.1.37:3000/"; //cambiar por la IP del portatil
    private static InstitutoInterface servicio;

    public interface InstitutoInterface {
        @GET("alumnos")
        Call<List<Alumno>> listAlumnos();
        @DELETE("alumnos/{id}")
        Call<Alumno> eliminarAlumno(@Path("id") int idAlumno);
        @POST("alumnos")
        Call<Alumno> agregarAlumno(@Body Alumno alumno);
        @PUT("alumnos/{id}")
        Call<Alumno> updateAlumno(@Path("id") int idAlumno, @Body Alumno nuevo);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient cliente = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(cliente).build();
        servicio = retrofit.create(InstitutoInterface.class);
    }

    public static InstitutoInterface getApiInterface(){
        return servicio;
    }
}
