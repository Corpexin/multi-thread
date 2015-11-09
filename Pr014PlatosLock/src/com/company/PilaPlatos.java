package com.company; //Created by Corpex, by the Grace of God, on 19/10/2015.

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PilaPlatos {

    ArrayList<Integer> platos = new ArrayList<>();//arraylist<integer>
    ReentrantLock cerrojo = new ReentrantLock();
    Condition hayEspacio = cerrojo.newCondition();
    Condition hayPlato = cerrojo.newCondition();

    public PilaPlatos(ArrayList<Integer> platos){
        this.platos = platos;
    }

    //Aqui van los metodos de sacar y meter platos
    public void meterPlatos(int numPlatos){
        cerrojo.lock();
        while(platos.size()>10){//condicion inventada
            try {
                hayEspacio.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (this){ //??
            for(int i =0 ; i<numPlatos ; i++){
                platos.add(i);
                System.out.println(Thread.currentThread().getName()+" - Plato A�adido en pila id:"+i);
            }
            notifyAll();

        }
    }

    public void sacarPlatos(int numPlatos){
        synchronized (this){
            while(platos.isEmpty()){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ArrayList<Integer> auxRem = new ArrayList<>();
            for(int i = 0 ; i<numPlatos ; i++){
                auxRem.add(platos.get(i));
                System.out.println(Thread.currentThread().getName()+" - Plato Sacado de Pila id:"+platos.get(i));
            }
            platos.removeAll(auxRem);
        }
    }

}
