import java.util.concurrent.CyclicBarrier;

public class BubbleSortParallel {
    private static long[] A;
    private static int n;
    static CyclicBarrier cb;
    BubbleSortParallel(long[] m, int n) {
        A = new long[m.length];
        System.arraycopy(m,0,A,0,m.length);
        this.n = n;
        cb = new CyclicBarrier(n);
    }

    public static long[] sort(long[] check) {
        try {
            int parts = A.length/n, pos = 0;
            Thread[] t = new Thread[n];
            ThreadBubble []threads = new ThreadBubble[n];
            for(int i = 0; i < n; i++) {
                if(i==n-1) threads[i] =new ThreadBubble(cb,A,pos,A.length-1,check);
                else threads[i] = new ThreadBubble(cb, A, pos, pos += (parts - 1),check);
                t[i] = new Thread(threads[i],""+i);
                t[i].start();
                pos++;
            }
            t[0].join();
        } catch (InterruptedException e) {System.out.println(e);}
        return A;
    }
}
