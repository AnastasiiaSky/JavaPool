package ex01;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;

/*Давайте организуем аргументацию.
Теперь каждый поток может предоставить свой ответ только после того, как это сделает другой поток.
Давайте предположим, что поток egg всегда отвечает первым.*?
Для решения этой задачи мы рекомендуем изучить принцип работы модели Производитель-потребитель
 */



public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 1 && args[0].startsWith("--count=")) {
            String[] buffer = args[0].split("=");
            Integer amount = Integer.parseInt(buffer[1]);
            WorkingThreads egg = new WorkingThreads("Egg", amount);
            WorkingThreads hen = new WorkingThreads("Hen", amount);
            egg.start();
            hen.start();

        } else {
            System.out.println("Программа запускается с флагом {--count=n}, где n - колличество итераций выполнения");
            System.exit(0);
        }
    }
}
