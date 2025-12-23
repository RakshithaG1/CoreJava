package org.example.Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Used to mannage creation and deletion of multiple threads
public class CachedThreadPoolExecutor {

    //Synchronous Queue is used to create tasks.
    //Queue can contain one task at max, if all threads are busy
    //new thread a=is craeted and assigned that task and if idle for > than 60s then its killeds
    public static void main(String args[]){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            executorService.execute(new Task(i));
        }
    }
}
class Task2 implements Runnable{

    int taskId;
    Task2(int taskId) {
        this.taskId = taskId;
    }
    @Override
    public void run() {
        System.out.println("Task Id"+taskId+"Thread Name="+Thread.currentThread().getName());
    }
}