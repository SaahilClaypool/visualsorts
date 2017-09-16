package visualsorts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Saahil on 3/26/2016.
 */
public class AllDisplay extends JPanel {
    int[] a = new int[2];
    boolean isRunning = false; 
    int delay = 5;
    public AllDisplay (int len){
        a = new int[len];
        for(int i = len; i > 0; i--){
            a[len-i] = (int)(Math.random() * len);
        }

      
        setSize(1200,500);
        setMinimumSize(new Dimension(500,500));
        JFrame frame = new JFrame();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JPanel buttons = new JPanel();
        JButton merger = new JButton();

        frame.setLayout(new BorderLayout());
        frame.add(buttons, BorderLayout.AFTER_LAST_LINE);
        frame.add(this, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1600,500);
        frame.setVisible(true);

        repaint();



    }
      
       
    public void gen(int len)
    {
        a = new int[len];
        for(int i = len; i > 0; i--){
            //a[len-i] = (int)(Math.random() * len);
            int rand = (int)(Math.random() * len );
            if(a[rand] == 0) a[rand] = i;
            else i++;
        }
        this.repaint();
        this.mysleep(1000);
    }
    public void paint(Graphics g) {
        System.out.println("painting");
        int width = this.getWidth();
        int height = this.getHeight();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        double blockWidth = 1. * width / a.length ;
        double blockHeight = 1. * height / a.length ;
        //g.setColor(Color.white);
        int hex = 0;
        for (int i = 0; i < a.length; i++) {
            //g.setColor(new Color((int)(100 + a[i] * .75),0,(int)(100 + a[i]*.75)));
            int r,gr,b;
            if(a[i] < a.length / 3){
                r = a[i] * 3;
                gr = 0;
                b = 0;
            }
            else if(a[i] < a.length * (2. / 3)){
                r = 0 ;
                gr = (a[i]-(a.length/3)) * 3;
                b = 0;
            }
            else{
                b = (int)((a[i]-  2 * a.length/3.) * 3);
                gr = 0;
                r = 0;
            }

            g.setColor(new Color(r,gr,b));
            int thisBlockHeight = (int)(blockHeight * a[i]);
            //int thisBlockHeight = blockHeight * a.length;
            g.fillRect((int)(i * blockWidth + .5), (height - thisBlockHeight), (int)(blockWidth + .5), thisBlockHeight);
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
    public  void mysleep(int time)  {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }

    public  void merge() {

        merge(0, a.length);

        repaint();


    }


    public  void merge(int stat, int end) {
        repaint();
        System.out.printf("stat %d end %d\n", stat, end);
        if (end - stat <= 1) return;
        merge(stat, (end + stat) / 2);
        merge((end + stat) / 2, end);
        mergeSorted( stat, (end + stat) / 2, (end + stat) / 2, end);

    }

    public void mergeSorted( int ss, int se, int es, int ee) {
        repaint();
        //sleep(100);
        //System.out.printf("ss = %d ee = %d\n", ss, ee);
        int counter = 0;
        int num = ee - ss;
        while (counter++ < num) {
            repaint();
            mysleep();
            //System.out.printf("ss %d\nse %d\n\n", ss, se);
            if (ss == se || es == ee) {
                break;
            }
            if (a[ss] > a[es]) {
                // shift and place
                int temp = a[es];
                for (int i = es; i > ss; i--) {
//                    j.repaint();
//                    mysleep(30);
                    a[i] = a[i - 1];
                }
                a[ss] = temp;
                ss++;
                se++;
                es++;
            } else {
                // leave in place;
                ss++;

            }
        }
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
        AllDisplay all = new AllDisplay(100);
        all.gen(150);
        all.mysleep(100);
        all.quick();
        all.mysleep(100);
        all.gen(150);
        all.mysleep(80); 
        all.merge();
        all.mysleep(100); 
        all.gen(150);
        all.mysleep(80); 
        all.heapSort();
    }
}
