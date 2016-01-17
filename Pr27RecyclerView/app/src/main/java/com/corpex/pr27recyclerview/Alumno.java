package com.corpex.pr27recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by corpex, by the Grace of God on 14/01/2016.
 */
public class Alumno extends ListItem implements Parcelable {
    private String nombre;

    public Alumno(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


    //Parcelable
    @Override
    public int getType() {
        return ListItem.TYPE_CHILD;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
    }

    protected Alumno(Parcel in) {
        this.nombre = in.readString();
    }

    public static final Creator<Alumno> CREATOR = new Creator<Alumno>() {
        @Override
        public Alumno createFromParcel(Parcel in) {
            return new Alumno(in);
        }

        @Override
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };
}
