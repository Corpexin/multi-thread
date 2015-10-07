public class Main {

    public static void main(String[] args) {
        int cont =1;
        do{
            Calculadora calc = new Calculadora(cont);
            Thread hilo = new Thread(calc, "Tabla del"+cont);
            hilo.setPriority(cont);
            cont++;
            hilo.start(); //es el que realmente crea el hilo, no run.
        }while(cont<11);

    }

}