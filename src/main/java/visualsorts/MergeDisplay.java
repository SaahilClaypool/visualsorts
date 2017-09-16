package visualsorts;

import javax.swing.*;
import java.awt.*;



public class MergeDisplay extends JPanel {
    int[] ar = new int[2];
    int delay =2;
    public MergeDisplay(int len){
        ar = new int[len];
        for(int i = len; i > 0; i--){
            //ar[100-i] = (int)Math.random() * len;
            ar[len-i] = (int)(Math.random() * len);
        }
        this.repaint();
    }
    public void paint(Graphics g) {
        System.out.println("painting");
        int width = this.getWidth();
        int height = this.getHeight();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        int blockWidth = width / ar.length ;
        int blockHeight = height / ar.length;
        g.setColor(Color.white);
        for (int i = 0; i < ar.length; i++) {
            int thisBlockHeight = blockHeight * ar[i];
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
    public  void merge() {

        merge(0, ar.length);

        repaint();


    }


    public  void merge(int start, int end) {
        repaint();
        System.out.printf("start %d end %d\n", start, end);
        if (end - start <= 1) return;
        merge(start, (end + start) / 2);
        merge((end + start) / 2, end);
        mergeSorted( start, (end + start) / 2, (end + start) / 2, end);

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
            if (ar[ss] > ar[es]) {
                // shift and place
                int temp = ar[es];
                for (int i = es; i > ss; i--) {
//                    j.repaint();
//                    mysleep(30);
                    ar[i] = ar[i - 1];
                }
                ar[ss] = temp;
                ss++;
                se++;
                es++;
            } else {
                // leave in place;
                ss++;

            }
        }
    }

    public static void main(String[] args) {
        MergeDisplay j = new MergeDisplay(100);
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
        j.merge();



    }
}
