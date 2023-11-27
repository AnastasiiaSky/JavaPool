package ex01;

import java.util.Stack;

public class WorkingThreads extends Thread {
    private Object muter;
    private Stack<String> data;

    private String object;
    private int amount;
    private ProducerConsumer prodCons;
    public WorkingThreads(String object, int amount, Object muter, Stack<String> data) {
        if(amount < 0) throw new IncorrectDataException();
        this.object = object;
        this.amount = amount;
        this.muter = muter;
        this.data = data;
        this.prodCons = new ProducerConsumer(muter, data);
    }

    @Override
    public void run() {
        while (amount > 0) {
            try {
                prodCons.putObj(object);
                --amount;
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
