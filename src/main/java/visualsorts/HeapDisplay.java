package visualsorts;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Saahil on 3/25/2016.
 */
public class HeapDisplay extends JPanel {
    int[] a = new int[2];
    int delay = 10;
    public HeapDisplay (int len){
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
        int blockWidth = width / a.length;
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

    public void heapSort() {
        // make heap
        for(int i = 0; i < a.length; i++){
            makeHeap(i);
        }
        //De Que
        for(int i = a.length -1; i > -1; i--){
            dqHeap(a, i);
        }
        this.mysleep();
        this.repaint();
    }
    public void dqHeap(int[] a, int insert){
        System.out.println("insert = " + insert);
        int max = a[0];
        a[0] = a[insert];
        a[insert] = max;// place max in correct spot

        //fix

        int i = 0;
        while(i*2 +2 < insert && (a[i] < a[i*2+1] || a[i] < a[i*2+2])){
            this.mysleep();
            this.repaint();
            int swapKey;
            if(a[i*2 + 1] > a[i*2+2]){
                swapKey = i*2+1;
            }
            else{
                swapKey = i*2+2;
            }
            // swap
            int temp = a[swapKey];
            a[swapKey] = a[i];
            a[i] = temp;
            i = swapKey;
        }
        if(i*2+1 < insert && a[i] < a[i*2+1] ){
            // swap
            int temp = a[i];
            a[i] = a[i*2 +1];
            a[i*2+1] = temp;
        }


    }
    public void makeHeap( int insert) {
        int i = insert;
        while(i > 0 && a[i]  > a[(i-1)/2]){
            this.mysleep();
            this.repaint();
            // swap
            int temp = a[i];
            a[i] = a[(i-1)/2];
            a[(i-1)/2] = temp;
            i = (i-1)/2;
        }

    }

    public static void main(String[] args) {
        HeapDisplay j = new HeapDisplay(100);
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
        j.heapSort();



    }
}
