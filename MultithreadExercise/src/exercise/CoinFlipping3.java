package exercise;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoinFlipping3 extends JFrame{
    public CoinFlipping3() {
        super("Flipping Coins");
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(5, 1));
        ExecutorService tasks = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; i++) {
            JLabel label = new JLabel("Label " + i);
            label.setFont(new Font("SansSerif", Font.PLAIN, 60));
            tasks.execute(new JLabelFlipping(label));
            contentPane.add(label);
        }
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CoinFlipping3();
    }
}
