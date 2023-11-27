package ex02;


public class Program {
    public static void main(String[] args) {
        if (args.length == 2 && args[0].startsWith("--arraySize=") && args[1].startsWith("--threadsCount=")) {
            String[] buffer = args[0].split("=");
            int arraySize = Integer.parseInt(buffer[1]);
            if (arraySize < 1 || arraySize > 2_000_000) throw new IncorrectInputException();
            buffer = args[1].split("=");
            int threads = Integer.parseInt(buffer[1]);
            if (threads > arraySize) throw new IncorrectInputException();
            WorkingProcess processor = new WorkingProcess(arraySize, threads);
            processor.execute();
            System.out.println(processor.getResultSum());
        } else {
            System.out.println("Программа запускается с флагами {--arraySize=n}, где n - размер массива и " +
                    "{--threadsCount=n}, где n - число потоков");
            System.exit(0);
        }
    }
}