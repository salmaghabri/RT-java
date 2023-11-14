package tutorial;

class BufferCirc {
    private Object [] tampon ;
    private int taille ;
    private int prem , der , nbObj ;
    public BufferCirc ( int t) {
        taille = t;
        tampon = new Object [ taille ];
        prem = 0;
        der = 0;
        nbObj = 0;
    }
    public synchronized void depose ( Object o) {
        while ( nbObj == taille ) {
            try { wait ();}
            catch ( InterruptedException e) {}
        }
        tampon [der] = o;
        der = (der + 1) % taille ;
        nbObj = nbObj + 1;
        notify ();
    }
    public synchronized Object preleve () {
        while ( nbObj == 0) {
            try { wait ();}



catch ( InterruptedException e) {}
        }
        Object o = tampon [ prem ];
        tampon [ prem ] = null ;
        prem = ( prem + 1) % taille ;
        nbObj = nbObj - 1;
        notify ();
        return (o);
    }
} // fin class BufferCirc
class Producteur implements Runnable {
    private BufferCirc buffer ;
    private int val ;
    public Producteur ( BufferCirc b) {
        buffer = b;
    }
    public void run () {
        while ( true ) {
            buffer . depose (new Integer (val ));
            System .out. println ( Thread . currentThread (). getName () +
                    " a depose " + val );
            val ++;
            try {
                Thread . sleep (( int )( Math . random ()*100));
            }
            catch ( InterruptedException e) {}
        }
    }
} // fin classe Producteur
class Consommateur implements Runnable {
    private BufferCirc buffer ;
    public Consommateur ( BufferCirc b) {
        buffer = b;
    }
    public void run () {
        Integer val;
        while ( true ) {
            val = ( Integer ) buffer . preleve ();
            System .out. println ( Thread . currentThread (). getName () +
                    " a preleve " + val );


            try {
                Thread . sleep (( int )( Math . random ()*300));
            }
            catch ( InterruptedException e) {}
        }
    }
} // fin classe Consommateur
class Principal {
    public static void main ( String [] args ) {
        BufferCirc b = new BufferCirc (20);
        Producteur p = new Producteur (b);
        Consommateur c = new Consommateur (b);
        Thread P1 = new Thread (p);
        P1. setName ("P1");
        Thread C1 = new Thread (c);
        C1. setName ("C1");
        P1. start ();
        C1. start ();
    }
}
