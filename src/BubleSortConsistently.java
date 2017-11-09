// Последовательный алгоритм чет-нечетной сортировки
public class BubleSortConsistently {

    public static void bubbleSort(long[] A, int left, int right) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i <= A.length - 2; i += 2) {
                if (A[i] > A[i + 1]) {
                    long temp = A[i];
                    A[i] = A[i + 1];
                    A[i + 1] = temp;
                    isSorted = false;
                }
            }
            for (int i = 1; i <= A.length - 2; i += 2) {
                if (A[i] > A[i + 1]) {
                    long temp = A[i];
                    A[i] = A[i + 1];
                    A[i + 1] = temp;
                    isSorted = false;
                }
            }
        }
    }
}