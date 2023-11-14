class Compte {
    private int solde = 100;
    public int getSolde () {
        return solde ;
    }
    public void retirer ( int montant ){
        solde = solde - montant ;
    }
}// fin class Compte
 class JobSylvieEtBruno implements Runnable {
    private Compte compte = new Compte ();
    public static void main ( String [] args ){
        JobSylvieEtBruno tache = new JobSylvieEtBruno ();
        Thread t1 = new Thread ( tache );
        Thread t2 = new Thread ( tache );
        t1. setName (" Bruno ");
        t2. setName (" Sylvie ");
        t1. start ();
        t2. start ();
    } // fin methode main
    public void run (){
        for ( int x = 0; x < 10; x++) {
            effectuerRetrait (10);
            if ( compte . getSolde () < 0) {
                System .out. println (" decouvert !");
            }
        }
    } // fin methode run
    private void effectuerRetrait (int montant ){
        String nom = Thread . currentThread (). getName ();
        if( compte . getSolde () >= montant ) {
            System .out. println (nom + "va retirer ");
            try {
                System .out. println (nom + "va dormir ");
                Thread . sleep (500);
            } catch ( InterruptedException e) {}
            System .out. println (nom + "se reveille ");
            compte . retirer ( montant );
            System .out. println (nom + " retrait fait ");
        }
        else {
            System .out. println (nom + "pas assez ");
        }
    }// fin methode effectuerRetrait
}// fin class JobSylvieEtBruno
