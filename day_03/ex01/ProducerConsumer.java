package ex01;

import java.util.Stack;


public class ProducerConsumer {

    private Stack<String> data;
    private Object muter;

    public ProducerConsumer(Object muter, Stack<String> data) {
        this.muter = muter;
        this.data = data;
    }


    public void putObj(String object) throws InterruptedException {
        synchronized (muter) {
            if (object.equals("Egg")) {
                while (data.size() % 2 != 0) {
                    muter.wait();
                }
            } else {
                while (data.size() % 2 == 0) {
                    muter.wait();
                }
            }
            data.push(object);
            System.out.println(object);
            muter.notifyAll();
        }
    }
}
