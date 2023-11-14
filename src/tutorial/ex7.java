package tutorial;

class MonObjet {
    static private int i;
    private String name ;
    public MonObjet ( String n, int x) {
        i = x;
        name = n;
    }
    public void action ( int x) {
        System .out. println ( Thread . currentThread (). getName ()
                + " au porte de " + name );
        ajoute (x, name );
        System .out. println ( Thread . currentThread (). getName ()
                + " est sorti de " + name );
    }
    public static synchronized void ajoute ( int x, String n) {
        System .out. println ( Thread . currentThread (). getName ()
                + " entre dans " + n);
        try {
            System .out. println ( Thread . currentThread (). getName ()
                    + " attend ");
            Thread . currentThread (). sleep (200);
        }
        catch ( InterruptedException ex) { return ; }
        System .out. println ( Thread . currentThread (). getName ()
                + " ajoute dans " + n);
        System .out. println ("i = " + i);
        i = i + x;
        System .out. println (" : i = " + i);
        System .out. println ( Thread . currentThread (). getName ()
                + " sort de " + n);
    }
} // fin classe MonObjet
class MonThread2 extends Thread {
    private MonObjet Obj ;
    private int a;
    public MonThread2 ( MonObjet o, int x) {
        Obj = o;
        a = x;
    }
    public void run () {
        Obj . action (a);
    }


} // fin classe MonThread2
class Principale {
    public static void main ( String [] args ) {
        MonObjet o1 = new MonObjet ("o1", 1);
        MonObjet o2 = new MonObjet ("o2", 2);
        MonThread2 t1 = new MonThread2 (o1 , 3);
        MonThread2 t2 = new MonThread2 (o2 , 5);
        t1. setName ("t1");
        t2. setName ("t2");
        t1. start ();
        t2. start ();
    }
} // fin classe Principale
