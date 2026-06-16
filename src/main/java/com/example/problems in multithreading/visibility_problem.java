public class visibility_problem {
    static boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            }
            catch(Exception e) {}
            flag = true; // cache --> flag = true
        });

        Thread t2 = new Thread(() -> { // cache --> flag = false
            while(!flag) {
                // System.out.println("Thread 2 Running..."); // synchronized
                // do nothing
            }
            System.out.println("Thread 2 finished");
        });

        t1.start();
        t2.start();
    }
}
