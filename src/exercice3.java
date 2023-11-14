class EssaiScope
{
    static public long c1, c2, r1, r2;
    public static void main (String []arg)
    {
        Runnable logic = new Runnable () {
            public void run()
            {
                Runtime runtime = Runtime.getRuntime();
                c1 = runtime.totalMemory() - runtime.freeMemory(); // memory consumed
                r1 = runtime.freeMemory(); // memory remaining
                Integer i = new Integer (1);
                c2 = runtime.totalMemory() - runtime.freeMemory(); // memory consumed
                r2 = runtime.freeMemory(); // memory remaining
            }
        };
        Thread thread = new Thread(logic);
        thread.start();

        try {
            thread.join ();
        }catch (InterruptedException e) { }

        Runtime runtime = Runtime.getRuntime();
        System.out.println ("Avant création de l'objet Integer : espace consomme: "
                + c1 + ", espace restant : " + r1);
        System.out.println ("Apres création de l'objet Integer : espace consomme: "
                + c2 + " espace restant: " + r2);
        System.out.println ("A la fin de la thread TR, espace consomme: " +
                (runtime.totalMemory() - runtime.freeMemory()) + " espace restant : " + runtime.freeMemory());
    }
}
