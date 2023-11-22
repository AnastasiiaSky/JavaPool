package ex00;

public class Hen implements Runnable {
    private Integer amount;

    public Hen(Integer amount) {
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int it = 0; it < amount; ++it) {
            System.out.println("Hen");
        }
    }
}
