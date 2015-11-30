package com.company; //Created by Corpex, by the Grace of God, on 23/11/2015.

import java.util.concurrent.Callable;

public class Tarea implements Callable<Integer> {
    int result;


    @Override
    public Integer call() throws Exception {
        int r=0;
        for(int i=0 ; i<20 ; i++){
            r = r+i;
            System.out.print(i+" + ");
        }

        return r;
    }

    public int getResult() {
        return result;
    }
}
