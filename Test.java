import java.util.Arrays;
import java.util.Random;

public class Test {
    public static int n = 2048000;
    public static int A[] = new int[n];

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < A.length; i++) {
            A[i] = new Random().nextInt() % 20 + 20;
        }

        //System.out.println("Basis array: " + Arrays.toString(A));

        //сортировка слиянием
        long startTime1 = System.nanoTime();
        MergeSortConsistently.mergeSort(A, 0, A.length - 1);
        long finishTime1 = System.nanoTime();
        long Time1 = finishTime1 - startTime1;
        //System.out.println("Marge sort: " + Arrays.toString(A));
        System.out.println("Time MergeSortConsistently: " + Time1);

        // сортировка слиянием для случая 2х потоков
        long startTime2 = System.nanoTime();
        sort1();
        long endTime2 = System.nanoTime();
        long Time2 = endTime2 - startTime2;
        System.out.println("Time MergeSortParallel by 2Thread : " + Time2);

        // сортировка слиянием для случая 4х потоков
        long startTime3 = System.nanoTime();
        sort2();
        long endTime3 = System.nanoTime();
        long Time3 = endTime3 - startTime3;
        System.out.println("Time MergeSortParallel by 4Thread: " + Time3);

        //быстрая сортировка
        long startTime4 = System.nanoTime();
        QuickSortConsistently.quickSort(A, 0, A.length-1);
        long endTime4 = System.nanoTime();
        long Time4 = endTime4 - startTime4;
        System.out.println("Time QuickSortConsistently : " + Time4);

        //быстрая сортировка для 2х потоков
        long startTime5 = System.nanoTime();
        sort3();
        long endTime5 = System.nanoTime();
        long Time5 = endTime5 - startTime5;
        System.out.println("Time QuickSortParallel by 2Thread : " + Time5);

        //быстрая сортировка для 2х потоков
        long startTime6 = System.nanoTime();
        sort3();
        long endTime6 = System.nanoTime();
        long Time6 = endTime6 - startTime6;
        System.out.println("Time QuickSortParallel by 4Thread : " + Time6);

        //чет-нечетная сортировка
        long startTime7 = System.nanoTime();
        BubleSortConsistently.bubbleSort(A, 0, A.length-1);
        long endTime7 = System.nanoTime();
        long Time7 = endTime7 - startTime7;
        //System.out.println("Bubble sort: " + Arrays.toString(A));
        System.out.println("Time BubbleSortConsistently: " + Time7);

        long startTime8 = System.nanoTime();
        sort5();
        long endTime8 = System.nanoTime();
        long Time8 = endTime8 - startTime8;
        System.out.println("Time BubbleSortParallel by 2Thread: " + Time8);

        long startTime9 = System.nanoTime();
        sort6();
        long endTime9 = System.nanoTime();
        long Time9 = endTime9 - startTime9;
        System.out.println("Time BubbleSortParallel by 4Thread: " + Time9);
    }
    /***********************************************************************/
    //сортировка слиянием для случая 2х потоков
    public static class Thread1 extends Thread {
        public void run() {
            int lenght = A.length / 2;
            MergeSortConsistently.mergeSort(A, 0, lenght);
            //QuickSortConsistently.quickSort (A,0, lenght);
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            int lenght = A.length / 2 + 1;
            MergeSortConsistently.mergeSort(A, lenght, A.length - 1);
            //QuickSortConsistently.quickSort (A, lenght, A.length-1);
        }
    }

    public static void sort1() throws Exception {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        MergeSortConsistently.merge(A, 0, A.length / 2, A.length - 1);
    }

    //для случая 4х потоков
    public static class Thread3 extends Thread {
        public void run() {
            int lenght = A.length / 4;
            MergeSortConsistently.mergeSort(A, 0, lenght);
        }
    }

    public static class Thread4 extends Thread {
        public void run() {
            int lenght = A.length / 4 + 1;
            MergeSortConsistently.mergeSort(A, lenght, A.length / 2);
        }
    }

    public static class Thread5 extends Thread {
        public void run() {
            int lenght = A.length / 2 + 1;
            MergeSortConsistently.mergeSort(A, lenght, A.length * 3 / 4);
        }
    }

    public static class Thread6 extends Thread {
        public void run() {
            int lenght = A.length * 3 / 4 + 1;
            MergeSortConsistently.mergeSort(A, lenght, A.length - 1);
        }
    }

    public static void sort2() throws Exception {
        Thread3 t3 = new Thread3();
        Thread4 t4 = new Thread4();
        Thread5 t5 = new Thread5();
        Thread6 t6 = new Thread6();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        MergeSortConsistently.merge(A, 0, A.length / 2, A.length - 1);
    }
    /******************************************************************************************/

    // быстрая сортировка для случая 2х потоков
    public static class Thread7 extends Thread {
        public void run() {
            int lenght = A.length / 2;
            QuickSortConsistently.quickSort (A,0, lenght);
        }
    }

    public static class Thread8 extends Thread {
        public void run() {
            int lenght = A.length / 2 + 1;
            QuickSortConsistently.quickSort (A, lenght, A.length-1);
        }
    }

    public static void sort3() throws Exception {
        Thread7 t7 = new Thread7();
        Thread8 t8 = new Thread8();
        t7.start();
        t8.start();
        t7.join();
        t8.join();
        QuickSortConsistently.quickSort(A, 0, A.length - 1);
    }

    //для случая 4х потоков
    public static class Thread9 extends Thread {
        public void run() {
            int lenght = A.length / 4;
            QuickSortConsistently.quickSort (A,0, lenght);
        }
    }

    public static class Thread10 extends Thread {
        public void run() {
            int lenght = A.length / 4 + 1;
            QuickSortConsistently.quickSort (A, lenght, lenght/2);
        }
    }

    public static class Thread11 extends Thread {
        public void run() {
            int lenght = A.length / 2 + 1;
            QuickSortConsistently.quickSort(A, lenght, A.length*3/4);
        }
    }

    public static class Thread12 extends Thread {
        public void run() {
            int lenght = A.length * 3 / 4 + 1;
            QuickSortConsistently.quickSort(A, lenght, A.length-1);
        }
    }

    public static void sort4() throws Exception {
        Thread9 t9 = new Thread9();
        Thread10 t10 = new Thread10();
        Thread11 t11 = new Thread11();
        Thread12 t12 = new Thread12();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t9.join();
        t10.join();
        t11.join();
        t12.join();
        QuickSortConsistently.quickSort(A, 0,  A.length - 1);
    }
    /***********************************************************************/

    //чет-нечет для случая 2х потоков
    public static class Thread13 extends Thread {
        public void run() {
            int lenght = A.length / 2;
            BubleSortConsistently.bubbleSort (A,0, lenght);
        }
    }

    public static class Thread14 extends Thread {
        public void run() {
            int lenght = A.length / 2 + 1;
            BubleSortConsistently.bubbleSort (A, lenght, A.length-1);
        }
    }

    public static void sort5() throws Exception {
        Thread13 t13 = new Thread13();
        Thread14 t14 = new Thread14();
        t13.start();
        t14.start();
        t13.join();
        t14.join();
        BubleSortConsistently.bubbleSort(A, 0, A.length - 1);
    }

    //для случая 4х потоков
    public static class Thread15 extends Thread {
        public void run() {
            int lenght = A.length / 4;
            BubleSortConsistently.bubbleSort (A,0, lenght);
        }
    }

    public static class Thread16 extends Thread {
        public void run() {
            int lenght = A.length / 4 + 1;
            BubleSortConsistently.bubbleSort (A, lenght, lenght/2);
        }
    }

    public static class Thread17 extends Thread {
        public void run() {
            int lenght = A.length / 2 + 1;
            BubleSortConsistently.bubbleSort(A, lenght, A.length*3/4);
        }
    }

    public static class Thread18 extends Thread {
        public void run() {
            int lenght = A.length * 3 / 4 + 1;
            BubleSortConsistently.bubbleSort(A, lenght, A.length-1);
        }
    }

    public static void sort6() throws Exception {
        Thread15 t15 = new Thread15();
        Thread16 t16 = new Thread16();
        Thread17 t17 = new Thread17();
        Thread18 t18 = new Thread18();
        t15.start();
        t16.start();
        t17.start();
        t18.start();
        t15.join();
        t16.join();
        t17.join();
        t18.join();
        BubleSortConsistently.bubbleSort(A, 0,  A.length - 1);
    }
}

