package com.corpex.prm009broadcastreceiver;

import android.app.IntentService;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

/**
 * Created by corpex, by the Grace of God on 06/02/2016.
 */
public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("Hilo nuevo");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Se llama al onStartCommand del padre.
        super.onStartCommand(intent, flags, startId);
        // El servicio NO será reiniciado automáticamente.
        return START_NOT_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
            // Se obtiene el extra correspondiente al mensaje.
            try {
                TimeUnit.SECONDS.sleep(3);
                Intent aa=new Intent(getString(R.string.action));
                //sendBroadcast(intent);
                sendOrderedBroadcast(aa, null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
