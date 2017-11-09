public class ThreadQuick extends Thread{
    static int n;
    int start, end;
    long[] A;
    ThreadQuick(long []A, int start, int end, int n) {
        this.start = start;
        this.end = end;
        this.A = A;
        this.n = n;
    }
    public void run() {
        doSort(start,end);
    }

    private  void doSort(int firstIndex, int lastIndex) {
        int end = lastIndex, start = firstIndex, half = start + (end - start)/2;
        if(start>=end) {
            return;
        }
       QuickSortConsistently.quickSort(A,start,end);
        if(n > 0) {
            Thread t = new Thread(new ThreadQuick(A, firstIndex, half, n));
            n--;
            t.start();
            doSort(half + 1, lastIndex);
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        }else {
            doSort(firstIndex, half);
            doSort(half + 1, lastIndex);
        }
    }
}