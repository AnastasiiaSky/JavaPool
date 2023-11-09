import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int checkingNumber;
        int coffeRequestCounter = 0;
        do {
            checkingNumber = myScanner.nextInt();
            if (requestValidation(checkingNumber) == true) {
                if (checkCoffeRequest(checkingNumber) == true) {
                    ++coffeRequestCounter;
                }
            } else {
                System.err.println("Illegal Argument, try again");
            }
        } while (checkingNumber != 42);

        System.out.println("Count of coffee-request - " + coffeRequestCounter);
        myScanner.close();
    }

    public static boolean checkCoffeRequest(int number) {
        int newNumber = getSumFromNumberParts(number);
        if (isItSimple(newNumber) == true) {
            return true;
        }
        return false;
    }

    public static int getSumFromNumberParts(int number) {
        int resultNumber = 0;
        while (number != 0) {
            resultNumber = resultNumber + (number % 10);
            number = number / 10;
        }
        return resultNumber;
    }

    public static boolean isItSimple(int number) {
        for (int it = 2; it * it <= number; it++) {
            if (number % it == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean requestValidation(int number) {
        if (number > 1) {
            return true;
        }
        return false;
    }

}