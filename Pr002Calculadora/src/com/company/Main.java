package com.company;

public class Main {
    public static void main(String[] args) {
        int cont =1;
        do{
            Calculadora calc = new Calculadora(cont);
            calc.setName("Tabla del "+cont);
            calc.setPriority(cont);
            calc.start();//como en este extiende de thread, calculadora ya es un hilo y por tanto puede ejecutar sus metodos
            cont++;
        }while(cont<11);

    }
}