public class Calculadora implements Runnable{

    int numero;

    public Calculadora(int numero){
        this.numero = numero;
    }

    @Override
    public void run() {
        int i;
        System.out.println(Thread.currentThread().getName());
        for(i=0 ; i<11 ; i++){
            System.out.println(numero+" * "+i+" = "+numero*i);
        }
        System.out.println("\n");

    }


}
