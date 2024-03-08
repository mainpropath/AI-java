package com.ai.openai;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockPrinter {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int number = 0;

    public static void main(String[] args) {
        new Thread(() -> LockPrinter.printNumber(1), "A").start();
        new Thread(() -> LockPrinter.printNumber(2), "B").start();
        new Thread(() -> LockPrinter.printNumber(3), "C").start();
    }

    public static void printNumber(int target) {
        while (number <= 10) {
            try {
                lock.lock();
                if ((number % 3 + 1) != target) condition.await();
                else {
                    System.out.println(Thread.currentThread().getName() + " : " + target);
                    number++;
                    condition.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

class SyncPrinter {

    private static int number = 0;

    public static void main(String[] args) {
        new Thread(() -> SyncPrinter.printNumber(1), "A").start();
        new Thread(() -> SyncPrinter.printNumber(2), "B").start();
        new Thread(() -> SyncPrinter.printNumber(3), "C").start();
    }

    public static void printNumber(int target) {
        while (number <= 10) {
            if (number % 3 + 1 == target)
                synchronized (SyncPrinter.class) {
                    if (number % 3 + 1 == target) {
                        System.out.println(Thread.currentThread().getName() + " : " + target);
                        number++;
                    }
                }
        }
    }

}
