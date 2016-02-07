package com.corpex.prm008notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int NC_TAREA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearNotificacion();
            }
        });
    }

    private void crearNotificacion() {
        // Se obtiene el gestor de notificaciones del sistema.
        NotificationManager mGestor = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Se crea el constructor de notificaciones.
        NotificationCompat.Builder b = new NotificationCompat.Builder(this);
// Se configuran los elementos básicos de la notificación.
        b.setSmallIcon(R.drawable.ic_launcher); // Icono pequeño.
        b.setLargeIcon(((BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher)).getBitmap()); // Icono grande.
        b.setContentTitle("Nueva tarea pendiente"); // Título (1ª línea).
        b.setContentText("Pasar ITV al coche"); // Texto (2º línea).
        b.setContentInfo("3"); // Info adicional (nº total tareas pendientes).
        b.setTicker("Nueva tarea pendiente");  // Ticker.

        //CAMBIAR POR INTENT POR DEFECTO DE NAVEGADOR
        Intent intent = new Intent(this, TareaActivity.class);
       // intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
        TaskStackBuilder pila = TaskStackBuilder.create(this);
        pila.addParentStack(TareaActivity.class);
// Se añade el intent a la cima de la pila.
        pila.addNextIntent(intent);
// Se obtiene un PendingIntent que contiene la pila al completo.
        PendingIntent pendingIntent =pila.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
// Se establece el pending intent para cuando el usuario pulse sobre
// la notificación.
        b.setContentIntent(pendingIntent);
// Se construye y muestra la notificación, asignándole un código de notificación entero.
        mGestor.notify(NC_TAREA, b.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
