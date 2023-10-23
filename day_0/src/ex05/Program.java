import java.util.Scanner;


public class Program {
    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] monthDays = {"TU", "WE", "TH", "FR", "SA", "SU", "MO", "TU", "WE", "TH", "FR", "SA", "SU", "MO", "TU",
                "WE", "TH", "FR", "SA", "SU", "MO", "TU", "WE", "TH", "FR", "SA", "SU", "MO", "TU", "WE"};
        String[] studentsNames = GetStudentsNames();
        String[] dateAndTimeForWeek = GetDateAndTime();
        dateAndTimeForWeek = CutDateAndTime(dateAndTimeForWeek);
        dateAndTimeForWeek = CreateResultDateTimeString(dateAndTimeForWeek, monthDays);
        String[] visitedOrNot = GetVisetedOrNot(studentsNames, dateAndTimeForWeek);

        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
            System.out.println(dateAndTimeForWeek[it]);
        }

    }

    public static String[] GetVisetedOrNot(String[] studentsNames, String[] dateAndTimeForWeek) {


    }

    public static String[] CreateResultDateTimeString(String[] dateAndTime, String[] monthDays) {
        String resultTime[] = new String[dateAndTime.length * 4];
        for(int i = 0; i < dateAndTime.length * 4; i += dateAndTime.length) {
            for (int it = 0; it < dateAndTime.length; ++it) {
                String bufferString;
                String num;
                bufferString = dateAndTime[it].substring(0, 1);
                bufferString = bufferString.concat(":00 ");
                bufferString = bufferString.concat(dateAndTime[it].substring(2));
                resultTime[it+i] = bufferString;
                bufferString = "";
            }
        }
        resultTime = AddMonthDay(resultTime, monthDays);
        return resultTime;
    }

    public static String[] AddMonthDay(String[] dateAndTime, String[] monthDays) {
        int currentDay = 1;
        byte firstDay = 0;
        String[] result = new String[dateAndTime.length];
        String tu = "TU";
        String we = "WE";
        String th = "TH";
        String fr = "FR";
        String sa = "SA";
        String su = "SU";
        String mo = "MO";



        if(dateAndTime[0].endsWith(tu)) {
            firstDay = 1;
        }
        if(dateAndTime[0].endsWith(we)) {
            firstDay = 2;
        }
        if(dateAndTime[0].endsWith(th)) {
            firstDay = 3;
        }
        if(dateAndTime[0].endsWith(fr)) {
            firstDay = 4;
        }
        if(dateAndTime[0].endsWith(sa)) {
            firstDay = 5;
        }
        if(dateAndTime[0].endsWith(su)) {
            firstDay = 6;
        }
        if(dateAndTime[0].endsWith(mo)) {
            firstDay = 7;
        }

        currentDay = firstDay;


        for(int it = 0; it < dateAndTime.length; ++it) {
            String bufferString = dateAndTime[it];
            if(it == 0) {
                bufferString = bufferString.concat("  ");
                bufferString = bufferString.concat(Integer.toString(currentDay));
            } else {
                if(checkLastDate(dateAndTime[it - 1], dateAndTime[it])) {
                    bufferString = bufferString.concat("  ");
                    bufferString = bufferString.concat(Integer.toString(currentDay));
                } else {
                    for(int j = currentDay - 1; j < monthDays.length; ++j) {
                        if(monthDays[j].equals(dateAndTime[it].substring(5))) {
                            currentDay = j + 1;
                            break;
                        }
                    }
                    bufferString = bufferString.concat("  ");
                    bufferString = bufferString.concat(Integer.toString(currentDay));
                }
            }

            result[it] = bufferString;
            bufferString = "";
        }
        return result;
    }



    public static String[] CutDateAndTime(String[] dateAndTime) {
        int numberOfDates = 0;
        while(dateAndTime[numberOfDates] != null) {
           ++numberOfDates;
        }
        String[] correctDateAndTime = new String[numberOfDates];
        for(int it = 0; it < numberOfDates; ++it) {
            correctDateAndTime[it] = dateAndTime[it];
        }
        return correctDateAndTime;
    }

    public static String[] GetDateAndTime() {
        int dateCounter = 0;
        String[] dateAndTime = new String[14];
//        int dayLessons = 1, weekLessons = 10;
        int[] lessons = new int[7];
        int it = 0;
        String lastDay = "7 KU";
        String stopper;
        do {
            String currentData = myScanner.nextLine();
            stopper = currentData;
            if(currentData.startsWith(".") == false) {
                if (DateAndTimeValidation(currentData)) {
                    if(checkLastDate(lastDay, currentData)) {
                        lessons[it] += 1;
                        if(lessons[it] > 1) {
                            System.err.println("Illegal Argument!");
                            System.exit(-1);
                        }
                    } else {
                        ++it;
                    }
                    dateAndTime[dateCounter] = currentData;
                    lastDay = currentData;
                    ++dateCounter;
                }
            }
        } while (CheckWeekLessonsNumber(lessons) != false && stopper.startsWith(".") == false);

        return dateAndTime;
    }

    public static boolean CheckWeekLessonsNumber(int[] lessons) {
        int lessonsCounter = 0;
        for(int it = 0; it < lessons.length; ++it) {
            lessonsCounter += lessons[it];
        }
        if(lessonsCounter == 10) {
            return false;
        }
        return true;
    }

    public static boolean checkLastDate(String lastDate, String currentDate) {
        String lastWeekDay = lastDate.substring(2);
        String curWeekDay = currentDate.substring(2);

        if(lastWeekDay.equals(curWeekDay)) {
            return true;
        }
        return false;
    }

    public static boolean DateAndTimeValidation(String currentData) {
        if(currentData.length() > 4) {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }

        if((currentData.startsWith("1 ") == true || currentData.startsWith("2 ") == true
                || currentData.startsWith("3 ") == true || currentData.startsWith("4 ") == true
                || currentData.startsWith("5 ") == true || currentData.startsWith("6 ") == true)
                && (currentData.endsWith("MO") == true || currentData.endsWith("TU") == true
                || currentData.endsWith("WE") == true || currentData.endsWith("TH") == true
                || currentData.endsWith("FR") == true || currentData.endsWith("SA") == true
                || currentData.endsWith("SU") == true)) {
            return true;
        } else {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }
        return true;
    }

    public static String[] GetStudentsNames() {
        int namesCounter = 0;
        String[] studentNames = new String[10];
        String stopper;

        do {
            String currentName = myScanner.nextLine();
            stopper = currentName;
            if(NameValidation(currentName) == true && currentName.startsWith(".") == false) {
                studentNames[namesCounter] = currentName;
                ++namesCounter;
            }
        } while (namesCounter < 10 && stopper.startsWith(".") == false);

        return studentNames;
    }

    public static boolean NameValidation(String currentName) {
        if(currentName.length() > 10) {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }
        return true;
    }
}