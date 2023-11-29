package ex00;

public class Program {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].startsWith("--count=")) {
            String[] buffer = args[0].split("=");
            Integer amount = Integer.parseInt(buffer[1]);
            ThreadsWork hen = new ThreadsWork(amount, "Hen");
            ThreadsWork egg = new ThreadsWork(amount, "Egg");
            try {
                egg.join();
                hen.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int it = 0; it < amount; ++it) {
                System.out.println("Human");
            }
        } else {
            System.out.println("Программа запускается с флагом {--count=n}, где n - колличество итераций выполнения");
            System.exit(0);
        }
    }
}
