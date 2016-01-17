package com.corpex.prm003progressbarasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Callbacks {
    ProgressBar prbBarra;
    ProgressBar prbCirculo;
    Button btnIniciar;
    TextView lblMensaje;

    TareaSecundaria tarea;

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
        tarea = new TareaSecundaria(this);
        tarea.execute(10);
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


    public void resetearVistas() {
        prbBarra.setVisibility(View.INVISIBLE);
        prbBarra.setProgress(0);
        prbCirculo.setVisibility(View.INVISIBLE);
        prbCirculo.setProgress(0);
        btnIniciar.setEnabled(true);
    }

    @Override
    public void onPreExecute() {
        mostrarBarras();
    }

    @Override
    public void onProgressUpdate(int progress) {
        lblMensaje.setText(getString(R.string.trabajando, progress, 10));
        prbBarra.setProgress(progress);
    }

    @Override
    public void onPostExecute(int result) {
        lblMensaje.setText("Tareas realizadas "+result);
        resetearVistas();
    }

    // Al pausar la actividad.
    @Override
    protected void onPause() {
        super.onPause();
        if (tarea != null) {
            // Se cancela la tarea.
            tarea.cancel(true);
            tarea = null;
        }
    }














    private class TareaSecundaria extends AsyncTask<Integer, Integer, Integer> {

        Callbacks mListener;

        public TareaSecundaria(Callbacks mListener) {
            this.mListener = mListener;
        }

        protected void onPreExecute() {
            mListener.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int numTrabajos = params[0];
            // Se realizan los pasos.
            for (int i = 0; i < numTrabajos; i++) {
                // Se pone a trabajar.
                trabajar();
                // Si la tarea ha sido cancelada se sale del bucle.
                if (isCancelled()) {
                    break;
                }
                // Informa del progreso.
                publishProgress(i + 1);
            }
            // Se retorna el número de trabajos realizados.
            return numTrabajos;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mListener.onProgressUpdate(values[0]);
        }

        @Override
        protected void onCancelled() {
            // Se libera el mListener.
            mListener = null;
        }

        // Es lanzado automáticamente cuando se termina de ejecutar
        // doInBackground. Recibe lo que haya retornado éste. Se ejecuta en el
        // hilo principal.
        @Override
        protected void onPostExecute(Integer result) {
            if (mListener != null) {
                mListener.onPostExecute(result);
            }
        }

        private void trabajar() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
