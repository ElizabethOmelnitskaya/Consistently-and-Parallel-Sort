public class ThreadMerge extends Thread {

    int left, right;
    long[] A;
    static int n;

    ThreadMerge(long []A, int left, int right, int n) {
        this.left = left;
        this.right = right;
        this.A = A;
        this.n = n;
    }

    public void run() {
        doSort(left,right);
    }

    private  void doSort(int left, int right) {
        int half = (right + left) / 2;
        int i = left + 1, j = right;
        if(left == right){ return; }
        if(i == j && A[i] > A[j]) {
            long temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }else{
            if(n>0) {
                Thread t = new Thread(new ThreadMerge(A, left, half, n));
                t.start();
                n--;
                doSort(half + 1, right);
                try {
                    t.join();
                } catch (InterruptedException e) {
                }
            }else {
                doSort(left,half);
                doSort(half + 1, right);
            }
        }
        MergeSortConsistently.merge(A,left,half,right);
    }
}
