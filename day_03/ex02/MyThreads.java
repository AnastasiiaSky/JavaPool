package ex02;

import java.util.ArrayList;

public class MyThreads extends Thread {
    private final ArrayList<Integer> partToCount;
    private final int start;
    private final int finish;
    private int sumOfThePart = 0;

    public MyThreads(ArrayList<Integer> partToCount, int start, int finish) {
        this.partToCount = partToCount;
        this.start = start;
        this.finish = finish;
    }

    @Override
    public void run() {
        for (int it = 0; it < partToCount.size(); ++it) {
            this.sumOfThePart += partToCount.get(it);
        }
        printInfo();
    }

    private void printInfo() {
        System.out.println(Thread.currentThread().getName() + ": from " + this.start + " to " + this.finish
                + " sum is " + sumOfThePart);
    }

    public int getSumOfThePart() {
        return this.sumOfThePart;
    }
}
