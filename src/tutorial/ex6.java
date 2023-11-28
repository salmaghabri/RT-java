package tutorial;

class MonObjett {
    public synchronized void action1(MonObjett o) {
        try {
            Thread.currentThread().sleep(200);
        } catch (InterruptedException ex) {
            return;
        }
        o.action2(this);
    }

    public synchronized void action2(MonObjett o) {
        try {
            Thread.currentThread().sleep(200);
        } catch (InterruptedException ex) {
            return;
        }
        o.action1(this);
    }
}

class MonThreadd extends Thread {
    private MonObjett obj1, obj2;

    public MonThreadd(MonObjett o1, MonObjett o2) {
        obj1 = o1;
        obj2 = o2;
    }

    public void run() {
        obj1.action1(obj2);
    }
}

 class Deadlockk {
    public static void main(String[] args) {
        MonObjett o1 = new MonObjett();
        MonObjett o2 = new MonObjett();
        MonThreadd t1 = new MonThreadd(o1, o2);
        t1.setName("t1");
        MonThreadd t2 = new MonThreadd(o2, o1);
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}

