package ex00;

public class ThreadsWork {
    private Integer amount;

    public ThreadsWork(Integer amount) {
        this.amount = amount;
        execute();
    }

    public void execute() {
        Thread thread1 = new Thread(new Hen(amount));
        Thread thread2 = new Thread(new Egg(amount));

        thread1.start();
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
