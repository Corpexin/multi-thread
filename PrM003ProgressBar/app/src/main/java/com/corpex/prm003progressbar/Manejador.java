package com.corpex.prm003progressbar;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by corpex, by the Grace of God on 11/01/2016.
 */
public class Manejador extends Handler {
    private final WeakReference<MainActivity> mActivityWeakReference;

    public Manejador(MainActivity mainActivity) {
        mActivityWeakReference = new WeakReference<MainActivity>(mainActivity);
    }

    public void handleMessage(Message mensaje){
        MainActivity activity = mActivityWeakReference.get();
        if (activity != null) {
            switch (mensaje.what) {
                case MainActivity.onPreExecute:
                    // Se hacen visibles las vistas para el progreso.
                    activity.mostrarBarras();
                    break;
                // Mensaje de progreso del hilo secundario.
                case MainActivity.onProgressUpdate:
                    // Se actualizan las barras.
                    int progreso = mensaje.arg1;
                    activity.actualizarBarras(progreso);
                    break;
                // Mensaje de fin del hilo secundario.
                case MainActivity.onPostExecute:
                    // Se informa al usuario y se resetean las vistas.
                    int tareas = mensaje.arg1;
                    activity.mostrarRealizadas(tareas);
                    activity.resetearVistas();
                    break;
            }
        }
    }
}
