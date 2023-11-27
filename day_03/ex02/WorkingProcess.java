package ex02;

import java.util.ArrayList;

public class WorkingProcess {
    private final int arraySize;
    private final int threadsCount;
    private final ArrayCreator creator;
    private final ArrayList<Integer> generatedArray;
    private int standartSum;
    private int sizeForThreads;
    private volatile int resultSum;

    public WorkingProcess(int arraySize, int threadsCount) {
        this.arraySize = arraySize;
        this.threadsCount = threadsCount;
        this.creator = new ArrayCreator(arraySize);
        this.generatedArray = creator.getNumbers();
        this.standartSum = getStandartSum();
        this.sizeForThreads = getSizeFoThreads();
    }

    public void execute() {

        System.out.println(standartSum);
        int start = 0;
        int finish = sizeForThreads;
        for (int it = 0; it < threadsCount; ++it) {
            ArrayList<Integer> current = new ArrayList<Integer>();
            if (it == threadsCount - 1) {
                for (int j = start; j < generatedArray.size(); ++j) {
                    current.add(generatedArray.get(j));
                }
                MyThreads currentThread = new MyThreads(current, start, generatedArray.size());
                currentThread.start();
                try {
                    currentThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resultSum += currentThread.getSumOfThePart();
            } else {
                for (int j = start; j < finish; ++j) {
                    current.add(generatedArray.get(j));
                }
                MyThreads currentThread = new MyThreads(current, start, finish);
                currentThread.start();
                try {
                    currentThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                start = finish;
                finish += sizeForThreads;
                resultSum += currentThread.getSumOfThePart();
            }
        }
    }

    public int getResultSum() {
        return resultSum;
    }

    private int getSizeFoThreads() {
        return (int) arraySize / threadsCount;
    }

    private int getStandartSum() {
        StandartSummator sum1 = new StandartSummator(creator);
        return sum1.getTotalSum();
    }

}
