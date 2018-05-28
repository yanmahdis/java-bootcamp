package exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoinFlipping2 implements Runnable {
    public static void main(String[] args) {
        new CoinFlipping2();
    }

    public CoinFlipping2(){
        ExecutorService task = Executors.newFixedThreadPool(5);
        task.execute(this);
        task.execute(this);
        task.execute(this);
        task.execute(this);
        task.execute(this);
    }

    @Override
    public void run() {
        /*
         0 = tail
         1 = head
         */
        int counter = 0;
        for (int i = 0; i<=1000; i++) {
            Long randomNumber = Math.round(Math.random());
            if(randomNumber == 1) {
                counter++;
                if(counter == 3) {
                    System.out.println(Thread.currentThread().getName());
                    counter = 0;
                }
            }
        }
    }
}