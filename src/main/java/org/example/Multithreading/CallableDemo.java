package org.example.Multithreading;

import java.util.concurrent.*;

//When run method wants to return value use Callable
//Use submit method on executeService
public class CallableDemo {
    public static void main(String args[]) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> value = executorService.submit(new ReturnValue());

        //System.out.println("Value ===="+value.get());

        //When u dont want to wait for longer time for getting Future value can usse this
        //depending on How much latency that thread will take
        System.out.println("Value ===="+value.get(6000, TimeUnit.MILLISECONDS));
        System.out.println("Main Thraeds gets blocked till we get the value from Future ====");

        //Attempts to cancel execution of this task.
        value.cancel(true);
        Boolean boolVal = value.isCancelled();

        Boolean done = value.isDone();
    }
}

class ReturnValue implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);
        return 12;
    }
}