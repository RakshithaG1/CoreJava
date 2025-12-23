package org.example.Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Used to mannage creation and deletion of multiple threads
public class SingleThreadExector {

    public static void main(String args[]){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            executorService.execute(new Task(i));
        }
    }
}
class Task implements Runnable{

    int taskId;
    Task(int taskId) {
        this.taskId = taskId;
    }
    @Override
    public void run() {
        System.out.println("Task Id"+taskId+"Thread Name="+Thread.currentThread().getName());
    }
}