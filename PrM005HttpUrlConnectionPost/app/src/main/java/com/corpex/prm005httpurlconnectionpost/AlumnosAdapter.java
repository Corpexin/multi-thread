package com.corpex.prm005httpurlconnectionpost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corpex, by the Grace of God on 06/11/2015.
 */
public class AlumnosAdapter extends ArrayAdapter<Alumno> {
    private final List<Alumno> mAlumnos;
    private final LayoutInflater mInflador;

    public AlumnosAdapter(Context contexto, ArrayList<Alumno> alumnos) {
        super(contexto, R.layout.activity_main_item, alumnos);
        mAlumnos = alumnos;
        mInflador = LayoutInflater.from(contexto);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // Si no se puede reciclar.
        if (convertView == null) {
            // Se obtiene la vista-fila inflando el layout.
            convertView = mInflador.inflate(R.layout.activity_main_item, parent, false);
            // Se crea el contenedor de vistas para la vista-fila.
            holder = new ViewHolder(convertView);
            // Se almacena el contenedor en la vista.
            convertView.setTag(holder);
        }
        // Si se puede reciclar.
        else {
            // Se obtiene el contenedor de vistas desde la vista reciclada.
            holder = (ViewHolder) convertView.getTag();
        }
        // Se escriben los datos en las vistas del contenedor de vistas.
        onBindViewHolder(holder, position);
        // Se retorna la vista que representa el elemento.
        return convertView;
    }

    // Cuando se deben escribir los datos en la vista del elemento.
    private void onBindViewHolder(ViewHolder holder, int position) {
        Alumno alumno = mAlumnos.get(position);
        holder.lblNombre.setText(alumno.getNombre());
        holder.lblEdad.setText(String.valueOf(alumno.getEdad()));
        holder.lblCurso.setText(alumno.getCurso());
        Picasso.with(getContext()).load(MainActivity.alumnos.get(position).getFoto()).placeholder(R.drawable.foto).resize(150, 150).centerCrop().into(holder.imgFoto);

    }

    // Contenedor de vistas para la vista-fila.
    private static class ViewHolder {

        // El contenedor de vistas para un elemento de la lista debe contener...
        private final TextView lblNombre;
        private final TextView lblEdad;
        private final TextView lblCurso;
        private final ImageView imgFoto;

        // El constructor recibe la vista-fila.
        public ViewHolder(View itemView) {
            // Se obtienen las vistas de la vista-fila.
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblEdad = (TextView) itemView.findViewById(R.id.lblEdad);
            lblCurso = (TextView) itemView.findViewById(R.id.lblCurso);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
        }

    }





}