package com.company; //Created by Corpex, by the Grace of God, on 23/11/2015.

import java.util.concurrent.FutureTask;

public class TareaResultado extends FutureTask<Integer>{
   int result;

    public TareaResultado(Tarea tarea) {
        super(tarea);
        this.result = tarea.getResult();
    }

    protected void done(){
        result = result*2;
    }
}
