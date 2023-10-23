import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        long resultData = 0L;
        String currentString = "inptstring";
        long lastWeekNumber = 0L;
        long currentWeekNumber = -1L;
        int counter = 0;
        long mark = 0;

        while (counter < 18) {
            ++counter;
            if (myScanner.hasNextInt() && myScanner.nextInt() == 42) {
                break;
            } else if (myScanner.hasNext("Week")) {
                myScanner.next();
                if (myScanner.hasNextInt()) {
                    currentWeekNumber = myScanner.nextInt();
                    if (WeekValidation(lastWeekNumber, currentWeekNumber) == true) {
                        lastWeekNumber = currentWeekNumber;
                        mark = GetMinMark();

                        currentWeekNumber = NumberPower(currentWeekNumber);
                        resultData+= (long) (currentWeekNumber * mark);
                    }
                }
            } else {
                ExitProgramm();
            }
        }
        System.out.println(resultData);
        PrintResultData(resultData);
        myScanner.close();
    }


    public static void PrintResultData(long result_number) {
        int counter = 0;
        while(result_number > 0) {
            ++counter;
            long what = result_number % 10;
            if(what == 0) {
                result_number /= 10;
                continue;
            }
            System.out.print("Week " + counter + " ");
            PrintEqual(what);
            System.out.println(">");
            result_number /= 10;
        }
    }

    public static void PrintEqual(long countOfEquals) {
        while (countOfEquals > 0) {
            System.out.print("=");
            --countOfEquals;
        }
    }

    public static long NumberPower(long number) {
        long i = 1L;
        number -= 1;
        while (number > 0) {
            i *= 10;
            --number;
        }
        return i;
    }
    public static boolean WeekValidation(long lastWeek, long currentWeek) {
        if (currentWeek <= lastWeek) ExitProgramm();
        if (currentWeek > 18 || currentWeek < 1) ExitProgramm();
        return true;
    }

    public static int GetMinMark() {
        Scanner myScanner = new Scanner(System.in);
        int counter = 0;
        int minMark = 10;
        int currentMark;
        while (counter < 5) {
            if(myScanner.hasNextInt() == false) ExitProgramm();
                currentMark = myScanner.nextInt();
                if(currentMark < 1 && currentMark > 19) ExitProgramm();
                if (minMark > currentMark) {
                    minMark = currentMark;
                }
            ++counter;
        }
        return minMark;
    }

    public static void ExitProgramm() {
        System.err.println("Illegal Argument");
        System.exit(-1);
    }
}


