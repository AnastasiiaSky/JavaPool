import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int numberForCheck = myScanner.nextInt();
        int counter = 1;
        boolean isSimple = true;

        if (validationCheck(numberForCheck) == true) {
            for (int it = 2; it * it <= numberForCheck; it++) {
                if (numberForCheck % it == 0) {
                    isSimple = false;
                    break;
                } else {
                    isSimple = true;
                }
                ++counter;
            }
            System.out.println(isSimple + " " + counter);
        } else {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        myScanner.close();
    }

    public static boolean validationCheck(int number) {
        if (number > 1) {
            return true;
        }
        return false;
    }
}

