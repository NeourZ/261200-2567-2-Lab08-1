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
         * ก่อนเพิ่ม Thread.sleep():
         * - เลขที่ออกมาจะสุ่มจากทั้ง 3 threads และไม่เรียงลำดับ
         * 
         * After adding Thread.sleep():
         * - เลขที่ออกมาจะสุ่มจากทั้ง 3 threads และไม่เรียงลำดับ แต่จะมีความหลากหลายมากขึ้นจากการใช้ Thread.sleep()
         * 
         * Explanation:
         * - การสลับการทำงานของเธรดขึ้นกับการจัดสรรของ CPU ทำให้ลำดับเปลี่ยนไปทุกครั้ง
         * - Thread.sleep() ทำให้บางเธรดหยุดชั่วคราว ส่งผลให้เธรดอื่นทำงานก่อน
         */
    }
}
