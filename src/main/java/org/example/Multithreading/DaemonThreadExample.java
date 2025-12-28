package org.example.Multithreading;


class DaemonHelper implements  Runnable{

    @Override
    public void run() {
        int count =0;
        while (count<500) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            System.out.println("Daemon Helper thraed running="+count);
        }
    }
}

class UserHelper implements  Runnable{

    @Override
    public void run() {
        int count =0;
        while (count<500) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            System.out.println("User Helper thraed running="+count);
        }
    }
}

//Daemon Threads-->background threads having low priority which will be run when all
//user Thraeds will be shutdown
//When a java program is run main Thread starts executing imeedietly and under normal circumstance
//main Thraed is parent thread shutdown at the end
//Daemon Thraeds is started when all userThraeds are done jvm then shutsdown daemon thraeds after its done executing
//eg:GC thread
//the daemon thraeds are shutdown by JVM
public class DaemonThreadExample{
    public static void main(String args[]) {
        Thread thread1= new Thread(new DaemonHelper());
        Thread thread2= new Thread(new UserHelper());

        thread1.start();
        thread1.setDaemon(true);
        thread2.start();
    }
}
