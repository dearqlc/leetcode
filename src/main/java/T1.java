import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author QLC
 */
public class T1 {

    /**
     * 控制打印次数
     */
    private final int times;
    /**
     * 当前状态值：保证三个线程之间交替打印
     */
    private int state;

    private final Lock lock = new ReentrantLock();

    public T1(int times) {
        this.times = times;
    }

    private void printAbc(String name, int targetNum) {
        for (int i = 0; i < times;){
            lock.lock();
            try {
                if (state % 3 == targetNum) {
                    state++;
                    // 成功打印了才进行下一次循环
                    i++;
                    System.out.print(name);
                }
            }
            finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) {

        //顺序打印10次
        T1 t1 = new T1(2);

        new Thread(() -> t1.printAbc("A", 0), "A").start();

        new Thread(() -> t1.printAbc("B", 1), "B").start();

        new Thread(() -> t1.printAbc("C", 2), "C").start();
    }

}
