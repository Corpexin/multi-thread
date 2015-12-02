package com.company; //Created by corpex, by the Grace of God, on 01/12/2015.

import java.util.Random;

public class Participante implements Runnable {
    VideoConferencia videoConferencia;

    public Participante(VideoConferencia videoConferencia) {
        this.videoConferencia = videoConferencia;
    }

    @Override
    public void run() {
        try {
            videoConferencia.conectar(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
