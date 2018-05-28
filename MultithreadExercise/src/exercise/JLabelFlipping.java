package exercise;

import javax.swing.*;

public class JLabelFlipping implements Runnable{
    protected JLabel label;

    public JLabelFlipping(JLabel label){
        label.setText(String.format("Heads: %d", 0));
        this.label = label;
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
                this.label.setText(String.format("Head: %d",counter));
            }
        }
    }
}
