import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class ThreadBubble extends Thread {
    int left, right, count = 0;
    long[] A,check;
    CyclicBarrier cb;
    ThreadBubble(CyclicBarrier cb,long []A, int left, int right, long[] check) {
        this.left = left;
        this.right = right;
        this.A = A;
        this.cb = cb;
        this.check = check;
    }
    public void run() {
        while(!sortBubble()) { }
        cb.reset();
    }
    public boolean sortBubble(){
        try {
            cb.await();
            if(count%2==0) {
                for(int i = left; i <= right; i+=2) {
                    if (i != A.length-1 ) {
                        if (A[i] > A[i + 1]) {
                            long temp = A[i];
                            A[i] = A[i + 1];
                            A[i + 1] = temp;
                        }
                    }
                }
            }
            else {
                for (int i = left + 1; i <= right; i += 2) {
                    if(i != A.length-1) {
                        if (A[i] > A[i + 1]) {
                            long temp = A[i];
                            A[i] = A[i + 1];
                            A[i + 1] = temp;
                        }
                    }
                }
            }
            count++;
        } catch (Exception e) {}
        return (Arrays.equals(A,check));
    }
}
