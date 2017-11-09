import java.util.Arrays;

public class Test {
    public static int n = 50000;
    public static long A[] = new long[n];
    public static long check[] = new long[A.length]; // массив для проверки
    public static long [] tempA; // текущий массив при параллельном выполнении
    static long startTime, endTime;
    static int numberOfThreads = 4; // 2, 4, 8

    public static void main(String[] args) {

        /******************************************************************************************************************/

        for (int i = 0; i < A.length; i++) {
            A[i] = (int) (Math.random() * 100);
        }
        //System.out.println("Basis array: " + Arrays.toString(A)); // неотсортированный массив
        System.arraycopy(A,0,check,0,A.length);
        //System.out.println("Check: " + Arrays.toString(check));
        Arrays.sort(check);
        //System.out.println("Check: " + Arrays.toString(check));

        // последовательная сортировка слиянием
        startTime = System.nanoTime();
        MergeSortConsistently.mergeSort(A, 0, A.length - 1);
        endTime = System.nanoTime();
        long Time1 = endTime - startTime;
        //System.out.println("Marge sort: " + Arrays.toString(A));
        System.out.println("Time MergeSortConsistently: " + Time1);

        // последовательная быстрая сортировка
        startTime = System.nanoTime();
        QuickSortConsistently.quickSort(A, 0, A.length - 1);
        endTime = System.nanoTime();
        long Time2 = endTime - startTime;
        //System.out.println("QuickSort: " + Arrays.toString(A));
        System.out.println("Time QuickSortConsistently : " + Time2);

        // последовательная чет-нечетная сортировка
        startTime = System.nanoTime();
        BubleSortConsistently.bubbleSort(A, 0, A.length - 1);
        endTime = System.nanoTime();
        long Time3 = endTime - startTime;
        //System.out.println("Bubble sort: " + Arrays.toString(A));
        System.out.println("Time BubbleSortConsistently: " + Time3);

        /******************************************************************************************************************/

        //многопоточная сортировка слиянием
        MergeSortParallel mergeSort = new MergeSortParallel(A,numberOfThreads);
        startTime = System.nanoTime();
        tempA = mergeSort.sort();
        endTime = System.nanoTime();
        if(Check(check,tempA)) System.out.println("TempA is sorted!");
        else System.out.println("TempA not sorted!");
        long Time4 = endTime - startTime;
        //System.out.println("Merge sort parallel: " + Arrays.toString(tempA));
        System.out.println("Time MergeSortParallel: " + Time4);

        //многопоточная быстрая сортировка
        QuickSortParallel quickSort = new QuickSortParallel(A,numberOfThreads);
        startTime = System.nanoTime();
        tempA = quickSort.sort();
        endTime = System.nanoTime();
        if(Check(check,tempA)) System.out.println("TempA is sorted!");
        else System.out.println("TempA not sorted!");
        long Time5 = endTime - startTime;
        //System.out.println("Quick sort parallel: " + Arrays.toString(tempA));
        System.out.println("Time QuickSortParallel: " + Time5);

        //многопоточная чет-нечет сортировка
        BubbleSortParallel bubbleSort = new BubbleSortParallel(A,numberOfThreads);
        startTime = System.nanoTime();
        tempA = bubbleSort.sort(check);
        endTime = System.nanoTime();
        if(Check(check,tempA)) System.out.println("TempA is sorted!");
        else System.out.println("TempA not sorted!");
        long Time6 = endTime - startTime;
        //System.out.println("Bubble sort parallel: " + Arrays.toString(tempA));
        System.out.println("Time BubbleSortParallel: " + Time6);
    }

    //проверка отсортированого массива
    public static boolean Check(long[] A, long[] result){
        return Arrays.equals(A,result);
    }
}