package com.company;

public class Main {

    public static void main(String[] args) {
        Acomodador acomodador = new Acomodador();

        //Creo 10 clientes
        for(int i =0 ; i<10 ; i++){
            Cliente cliente = new Cliente("Cliente"+i);
            acomodador.acomodarCliente(cliente); //los voy acomodando

            try {//duermo un segundo
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Una vez hecho todo cierro el teatro
        acomodador.cerrarTeatro();
    }
}
