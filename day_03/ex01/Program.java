package ex01;

import java.util.Stack;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 1 && args[0].startsWith("--count=")) {
            String[] buffer = args[0].split("=");
            Integer amount = Integer.parseInt(buffer[1]);
            Object muter = new Object();
            Stack<String> data = new Stack<>();
            WorkingThreads egg = new WorkingThreads("Egg", amount, muter, data);
            WorkingThreads hen = new WorkingThreads("Hen", amount, muter, data);
            egg.start();
            hen.start();
            egg.join();
            hen.join();

        } else {
            System.out.println("Программа запускается с флагом {--count=n}, где n - колличество итераций выполнения");
            System.exit(0);
        }
    }
}
