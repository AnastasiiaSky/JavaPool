import java.util.Scanner;



public class Program {

    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] studentsNames = GetStudentsNames();
        String[] dateAndTimeForWeek = GetDateAndTime();
        int[] time = GetLessonsTime(dateAndTimeForWeek);
        int[] weekDay = GetLessonsWeekDay(dateAndTimeForWeek);
        int[] firstWeekDates = GetFirstWeekDates(weekDay);
        int[][] firstWeekData = SortDataForFirstWeek(time, weekDay, firstWeekDates);
        int[][] allScadule = MakeAllScadule(firstWeekData);

//        System.out.println("Students");
//        for(int it = 0; it < studentsNames.length; ++it) {
//            System.out.print(studentsNames[it]);
//        }
        System.out.println();
        System.out.println("dateAndTimeForWeek");

        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
            System.out.print(" " + dateAndTimeForWeek[it]);
        }
        System.out.println();
        System.out.println("Time");
        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
            System.out.print(" " + time[it]);
        }
        System.out.println();
        System.out.println("weekDayNumbers");
        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
            System.out.print(" " + weekDay[it]);
        }
        System.out.println();
        System.out.println("firstWeekDates");
        for(int it = 0; it < dateAndTimeForWeek.length; ++it) {
            System.out.print(" " + firstWeekDates[it]);
        }
        System.out.println();
        System.out.println("firstWeekDatesAndDateAndTime");
        for(int it = 0; it < firstWeekData.length; ++it) {
            for(int j = 0; j < firstWeekData[it].length; ++j) {
                System.out.print(" " + firstWeekData[it][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("all scadule");
        for(int it = 0; it < allScadule.length; ++it) {
            for(int j = 0; j < allScadule[it].length; ++j) {
                System.out.print(" " + allScadule[it][j]);
            }
            System.out.println();
        }
    }
    // Делаем полное расписание
    public static int[][] MakeAllScadule(int[][] firstWeekData) {
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
    public static int[][] SortDataForFirstWeek(int[] time, int[] weekDay, int[] firstWeekDates) {
        int[][] firstWeekScadule = new int[firstWeekDates.length][3];
        int[] firstWeekDatesCopy = new int[firstWeekDates.length];
        boolean[] visitedTime = new boolean[firstWeekDates.length];
        for(int it = 0; it < firstWeekDatesCopy.length; ++it) {
            firstWeekDatesCopy[it] = firstWeekDates[it];
        }
            int[] firstWeekDatesSorted = BubbleSort(firstWeekDates);
        for(int it = 0; it < firstWeekDatesSorted.length; ++it) {
            for(int j = 0; j < firstWeekDatesCopy.length; ++j) {
                if(firstWeekDatesSorted[it] == firstWeekDatesCopy[j]) {
                    firstWeekScadule[it][0] = firstWeekDatesSorted[it];
                    firstWeekScadule[it][1] = weekDay[j];
                    int timePos = 0;
                    if(visitedTime[j] == true) {
                        timePos = ReturnNotVisitedTimeIndex(visitedTime, weekDay[j], weekDay);
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

    public static int ReturnNotVisitedTimeIndex(boolean[] visitedTime, int weekDayNumber, int[] weekDay) {
        int result = 0;
        for(int it = 0; it < weekDay.length; ++it) {
            if(weekDay[it] == weekDayNumber && visitedTime[it] == false) {
                result = it;
            }
        }
        return result;
    }

    // Sort Array
    public static int[] BubbleSort(int[] array) {
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
    public static int[] GetFirstWeekDates(int[] weekDay){
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
    public static int[] GetLessonsWeekDay(String[] dateAndTimeForWeek){
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
    public static int[] GetLessonsTime(String[] dateAndTimeForWeek) {
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
    public static String[] GetDateAndTime() {
        int it = 0;
        String[] сlassDaysTmp = new String[10];
        do {
            String tmpClassDate = myScanner.nextLine();
            if(tmpClassDate.equals(".")) break;
            char[] arrClassDate = tmpClassDate.toCharArray();
            if(CheckClassDay(arrClassDate)) {
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
    public static boolean CheckClassDay(char[] tmpString) {
        if((int)tmpString[0] < 49 || (int)tmpString[0] > 54) ExitProgramm();
        String[] monthDays = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};
        boolean check = false;
        for(int i = 0; i < monthDays.length; ++i) {
            char[] day = monthDays[i].toCharArray();
            if(day[0] == tmpString[2] && day[1] == tmpString[3]) check = true;
        }
        if(check == false) ExitProgramm();
        return true;
    }
    // Получаем имена студентов
    public static String[] GetStudentsNames() {
        int it = 0;
        String[] studentsNamesTmp = new String[10];
        do{
            if(myScanner.hasNextLine()) {
                String studentName = myScanner.nextLine();
                if(studentName.equals(".")) break;
                if(studentName.length() > 10) ExitProgramm();
                if(CheckName(studentName) == true) {
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

    public static boolean CheckName(String studentName) {
        int[] unicodeSimbols = new int[65535];
        char[] nameSimbols = studentName.toCharArray();
        for(int it = 0; it < nameSimbols.length; ++it) {
            if((int)nameSimbols[it] < 65 || (int)nameSimbols[it] > 122
                    || (int)nameSimbols[it] == 91 || (int)nameSimbols[it] == 92
                    || (int)nameSimbols[it] == 93 || (int)nameSimbols[it] == 94
                    || (int)nameSimbols[it] == 95 || (int)nameSimbols[it] == 96) ExitProgramm();
        }
        return true;
    }

    public static void ExitProgramm() {
        System.err.println("Illegal Argument");
        System.exit(-1);
    }
}
