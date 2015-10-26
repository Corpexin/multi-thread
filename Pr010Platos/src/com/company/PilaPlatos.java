package com.company; //Created by Corpex, by the Grace of God, on 19/10/2015.

import java.util.ArrayList;

public class PilaPlatos {

    ArrayList<Integer> platos = new ArrayList<>();//arraylist<integer>

    public PilaPlatos(ArrayList<Integer> platos){
        this.platos = platos;
    }

    //Aqui van los metodos de sacar y meter platos
    public void meterPlatos(int numPlatos){

        synchronized (this){ //??
            for(int i =0 ; i<numPlatos ; i++){
                platos.add(i);
                System.out.println("Plato Añadido en pila id:"+i);
            }
            notifyAll();
            //falta notifyall (cerrojo.notifyAll()), si usa this, simplemente (notifyall())
            //solo poner notify cuando haya otros esperando para realizar una accion que dependa de el

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
                System.out.println("Plato Sacado de Pila id:"+platos.get(i));
            }
            platos.removeAll(auxRem);
        }
    }

}
