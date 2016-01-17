package com.corpex.pr27recyclerview;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by corpex, by the Grace of God on 14/01/2016.
 */
public class DB {

    private static final ArrayList<Alumno> datos;
    private static int next =1;
    private static final Random aleatorio = new Random();

    public static Alumno getNextAlumno() {
        int num = next++;
        return new Alumno("Alumno " + num);
    }

    static{
        datos = new ArrayList<>();
        for(int i=0; i<5; i++){
            datos.add(getNextAlumno());
        }
    }

    public static ArrayList<Alumno> getAlumnos() {
        return datos;
    }

    public static void addAlumno(Alumno alumno){
        datos.add(alumno);
    }


    public static int getAlumnosCount() {
        return datos.size();
    }

    private static int getNext() {
        return next++;
    }
}
