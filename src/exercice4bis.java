import java.util.concurrent.*;

class MultiThreads {
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {
        Runnable task1 = () -> {
            try {
                Thread.sleep(200);
                System.out.println("Task1 completed");
            } catch (InterruptedException e) {
                System.out.println("Task1 was interrupted");
            }
        };

        Runnable task2 = () -> {
            try {
                Thread.sleep(200);
                System.out.println("Task2 completed");
            } catch (InterruptedException e) {
                System.out.println("Task2 was interrupted");
            }
        };

        ScheduledFuture<?> future1 = executorService.scheduleAtFixedRate(task1, 0, 500, TimeUnit.MILLISECONDS);
        ScheduledFuture<?> future2 = executorService.scheduleAtFixedRate(task2, 0, 1000, TimeUnit.MILLISECONDS);

        executorService.schedule(() -> {
            future1.cancel(true);
            future2.cancel(true);
        }, 10, TimeUnit.SECONDS);
    }
}
