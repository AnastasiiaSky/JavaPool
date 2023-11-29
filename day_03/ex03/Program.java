package ex03;

public class Program {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].startsWith("--threadsCount=")) {
            String[] buffer = args[0].split("=");
            int threadsCount = Integer.parseInt(buffer[1]);
            WorkingProcess processor = new WorkingProcess(threadsCount);
        } else {
            System.out.println("Программа запускается с флагами {--threadsCount=n}, где n - число потоков");
            System.exit(0);
        }
    }
}
