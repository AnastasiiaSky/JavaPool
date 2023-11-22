package ex00;

public class Egg implements Runnable {
    private Integer amount;

    public Egg(Integer amount) {
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int it = 0; it < amount; ++it) {
            System.out.println("Egg");
        }
    }
}
