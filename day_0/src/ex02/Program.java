import java.util.Scanner;

public class Program{
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int checkingNumber;
        int coffeRequestCounter = 0;
        do {
            checkingNumber = myScanner.nextInt();
                if(RequestValidation(checkingNumber) == true) {
                    if (CheckCoffeRequest(checkingNumber) == true) {
                        ++coffeRequestCounter;
                    }
                } else {
                    System.err.println("Illegal Argument, try again");
                }
        } while (checkingNumber != 42);

        System.out.println("Count of coffee-request - " + coffeRequestCounter);

    }

    public static boolean CheckCoffeRequest(int number) {
        int newNumber = GetSumFromNumberParts(number);


        if (IsItSimple(newNumber) == true) {
            return true;
        }
        return false;
    }

    public static int GetSumFromNumberParts(int number) {
        int resultNumber = 0;
        int counter = 0;
        int numberBuffer = number;
        while(numberBuffer != 0) {
            numberBuffer = numberBuffer / 10;
            ++counter;
        }
        while (counter > 0) {
            resultNumber = resultNumber + (number % 10);
            number = number / 10;
            --counter;
        }
        return resultNumber;
    }

    public static boolean IsItSimple(int number) {
        for(int it = 2; it * it <= number; it++) {
            if (number % it == 0) {
              return false;
            }
        }
    return true;
    }

    public static boolean RequestValidation(int number) {
        if(number > 1) {
            return true;
        }
        return false;
    }

}