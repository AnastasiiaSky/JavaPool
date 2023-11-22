package ex00;

public class ThreadsWork extends Thread{
    private volatile Integer amount;
    private volatile String object;


    public ThreadsWork(Integer amount, String object) {
        this.amount = amount;
        this.object = object;
        start();
        run();
    }

    @Override
    public void run() {
        for(int it = 0; it < amount; ++it) {
            System.out.println(object);
        }
    }
}
