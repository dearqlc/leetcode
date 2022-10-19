import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程顺序打印ABC
 * @author QLC
 */
public class T0 {

    ReentrantLock lock= new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    private int value = 0;
    /**
     * 打印多少遍
     */
    private final int count;

    private T0(int count) {
        this.count = count;
    }

    private void printAbc() {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
        new Thread(new ThreadC()).start();
    }

    class ThreadA implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (value % 3 != 0) {
                        conditionA.await();
                    }
                    System.out.print("A");
                    conditionB.signal();
                    value++;
                }
            } catch (InterruptedException e ) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    class ThreadB implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (value % 3 != 1) {
                        conditionB.await();
                    }
                    System.out.print("B");
                    conditionC.signal();
                    value++;
                }
            } catch (InterruptedException e ) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    class ThreadC implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (value % 3 != 2) {
                        conditionC.await();
                    }
                    System.out.print("C");
                    conditionA.signal();
                    value++;
                }
            } catch (InterruptedException e ) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {

        T0 t0 = new T0(5);
        t0.printAbc();

    }

}
