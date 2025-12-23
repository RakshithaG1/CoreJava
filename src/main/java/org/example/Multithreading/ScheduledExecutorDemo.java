package org.example.Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Used to mannage creation and deletion of multiple threads
public class ScheduledExecutorDemo {

    //Delay Queue is used to create tasks.
    public static void main(String args[]) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new ProbeTask(),1000, 2000, TimeUnit.MILLISECONDS);

        try {
            if(!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                executorService.shutdown();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class ProbeTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Probing task============");
    }
}

//Ideal pool Size?
//It depends
//Is your task dependent on CPU intensive task
//Is your task dependent on IO intensive task
//Uses a combinational approach

