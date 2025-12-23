package org.example.Multithreading;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String args[]) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Thread thread1 = new Thread(new FirstThread(exchanger));
        Thread thread2 = new Thread(new SecondThread(exchanger));
        thread1.start();
        thread2.start();

    }
}

class FirstThread implements Runnable {

    private final Exchanger<Integer> exchanger;

    public FirstThread(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int dataFirstThread = 10;
        System.out.println("First Thread is sending data to send=" + dataFirstThread);

        try {
            Integer receiveData = exchanger.exchange(dataFirstThread);
            System.out.println("FirstThread received data from the exchnage===" + receiveData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

    class SecondThread implements Runnable {

        private final Exchanger<Integer> exchanger;

        public SecondThread(Exchanger exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            int dataSecondThread = 20;

            try {
                Thread.sleep(2000);
                System.out.println("Second Thread is sending data to send=" + dataSecondThread);
                Integer receiveData = exchanger.exchange(dataSecondThread);
                System.out.println("SecondThread received data from the exchnage===" + receiveData);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
