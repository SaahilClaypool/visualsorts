package visualsorts;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Saahil on 3/26/2016.
 */
public class QuickDisplay extends JPanel{

    int[] a = new int[2];
    int delay =2;
    public QuickDisplay(int len){
        a = new int[len];
        for(int i = len; i > 0; i--){
            //ar[100-i] = (int)Math.random() * len;
            a[len-i] = (int)(Math.random() * len);
        }
        this.repaint();
    }
    public void paint(Graphics g) {
        System.out.println("painting");
        int width = this.getWidth();
        int height = this.getHeight();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        int blockWidth = width / a.length ;
        int blockHeight = height / a.length;
        g.setColor(Color.white);
        for (int i = 0; i < a.length; i++) {
            int thisBlockHeight = blockHeight * a[i];
            g.fillRect(i * blockWidth, (height - thisBlockHeight), blockWidth-2, thisBlockHeight);
        }


    }

    public  void mysleep()  {

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }
    public void quick(){
        quick(0,a.length);
    }
    public void quick( int start, int end){
        System.out.println("sorting");
        this.repaint();
        // split
        if(end-start <= 1)
            return;

        int piv = a[start];
        int st=start+1,e=end-1;
        while(st <= e){
            this.mysleep();
            this.repaint();
            if(a[st] >piv){
                // swap start and end
                int temp = a[st];
                a[st] = a[e];
                a[e] = temp;
                e--;

            }
            else{
                st++;
            }
        }
        // end of loop when st = e; place piv at e
        a[start] = a[st-1];
        a[st-1] = piv;
        // quick left
        quick( start, st-1);
        quick( st, end);
        // quick right

    }

    public static void main(String[] args) {
        QuickDisplay j = new QuickDisplay(100);
        j.setSize(500,500);
        j.setMinimumSize(new Dimension(500,500));
        JFrame frame = new JFrame();
        frame.add(j);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(2000,550);
        frame.setVisible(true);
        j.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        j.quick();



    }
}
