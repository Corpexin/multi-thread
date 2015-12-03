package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    //InvokeAny. Recibir el primer resultado conseguido de entre un conjunto de tareas
    //Para llevar a cabo esta estrategia deberemos agrupar los objetos Callable correspondientes a las distintas tareas en una lista de tareas (clase List) y enviar dicha lista al ejecutor mediante el método invokeAny() (y no mediante submit(), como hicimos en el apartado anterior).
    //El método invokeAny() de la clase ThreadPoolExecutor recibe una de tareas, inicia su ejecución y retorna el resultado de la primera tarea que finalice sin lanzar una excepción. El tipo de dato del valor retornado corresponderá al tipo de dato del valor retornado por el método call() de dicha tarea.
    /**
     *
     * En este proyecto vamos a desarrollar una aplicación para la validación de un usuario en el sistema.
     * La comprobación se llevará a cabo mediante dos métodos: comprobación sobre la base de datos local y
     * comprobación sobre la red ldap. En realidad, en cuanto una de las dos validaciones tengan éxito y nos indique
     * que el usuario es un usuario registrado en el sistema, el resultado de la otra comprobación no nos será de
     * interés. Finalmente mostraremos por pantalla el método que ha validado al usuario.
     */
    public static void main(String[] args) {
        /**
         * Creo dos sistemas de validación del usuario y muestra un mensaje con el
         * nombre del sistema que primero lo ha conseguido. Si el usuario no es validado
         * por ninguno de los dos sistemas, se informa de que el usuario no es válido.
         */
        // Datos del usuario.
        String usuario = "demo";
        String password = "demo";
        // Creo dos sistemas de validación y sus tareas: contra LDAP y contra BD.
        SistemaValidacion sistemaLDAP = new SistemaValidacion("LDAP");
        SistemaValidacion sistemaBD = new SistemaValidacion("BD");
        Tarea tareaLDAP = new Tarea(sistemaLDAP, usuario, password);
        Tarea tareaBD = new Tarea(sistemaBD, usuario, password);
        // Añado las dos tareas a las lista de tareas.
        List<Tarea> listaTareas = new ArrayList<>();
        listaTareas.add(tareaLDAP);
        listaTareas.add(tareaBD);
        // Creo el ejecutor ChachedThreadPool.
        ExecutorService ejecutor = (ExecutorService) Executors
                .newCachedThreadPool();
        // Envío la lista de tareas al ejecutor y espero el resultado de la
        // primera
        // tarea en terminar sin lanzar una excepción, obteniendo su resultado.
        String quienHaValidado;
        try {
            quienHaValidado = ejecutor.invokeAny(listaTareas);
            System.out.printf("Usuario validado por %s\n", quienHaValidado);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // Si ninguno de los sistema ha finalizado bien y no ha lanzado una
            // excepción, significa que ningún sistema ha validado al usuario.
            System.out.printf("USUARIO INCORRECTO");
        }
        // Finalizo el ejecutor.
        ejecutor.shutdown();
    }

}