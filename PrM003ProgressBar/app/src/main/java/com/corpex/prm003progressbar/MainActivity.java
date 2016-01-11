package com.corpex.prm003progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar prbBarra;
    ProgressBar prbCirculo;
    Button btnIniciar;
    TextView lblMensaje;
    Manejador manejador;

    // Constantes (se definen con esos nombres para introducir las AsyncTasks).
     static final int onPreExecute = 0;
     static final int onProgressUpdate = 1;
     static final int onPostExecute = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
    }

    private void iniciar() {
        btnIniciar.setEnabled(false);

    }

    private void initViews() {
        prbBarra = (ProgressBar) findViewById(R.id.prbBarra);
        prbCirculo = (ProgressBar) findViewById(R.id.prbCirculo);
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        lblMensaje = (TextView) findViewById(R.id.lblMensaje);
    }

    public void mostrarBarras() {
        prbBarra.setVisibility(View.VISIBLE);
        lblMensaje.setText(R.string.trabajando);
        lblMensaje.setVisibility(View.VISIBLE);
        prbCirculo.setVisibility(View.VISIBLE);
    }

    public void actualizarBarras(int progreso) {
        lblMensaje.setText(getString(R.string.trabajando, progreso, 10));
        prbBarra.setProgress(progreso);
    }

    public void mostrarRealizadas(int tareas) {
        lblMensaje.setText("Tareas: "+tareas);
    }

    public void resetearVistas() {
        prbBarra.setVisibility(View.INVISIBLE);
        prbBarra.setProgress(0);
        prbCirculo.setVisibility(View.INVISIBLE);
        prbCirculo.setProgress(0);
        btnIniciar.setEnabled(true);
    }
}
