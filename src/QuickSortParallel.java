public class QuickSortParallel {
    private long[] A;
    private int n;

    QuickSortParallel(long[] m, int n) {
        A = new long[m.length];
        System.arraycopy(m, 0, A, 0, m.length);
        this.n = n;
    }
    public long[] sort() {
        n--;
        Thread t = new Thread(new ThreadQuick(A,0,A.length-1,n));
        t.start();

        try { t.join(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return A;
    }
}