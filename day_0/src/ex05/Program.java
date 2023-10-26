import java.util.Scanner;



public class Program {

    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] studentsNames = getStudentsNames();
        String[] dateAndTimeForWeek = getDateAndTime();
        int[] time = getLessonsTime(dateAndTimeForWeek);
        int[] weekDay = getLessonsWeekDay(dateAndTimeForWeek);
        int[] firstWeekDates = getFirstWeekDates(weekDay);
        int[][] firstWeekData = sortDataForFirstWeek(time, weekDay, firstWeekDates);
        int[][] allScadule = makeAllScadule(firstWeekData);
        String[] allScaduleForPrint = makeAllScaduleForPrint(allScadule);

//        System.out.println("Students");
//        for(int it = 0; it < studentsNames.length; ++it) {
//            System.out.print(studentsNames[it]);
//        }
//        System.out.println();
//        System.out.println("dateAndTimeForWeek");
//
//        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
//            System.out.print(" " + dateAndTimeForWeek[it]);
//        }
//        System.out.println();
//        System.out.println("Time");
//        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
//            System.out.print(" " + time[it]);
//        }
//        System.out.println();
//        System.out.println("weekDayNumbers");
//        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
//            System.out.print(" " + weekDay[it]);
//        }
//        System.out.println();
//        System.out.println("firstWeekDates");
//        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
//            System.out.print(" " + firstWeekDates[it]);
//        }
//        System.out.println();
//        System.out.println("firstWeekDatesAndDateAndTime");
//        for(int it = 0; it < firstWeekData.length; ++it) {
//            for(int j = 0; j < firstWeekData[it].length; ++j) {
//                System.out.print(" " + firstWeekData[it][j]);
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//        System.out.println("all scadule");
//        for(int it = 0; it < allScadule.length; ++it) {
//            for(int j = 0; j < allScadule[it].length; ++j) {
//                System.out.print(" " + allScadule[it][j]);
//            }
//            System.out.println();
//        }
                System.out.println();
        System.out.println("AllScadule");
        for(int it = 0; it < allScaduleForPrint.length; ++it) {
            System.out.print(allScaduleForPrint[it]);
        }
    }
    // Делаем массив строк расписания для печати
//    int[][] allScadule = MakeAllScadule(firstWeekData);
    public static String[] makeAllScaduleForPrint(int[][] allScadule) {
        String[] result = new String[allScadule.length];
        for(int it = 0; it < result.length; ++it) {
            String s1 = getTimeForPrint(allScadule, it);
            String s2 = getWeekDayForPrint(allScadule, it);
            String s3 = getDate(allScadule, it);
            result[it] = s1 + " " + s2 + "  " + s3 + "|";
        }
        return result;
    }

    public static String getDate(int[][] allScadule, int position) {
        String[] Days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
                "22", "23", "24", "25", "26", "27", "28", "29", "30"};;
        String day = Days[allScadule[position][0] - 1];
        return day;
    }
    public static String getWeekDayForPrint(int[][] allScadule, int position) {
        String[] weekDays = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};;
        String wDay = weekDays[allScadule[position][1]];
        return wDay;
    }
    public static String getTimeForPrint(int[][] allScadule, int position) {
        String[] lessonTime = {"1:00", "2:00", "3:00", "4:00", "5:00", "6:00"};
        String time = lessonTime[allScadule[position][2]];
        return time;
    }
    // Делаем полное расписание
    public static int[][] makeAllScadule(int[][] firstWeekData) {
        int[][] allScadule = new int[firstWeekData.length * 4][firstWeekData[0].length];
        for(int it = 0; it < firstWeekData.length; ++it) {
            for(int j = 0; j < firstWeekData[0].length; ++j) {
                allScadule[it][j] = firstWeekData[it][j];
            }
        }
        for(int it = firstWeekData.length; it < allScadule.length; ++it) {
                allScadule[it][0] = allScadule[it - firstWeekData.length][0] + 7;
                allScadule[it][1] = allScadule[it - firstWeekData.length][1];
                allScadule[it][2] = allScadule[it - firstWeekData.length][2];
        }
        return  allScadule;
    }

    // Сортируем данные для первой недели расписания
    public static int[][] sortDataForFirstWeek(int[] time, int[] weekDay, int[] firstWeekDates) {
        int[][] firstWeekScadule = new int[firstWeekDates.length][3];
        int[] firstWeekDatesCopy = new int[firstWeekDates.length];
        boolean[] visitedTime = new boolean[firstWeekDates.length];
        for(int it = 0; it < firstWeekDatesCopy.length; ++it) {
            firstWeekDatesCopy[it] = firstWeekDates[it];
        }
            int[] firstWeekDatesSorted = bubbleSort(firstWeekDates);
        for(int it = 0; it < firstWeekDatesSorted.length; ++it) {
            for(int j = 0; j < firstWeekDatesCopy.length; ++j) {
                if(firstWeekDatesSorted[it] == firstWeekDatesCopy[j]) {
                    firstWeekScadule[it][0] = firstWeekDatesSorted[it];
                    firstWeekScadule[it][1] = weekDay[j];
                    int timePos = 0;
                    if(visitedTime[j] == true) {
                        timePos = returnNotVisitedTimeIndex(visitedTime, weekDay[j], weekDay);
                        visitedTime[timePos] = true;
                        firstWeekScadule[it][2] = time[timePos];
                    } else {
                        firstWeekScadule[it][2] = time[j];
                        visitedTime[j] = true;
                    }
                }
            }
        }
        return firstWeekScadule;
    }

    public static int returnNotVisitedTimeIndex(boolean[] visitedTime, int weekDayNumber, int[] weekDay) {
        int result = 0;
        for(int it = 0; it < weekDay.length; ++it) {
            if(weekDay[it] == weekDayNumber && visitedTime[it] == false) {
                result = it;
            }
        }
        return result;
    }

    // Sort Array
    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            for(int j = 0; j < array.length - i - 1; ++j) {
                if(array[j + 1] < array[j]) {
                    int swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;
                }
            }
        }
        return array;
    }

    // Получаем даты для первой недели
    public static int[] getFirstWeekDates(int[] weekDay){
        int[] firstWeekDates = new int[weekDay.length];
        for(int it = 0; it < weekDay.length; ++it) {
                if(weekDay[it] == 0) {
                    firstWeekDates[it] = 7;
                } else {
                    firstWeekDates[it] = weekDay[it];
                }
        }
        return firstWeekDates;
    }

    // Получаем массив с номерами дней недели
    public static int[] getLessonsWeekDay(String[] dateAndTimeForWeek){
        String[] weekDays = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        int[] weekDaysData = new int[dateAndTimeForWeek.length];
        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
            char[] curData = dateAndTimeForWeek[it].toCharArray();
            for(int j = 0; j < weekDays.length; ++j) {
                char[] day = weekDays[j].toCharArray();
                if(day[0] == curData[2] && day[1] == curData[3]) weekDaysData[it] = j;
            }
        }
        return weekDaysData;
    }

    // Получаем массив интов со временем занятий
    public static int[] getLessonsTime(String[] dateAndTimeForWeek) {
        String[] lessonTime = {"1:00", "2:00", "3:00", "4:00", "5:00", "6:00"};
        int[] timeTable = new int[dateAndTimeForWeek.length];
        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
            char[] curData = dateAndTimeForWeek[it].toCharArray();
            for(int j = 0; j < lessonTime.length; ++j) {
                char[] time = lessonTime[j].toCharArray();
                if (time[0] == curData[0]) timeTable[it] = j;
            }
        }
        return timeTable;
    }
    // Получаем даты
    public static String[] getDateAndTime() {
        int it = 0;
        String[] сlassDaysTmp = new String[10];
        do {
            String tmpClassDate = myScanner.nextLine();
            if(tmpClassDate.equals(".")) break;
            char[] arrClassDate = tmpClassDate.toCharArray();
            if(checkClassDay(arrClassDate)) {
                сlassDaysTmp[it] = tmpClassDate;
                ++it;
            }
        }   while (it < 10);
        String[] сlassDays = new String[it];

        for(int i = 0; i < it; ++i) {
            сlassDays[i] = сlassDaysTmp[i];
        }
        return сlassDays;
    }
    public static boolean checkClassDay(char[] tmpString) {
        if((int)tmpString[0] < 49 || (int)tmpString[0] > 54) exitProgramm();
        String[] monthDays = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};
        boolean check = false;
        for(int i = 0; i < monthDays.length; ++i) {
            char[] day = monthDays[i].toCharArray();
            if(day[0] == tmpString[2] && day[1] == tmpString[3]) check = true;
        }
        if(check == false) exitProgramm();
        return true;
    }
    // Получаем имена студентов
    public static String[] getStudentsNames() {
        int it = 0;
        String[] studentsNamesTmp = new String[10];
        do{
            if(myScanner.hasNextLine()) {
                String studentName = myScanner.nextLine();
                if(studentName.equals(".")) break;
                if(studentName.length() > 10) exitProgramm();
                if(checkName(studentName) == true) {
                    studentsNamesTmp[it] = studentName;
                    ++it;
                }
            }
        } while (it < 10);

        String[] studentsNames = new String[it];
        for(int i = 0; i < it; ++i) {
            studentsNames[i] = studentsNamesTmp[i];
        }
        return studentsNames;
    }

    public static boolean checkName(String studentName) {
        int[] unicodeSimbols = new int[65535];
        char[] nameSimbols = studentName.toCharArray();
        for(int it = 0; it < nameSimbols.length; ++it) {
            if((int)nameSimbols[it] < 65 || (int)nameSimbols[it] > 122
                    || (int)nameSimbols[it] == 91 || (int)nameSimbols[it] == 92
                    || (int)nameSimbols[it] == 93 || (int)nameSimbols[it] == 94
                    || (int)nameSimbols[it] == 95 || (int)nameSimbols[it] == 96) exitProgramm();
        }
        return true;
    }

    public static void exitProgramm() {
        System.err.println("Illegal Argument");
        System.exit(-1);
    }
}
