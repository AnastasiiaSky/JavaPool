import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        long resultData = 0L;
        String currentString;
        int lastWeekNumber = 0;
        int currentWeekNumber = -1;
        int counter = 0;
        int mark = 0;

        do {
            currentString = myScanner.nextLine();
            ++counter;
            if(currentString.startsWith("42") == false) {
                currentWeekNumber = ParseWeek(currentString);
                if (WeekValidation(lastWeekNumber, currentWeekNumber) == true) {
                        mark = GetMinMark();
                        resultData = resultData + mark;
                        resultData *= 10;
                        lastWeekNumber = currentWeekNumber;
                    }
                }
        } while (counter < 18 && (currentString.startsWith("42") == false));
        resultData /= 10;
        long correctResultData = ReverseNumberData(resultData);
        GiveAnswer(correctResultData, counter);
    }

    public static long ReverseNumberData(long number) {
        long resultNumber = 0L;
        while(number > 0) {
            resultNumber += number % 10;
            resultNumber *= 10;
            number /= 10;
        }
        resultNumber /= 10;
        return resultNumber;
    }

    public static int GetMinMark() {
        Scanner myScanner = new Scanner(System.in);
        int counter = 0;
        int minMark = 10;
        int currentMark;
        do {
            ++counter;
            currentMark = myScanner.nextInt();
            if (currentMark > 0 && currentMark <= 9) {
                if (minMark > currentMark) {
                    minMark = currentMark;
                }
            } else {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
        } while (counter < 5);
        return minMark;
    }

    public static int ParseWeek(String week) {
        int result = -1;

        if(week.startsWith("Week ") == true && (week.endsWith("0") == true || week.endsWith("1") == true
        || week.endsWith("2") == true || week.endsWith("3") == true || week.endsWith("4") == true
        || week.endsWith("5") == true || week.endsWith("6") == true || week.endsWith("7") == true
        || week.endsWith("8") == true || week.endsWith("9") == true)) {
            String weekNumber = week.substring(5);
            result = Integer.parseInt(weekNumber);
        } else {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        return result;
    }

    public static boolean WeekValidation(int lastWeek, int currentWeek) {
        if(currentWeek <= lastWeek) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        if(currentWeek - lastWeek > 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        if(currentWeek > 18 || currentWeek < 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        return true;
    }

    public static void GiveAnswer(long resultData, int CountOfWeeks) {
        long markToPrint = 0;
        for(long it = 1; it <= CountOfWeeks; ++it) {
                markToPrint = resultData % 10;
                resultData /= 10;
                PrintAnswer(it, markToPrint);
                markToPrint = 0;
            }
        }

    public static void PrintAnswer(long weekNumber, long mark) {
        if(mark == 1) {
            System.out.println("Week " + weekNumber + " =>");
        } else if(mark == 2) {
            System.out.println("Week " + weekNumber + " ==>");
        } else if(mark == 3) {
            System.out.println("Week " + weekNumber + " ===>");
        } else if(mark == 4) {
            System.out.println("Week " + weekNumber + " ====>");
        } else if(mark == 5) {
            System.out.println("Week " + weekNumber + " =====>");
        } else if(mark == 6) {
            System.out.println("Week " + weekNumber + " ======>");
        } else if(mark == 7) {
            System.out.println("Week " + weekNumber + " =======>");
        } else if(mark == 8) {
            System.out.println("Week " + weekNumber + " ========>");
        } else if(mark == 9) {
            System.out.println("Week " + weekNumber + " =========>");
        }
    }
}
