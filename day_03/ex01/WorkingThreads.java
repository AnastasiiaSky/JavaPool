package ex01;

public class WorkingThreads extends Thread {
    private String object;
    private int amount;

    public WorkingThreads(String object, int amount) {
        if(amount < 0) throw new IncorrectDataException();
        this.object = object;
        this.amount = amount;
    }

    @Override
    public void run() {
        while (amount > 0) {
            System.out.println(object);
            --amount;
        }
    }
}
