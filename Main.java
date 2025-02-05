import java.util.Random;

class NumberPrinter implements Runnable {
    private int threadNumber;
    private static final Random random = new Random();

    public NumberPrinter(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("Thread #" + threadNumber + " : " + i);
            
            try {
                Thread.sleep(random.nextInt(601));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(new NumberPrinter(1));
        Thread t2 = new Thread(new NumberPrinter(2));
        Thread t3 = new Thread(new NumberPrinter(3));
        
        t1.start();
        t2.start();
        t3.start();

        // Observations:
        /*
         * Before adding Thread.sleep():
         * - The output is unpredictable.
         * - The numbers appear in a mixed order from all three threads.
         * - Since threads execute independently, we may see multiple threads printing before another completes.
         * 
         * After adding Thread.sleep():
         * - The output is even more random because delays cause thread execution to shift unpredictably.
         * - Threads do not print in a strict sequence.
         * - Each execution gives a different output due to the randomized delays.
         * 
         * Explanation:
         * - In Java, threads run independently and the OS manages execution order.
         * - The CPU switches between threads using a technique called **context switching**.
         * - `Thread.sleep()` pauses a thread, allowing other threads to execute, increasing unpredictability.
         */
    }
}
