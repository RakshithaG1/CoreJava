package org.example.Multithreading;

//Intr to countDown Latch
//When to use countdown latch
//code demonstartion
//it is functionality similar to join
//purpose usage
//can we reset the count?-->not-->use cyclic barrier
import java.util.concurrent.CountDownLatch;
public class Resturant {
    public static void main(String args[]) throws InterruptedException {
        int numberOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numberOfChefs);
        new Thread(new Chef("ChefsA", "Cutting", latch)).start();
        new Thread(new Chef("ChefsB", "Frying", latch)).start();
        new Thread(new Chef("ChefsC", "Cooking", latch)).start();

        latch.await();
        System.out.println("All dishes are ready");
    }
}

class Chef implements Runnable{

    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(name+"is preparing"+dish);
            Thread.sleep(2000);
            System.out.println(name+"has finished preparing"+dish);
            latch.countDown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}