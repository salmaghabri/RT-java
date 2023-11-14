class Thread1 implements Runnable {
    public void run (){
        for(int i = 1; i <=26; i++) System . out. println (i +" ");
    }
}
class Thread2 implements Runnable {
    public void run (){
        for(int i = 'a'; i <= 'z'; i++) System .out. println (i +" ");
    }
}
class MonProgramme {
    public static void main ( String [] args ) {
        Runnable t1 = new Thread1 ();
        Runnable t2 = new Thread2 ();
        new Thread (t1 ). start ();
        new Thread (t2 ). start ();
    }
}
