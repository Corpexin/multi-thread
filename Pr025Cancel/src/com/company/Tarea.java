package com.company; //Created by Corpex, by the Grace of God, on 23/11/2015.

import java.util.concurrent.Callable;

public class Tarea implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "Cancelada";
    }


}
