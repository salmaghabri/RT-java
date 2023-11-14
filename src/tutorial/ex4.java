package tutorial;

class Solde {
    private int solde = 0;
    public Solde () { }
    public synchronized void incrementer () {
        int n = solde ;
        try { Thread . currentThread() . sleep (200);}
        catch ( InterruptedException e) {}
        solde = n + 1;
    }
}
class MaTache implements Runnable {


    private Solde sld ;
    public MaTache ( Solde s) { sld = s; }
    public void run () { sld. incrementer (); }
    public static void main ( String [] args ) {
        Solde s = new Solde ();
        Thread t1 = new Thread (new MaTache (s ));
        Thread t2 = new Thread (new MaTache (s ));
        t1. setName ("t1");
        t2. setName ("t2");
        t1. start ();
        t2. start ();
    }
}
 class Semaphore {
    int n;
    String name ;
    public Semaphore (int max , String S) {
        n = max;
        name = S;
    }
    public synchronized void P() {
        if (n == 0) {
            try { wait (); }
            catch ( InterruptedException e) {}
        }
        n--;
    }
    public synchronized void V() {
        n++;
        notify ();
    }
}
