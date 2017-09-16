package visualsorts;
/**
 * Created by Saahil on 3/24/2016.
 */
public class Sorts {

    public static void quickSort(int[] ar) {
        quickSort(ar, 0, ar.length);
    }

    public static void quickSort(int[] ar, int start, int end) {
        System.out.printf("start = %d end = %d\n", start, end);
        if (end - start <= 1) {// if subsection is 1 retrun
            return;
        }
        int piv = ar[start];
        int j = end - 1;
        for (int i = start + 1; i <= j; i++) {
            if (ar[i] > piv) {// swap with end and dec
                int temp = ar[i];
                ar[i] = ar[j];
                ar[j] = temp;
                i--;
                j--;
            }
        }
        // swap piv and j

        printAr(ar);
        System.out.printf("j = %d\n", j);
        ar[start] = ar[j];
        ar[j] = piv;

        printAr(ar);
        quickSort(ar, start, j);
        quickSort(ar, j + 1, end);


    }

    public static void printAr(int[] ar) {
        for (int i : ar) {
            System.out.printf(" %d ,", i);
        }
        System.out.printf("\n");
    }


    public static void merge(int[] ar) {
        merge(ar, 0, ar.length);
    }

    public static void merge(int[] ar, int start, int end) {
        System.out.printf("start %d end %d\n", start, end);
        if (end - start <= 1) return;
        merge(ar, start, (end + start) / 2);
        merge(ar, (end + start) / 2, end);
        mergeSorted(ar, start, (end + start) / 2, (end + start) / 2, end);
    }

    public static void mergeSorted(int[] ar, int ss, int se, int es, int ee) {

        System.out.printf("ss = %d ee = %d\n", ss, ee);
        int counter = 0;
        int num = ee - ss;
        while (counter++ < num) {
            System.out.printf("ss %d\nse %d\n\n", ss, se);
            if (ss == se || es == ee) {
                break;
            }
            if (ar[ss] > ar[es]) {
                // shift and place
                int temp = ar[es];
                for (int i = es; i > ss; i--) {
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

    public static void heapSort(int[] ar) {
        // make heap
        for(int i = 0; i < ar.length; i++){
            makeHeap(ar, i);
        }
        //De Que
        for(int i = ar.length -1; i > -1; i--){
            dqHeap(ar, i);
       }
    }
    public static void dqHeap(int[] a, int insert){
        System.out.println("insert = " + insert);
        int max = a[0];
        a[0] = a[insert];
        a[insert] = max;// place max in correct spot

        //fix

        int i = 0;
        while(i*2 +2 < insert && (a[i] < a[i*2+1] || a[i] < a[i*2+2])){
            System.out.println("loop");
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
    public static void makeHeap(int[] a, int insert) {
        int i = insert;
        while(i > 0 && a[i]  > a[(i-1)/2]){
            // swap
            int temp = a[i];
            a[i] = a[(i-1)/2];
            a[(i-1)/2] = temp;
            i = (i-1)/2;
        }

    }

    public static void quick(int[] a){
        quick(a,0,a.length);
    }
    public static void quick(int[]a, int start, int end){
        // split
        if(end-start <= 1)
            return;

        int piv = a[start];
        int st=start+1,e=end-1;
        while(st <= e){
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
        quick(a, start, st-1);
        quick(a, st, end);
        // quick right
    }

    public static void main(String[] args) {
        // //int[] ar = new int[]{1,2,6,5,4,2,1};
        // //quickSort(ar);
        // //printAr(ar);
        // int[] twoSorted = new int[]{1,3,1,5};
        // printAr(twoSorted);
        // mergeSorted(twoSorted,0, twoSorted.length/2, twoSorted.length/2,twoSorted.length);
        // printAr(twoSorted);

        // int[] rand = new int[]{1,2,3,1,1,2};
        // printAr(rand);
        // mergeSorted(rand, 0,rand.length/2, rand.length/2, rand.length);
        // printAr(rand);
        // int num = 13;
        // int[] sorty = new int[num] ;
        // for(int i = 0; i < num; i++){
        //     sorty[i] = (int)(Math.random() * 100);
        // }
        // printAr(sorty);
        // merge(sorty); 

        // printAr(sorty);
//        int[] closeHeap = new int[]{1,2,3,1,2,3,4,5};
//        printAr(closeHeap);
//        heapSort(closeHeap);
//        printAr(closeHeap);
//        System.out.println("DQ");
//        dqHeap(closeHeap, closeHeap.length-1);
//        printAr(closeHeap);
//        dqHeap(closeHeap, closeHeap.length -2);
//        printAr(closeHeap);
//        dqHeap(closeHeap, closeHeap.length-3);
//        printAr(closeHeap);
        int[] rand = new int[]{5,4,3,2,1,0,1,2,3,4,5};
        printAr(rand);
        quick(rand);
        printAr(rand);
    }

}
