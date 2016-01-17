package com.corpex.pr27recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by corpex, by the Grace of God on 14/01/2016.
 */
public class AlumnosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final ArrayList<ListItem> mDatos;
    private View emptyView;
    private OnItemClickListener onItemClickListener;

    public AlumnosAdapter(ArrayList<ListItem> datos) {
        mDatos = datos;
    }


    // Retorna el tipo de ítem.
    @Override
    public int getItemViewType(int position) {
        return mDatos.get(position).getType();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ListItem.TYPE_CHILD)
            return onCreateAlumnoViewHolder(parent);
        else
            return onCreateGrupoViewHolder(parent);
    }



    private RecyclerView.ViewHolder onCreateAlumnoViewHolder(ViewGroup parent) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item_hijo, parent, false);
        final RecyclerView.ViewHolder viewHolder = new AlumnoViewHolder(itemView);
        // Cuando se hace click sobre el elemento.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    // Se informa al listener.
                    onItemClickListener.onItemClick(v,
                            (Alumno) mDatos.get(viewHolder.getAdapterPosition()),
                            viewHolder.getAdapterPosition());
                }
            }
        });
        return viewHolder;
    }

    private RecyclerView.ViewHolder onCreateGrupoViewHolder(ViewGroup parent) {
        // Se infla el layout.
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_grupo, parent, false);
        // Se crea el contenedor de vistas para la fila.

        // Se retorna el contenedor.
        return new GrupoViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDatos.get(position).getType() == ListItem.TYPE_CHILD) {
            ((AlumnoViewHolder) holder).onBind((Alumno) mDatos.get(position));
        } else {
            ((GrupoViewHolder) holder).onBind((Grupo) mDatos.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDatos.size();
    }

    public ArrayList<ListItem> getData() {
        return mDatos;
    }


    // Contenedor de vistas para la vista-fila.
    static class AlumnoViewHolder extends RecyclerView.ViewHolder {

        // El contenedor de vistas para un elemento de la lista debe contener...
        private final TextView lblNombre;

        // El constructor recibe la vista-fila.
        public AlumnoViewHolder(View itemView) {
            super(itemView);
            // Se obtienen las vistas de la vista-fila.
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
        }

        public void onBind(Alumno alumno) {
            // Se escriben los datos en la vista.
            lblNombre.setText(alumno.getNombre());
        }

    }

    // Contenedor de vistas para la vista-fila.
    static class GrupoViewHolder extends RecyclerView.ViewHolder {

        // El contenedor de vistas para un elemento de la lista debe contener...
        private final TextView lblEncCiclo;

        // El constructor recibe la vista-fila.
        public GrupoViewHolder(View itemView) {
            super(itemView);
            // Se obtienen las vistas de la vista-fila.
            lblEncCiclo = (TextView) itemView.findViewById(R.id.lblEncCiclo);
        }

        public void onBind(Grupo grupo) {
            // Se escriben los datos en la vista.
            lblEncCiclo.setText(grupo.getNombre());
        }

    }


    public void addItem(Alumno alumno){
        mDatos.add(alumno);
        notifyItemInserted(mDatos.size() - 1);
        checkIfEmpty();
    }

    private void checkIfEmpty() {
        if (emptyView != null) {
            // Muestra u oculta la empty view dependiendo de si la lista está vacía o no.
            emptyView.setVisibility(getItemCount() > 0 ? View.GONE : View.VISIBLE);
        }
    }

    // Establece la empty view para la lista.
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        // Muestra la empty view si  la lista está vacía.
        checkIfEmpty();
    }

    // Establece el listener a informar cuando se hace click sobre un elemento de la lista.
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    //INTERFAZ INTERNA listener para click en item
    public interface OnItemClickListener{
        void onItemClick(View view, Alumno alumno, int position);
    }

}
