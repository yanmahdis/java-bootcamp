import exercise.CoinFlipping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    public static void main(String[] args) {
        ExecutorService tasks = Executors.newFixedThreadPool(5);

        // coin flipping
        tasks.execute(new CoinFlipping());
        tasks.execute(new CoinFlipping());
        tasks.execute(new CoinFlipping());
        tasks.execute(new CoinFlipping());
        tasks.execute(new CoinFlipping());
    }
}
