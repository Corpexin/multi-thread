package com.corpex.prm010downloadmanager;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String URLSTRING ="http://219.138.27.38/m2.music.126.net/2l7tXm5JyC8D9nXAV5ZtVQ==/2808152697367128.mp3" ;
    DownloadManager Gestor;
    private BroadcastReceiver mReceptor;

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
                descargar();
            }
        });

        mReceptor = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                comprobarDescarga(intent);
            }
        };
    }


    private void descargar() {
        Gestor = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request solicitud = new DownloadManager.Request(Uri.parse(URLSTRING));
        solicitud.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        solicitud.setAllowedOverRoaming(false);
        solicitud.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC, "Ejemplo.mp3");

        solicitud.setTitle("Ejemplo");
        solicitud.setDescription("Descripcion de la musica");
        solicitud.allowScanningByMediaScanner();
        solicitud.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        // Se encola la solicitud.
        Gestor.enqueue(solicitud);
        // Se informa al usuario.
        Toast.makeText(this, "Descargando", Toast.LENGTH_LONG).show();
    }


    private void comprobarDescarga(Intent intent) {
        long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);

        DownloadManager.Query consulta = new DownloadManager.Query();
        consulta.setFilterById(downloadId);
        Cursor c = Gestor.query(consulta);

        if (c != null) {
            if (c.moveToFirst()) {
                int estado = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                switch (estado) {
                    case DownloadManager.STATUS_SUCCESSFUL:
                        Toast.makeText(this, "Cancion descargada", Toast.LENGTH_LONG).show();
                        break;
                    case DownloadManager.STATUS_FAILED:
                        Toast.makeText(this, "La cagaste", Toast.LENGTH_LONG).show();
                        break;
                }
            }
            c.close();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Se crea el filtro con las acción de descarga finalizada.
        IntentFilter filtro = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        // Se registra el receptor.
        registerReceiver(mReceptor, filtro);

    }


    @Override
    protected void onPause() {
        super.onPause();
        // Se quita el registro del recpetor.
        unregisterReceiver(mReceptor);

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
