package com.company;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        // Creo el gestor de tareas rechazadas.
        GestorTareasRechazadas gestor = new GestorTareasRechazadas();
        // Creo el ejecutor.
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors
                .newCachedThreadPool();
        // Asigno al ejecutor el gestor de tareas rechazadas.
        ejecutor.setRejectedExecutionHandler(gestor);
        // Lanzo tres tareas y se las envío al ejecutor.
        System.out.printf("Enviando tres tareas al ejecutor...\n");
        for (int i = 0; i < 3; i++) {
            Tarea tarea = new Tarea("Tarea " + i);
            ejecutor.submit(tarea);
        }
        // Apago el ejecutor
        System.out.printf("Apagando el ejecutor...\n");
        ejecutor.shutdown();
        // Envío otra tarea (que será rechazada).
        System.out.printf("Enviando una cuarta tarea al ejecutor...\n");
        ejecutor.submit(new Tarea("Tarea 4"));
    }

    /**
     * -----PROCESO------
     -Leer ejercicio 3 veces minimo
     -Comprender ejercicio x2
     -Recomprender x2
     -Mirar ejercicio parecido (no solo hacer copy/paste, mira las diferencias y comprende cada uno)
     -Escribir en el main los pasos
     -DO IT FAGGOT!

     *Apuntar detalles
     **Preguntar dudas maldita sea!

     -----MISC-----
     Para Thread/lock/synchronized (no siempre es asi, pero en su mayoria si)
     -Identifica el pasivo (clase sobre la que se realiza la accion)
     *Si la pasiva hace alguna accion, puede ser que tambien sea un hilo CUIDADO
     -Identifica los hilos (son los que realizan las tareas sobre el pasivo)
     -Crea el pasivo y los hilos
     -Inicia los hilos
     -Programa la ejecucion de los hilos
     -Programa la ejecucion del pasivo
     -Termina la ejecucion del main

     -Hasta donde se el pasivo siempre es el que lleva el synchronized/lock/demas mierdas (no siempre <ver countdownlatch si no crees>)

     --llamada sincrona con invokeAll / invokeAny, se usa si el main no tiene que hacer nada mas despues (el main se espera)

     --llamada asincrona si despues de hacer el main tiene que hacer algo mas, por ejemplo si el main ejecuta unas tareas y despues tiene que realizar otras. Se usa submit + for(Future.isdone) para comprobar si ha terminado las tareas o awaitTermination() que se usa despues del shutdown para esperar la finalizacion de las tareas (el main pasa del tema)

     awaitTermination()  - Si despues de la finalizacion del ejecutor (shutdown) queremos hacer algo con el main, pero ese algo necesita que las tareas esten terminadas (algo que no se garantiza con el shutdown), se usa el awaitTermination que se espera a que finalicen para continuar. Lo normal es usar InvokeAll() para esperar a que terminen las tareas porque para usar awaitTermination antes hay que apagar el ejecutor.

     ESPERAR FINALIZACION DE TAREAS
     --Diferencia submit+future+isdone / invokeall - Con el submit puedo seguir haciendo acciones en el main despues de ejecutar las tareas. (Conforme se vayan completando se iran guardando en el future. Cuando termine de hacer mis acciones en el main, compruebo con isdone si ya han terminado)
     En el invokeAll por contrario el main se espera a que todas las tareas se ejecuten y rellenen al completo el future, sin avanzar con otras acciones hasta que no haya terminado.
     -----GENERAL------
     -Interrupt

     -Sleep

     -Join

     -ThreadGroup


     -------SYNCHRONIZED-------
     -Synchronized metodo

     -Synchronized bloque

     -Synchronized con mutex

     -wait / notifyAll(): Se usa para synchronized

     -------LOCK--------
     -Read/Write Lock, se pueden leer en varios hilos, pero escribir solo en 1

     -Conditions / await / signalAll :Se usa para lock. Suspende un hilo que ya ha obtenido el lock, dependiendo de una condicion (while(lista>=0)) con await(). Cuando termina llama al resto de hilos con signalAll().
     El truco es identificar las condiciones que suspensa un hilo "el almacen esta lleno y se intenta meter un elemento mas".Todo entre cerrojo.lock y cerrojo.unlock. Se usa cuando hay que proteger un recurso y hay alguna condicion que impide avanzar PrRep006

     ---SINCRONIZACION COMPLEJA---
     Se ponen en la pasiva
     -Semaphore: deja pasar hilos hasta que su contador llega a 0, a partir del cual habra que esperar a que algun hilo entrado salga. Se adquiere el semaforo con acquire(). Se libera con release().
     Se usa cuando hay mas de 1 recurso a proteger. PrRep007

     -CountDownLatch: suspende con await() a tantos hilos como diga su constructor hasta que terminen una serie de tareas marcadas por countDown(). Sucesion de eventos independientes del numero de hilos en el que se producen. No se protegen recursos. PrRep008

     -CyclicBarrier: sincronizacion intermedia o inicial de los hilos. Ejecucion de tareas paralelas. Los suspende hasta que todos llegan al mismo punto para reactivarlos a la vez. Suspende los hilos con await() hasta que el contador interno llega a 0. PrRep009.

     -Phaser: varios CyclicBarriers, varias etapas que suspende los hilos cuando llegan hasta que todos lleguen a ese punto. Suspender hilo al final de fase arriveAndAwaitAdvance(). Al final de la fase arriveAndDeregister() PrRep010

     ---EJECUTORES-----
     ThreadPooolExecutor + execute/submit(Runnable/Callable) + shutDown + PrRep011
     TODO AQUI PUEDE RECIBIR UN RUNNABLE. CUIDAIKO

     Ejecutor+Runnable no devuelve nada
     Ejecutor+Callable devuelve resultado que se guarda en un Future

     -CachedThreadPool: newCachedThreadPool() - Reutiliza hilos para realizar tareas, y si no puede, crea uno nuevo. PrRep011

     -FixedThreadPool:
     newFixedThreadPool(n)n - Reutiliza el mismo numero de hilos pasado por parametro dicho numero. Se usa cuando el numero de tareas es alto para no sobrecargar. PrRep011.

     -Callable/Future
     Ejecutor.submit()
     Callable, son como Runnable pero pueden retornar resultado. Implementan call().
     Future, obtiene el resultado de los callable con get(). No espera a la finalizacion de tareas como si lo hace invokeAll(). Tambien se podria usar Image PrRep012
     * Future con metodo isDone()comprueba si una tarea ha terminado. Sin embargo, para esperar la finalizacion de una tarea se suele usar invokeAll()

     **A partir de aqui se usa executorService en vez de ThreadPoolExecutor**

     -InvokeAny - Espera al Retornado del primer resultado de entre todas las tareas lanzadas PrRep013

     -InvokeAll - Espera el Retornado de todos los resultados de las tareas lanzadas. Es mas eficiente que usar Future.isdone().
     Ej: Future = ejecutor.invokeAll(Callable)
     PrRep014 x

     -Schedule - Tareas planificadas, para ser lanzadas con un delay (lanzar una tarea distinta cada 10 segundos, o a un a hora concreta). NO se repiten la ejecucion
     Se usa el ejecutor Planificado ScheduledThreadPoolExecutor = newScheduledThreadPool(numerodehilosdelEjeutor)
     Para enviar tareas al ejecutor se usa .schedule(tarea, tiempodeEsperaParaEjecutar)
     *Si queremos que la tarea se ejecute en un momento concreto, deberemos calcular la diferencia entre dicho momento y la fecha/hora actual y usarla como periodo de espera.
     **setExecuteExistingDelayedTasksAfterShutdownPolicy() del ejecutor. Si le pasamos como parámetro el valor false las tareas pendientes no serán ejecutadas al finalizar el ejecutor. PrRep015 x


     -ScheduleAtFixedRate() - Tarea periodica, es decir se ejecuta cada x tiempo. (Tambien puede ejecutarse con un retraso inicial (que es la propiedad del schedule))
     ScheduledExecutionService ejecutor = Executors.newScheduledThreadPool(numeroDeHilos)
     Metodo - scheduleAtFixedRate(tarea, tiempoEsperaParaPrimeraEjecucion, tiempoEntreEjecuciones, UnidadDeTiempo)
     FutureTask para obtener informacion de la repeticion(con getDelay())
     ***Si en vez de Callable, es un Runnable, el ScheduleFuture, debera ir parametrizado con ?
     PrRep016 x

     -Cancel - Cancelar tareas una vez enviadas al ejecutor. Future.cancel()
     (Comportamiento del cancel en ejercicio)
     PrRep017 x

     -Done - Ejecutar codigo una vez finalizada cada tarea en el ejecutor (no todas), para por ejemplo generar informar, enviar datos por email, etc. NO se podra cambiar el valor retornado por la tarea.
     El done es llamado internamente por la clase FutureTask cuando termina la tarea que este ejecutando y se automarque como isDone.
     Para usarlo, hacer extends de FutureTask y sobreescribir el done().
     Para acceder desde el done al resultado del callable, usamos get()
     --Observaciones: En el ejercicio se ha creado una clase extra que es la que extiende de futuretask y tiene el done. ESTA CLASE es la que se envia al ejecutor, no la tarea en si. La clase de la tarea, va DENTRO del futureTask (de hecho se le pasa al super()). Si se pudiera meter el done directamente en la tarea, lo desconozco.
     PrRep018


     -CompletionService - Independizar el envio de tareas a un hilo y el procesamiento del resultado a otro.
     Actua como puente.
     --submit() se envian las tareas a un ejecutor
     --poll() obtener  el future de la siguiente tarea que finalice su ejecucion.
     Solo operaciones de control (ver ejercicio)
     --Image
     PrRep019 NI PUTA IDEA DE ESTO AVISAO ESTAS


     -RejectedExecutionHandler - permite hacer acciones (que se encuentren en el metodo rejectedExecution) si una tarea ha sido rechazada (vease, ejecutor apagado, pero se le asigna tarea).
     Para ello se crea una clase aparte, que implementa RejectedExecutionHandler con el metodo rejectedExecution.
     En el main se tendra que crear el objeto de la clase RejectedEx.. y asignarselo al ejecutor con ejecutor.setRejectedExecutionHandler(objetoRejec..)

     */

}
