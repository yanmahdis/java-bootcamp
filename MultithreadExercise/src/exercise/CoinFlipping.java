package exercise;

public class CoinFlipping implements Runnable{
    
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
