//последовательный алгоритм быстрой сортировки
public class QuickSortConsistently {
    // функция быстрой сортировки
    public static void quickSort(int [] A, int start, int end){
        int half = start + (end - start)/2; // Центральный элемент
        int basis = A[half];
        int i = start;
        int j = end;

        // Сортировка относительно центра
        while(i<=j){
            while(A[i] < basis) i++;
            while(A[j] > basis) j--;
            // Процедура разделения
            if(i<=j){
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                i++;
                j--;
            }
        }
        // Рекурсивные вызовы, если есть, что сортировать
        if(start < j) quickSort(A, start, j);
        if(end > i) quickSort(A, i, end);
    }
}