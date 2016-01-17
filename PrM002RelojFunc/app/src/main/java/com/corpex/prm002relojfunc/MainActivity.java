package com.corpex.prm002relojfunc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView tvHora;
    Button btnParar;
    Thread hiloSecundario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnParar.getText().toString().matches("Iniciar")){
                    btnParar.setText("Parar");
                    iniciar();
                }else{
                    btnParar.setText("Iniciar");
                    parar();
                }

            }
        });
    }

    private void iniciar() {
        hiloSecundario = new Thread(new Reloj(), "Secundario");
        hiloSecundario.start();
    }

    private void parar() {
        hiloSecundario.interrupt();
    }

    private void initViews() {
        tvHora = (TextView) findViewById(R.id.tvHora);
        btnParar = (Button) findViewById(R.id.btnParar);
    }

    private class Reloj  implements Runnable {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");

        @Override
        public void run() {
            boolean enter = true;
            while(enter){
                tvHora.post(new Runnable() {
                    @Override
                    public void run() {
                        tvHora.setText(formato.format(new Date()));
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    enter = false;
                }
            }
        }
    }
}
