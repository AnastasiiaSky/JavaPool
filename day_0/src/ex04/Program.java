import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String inputString = myScanner.nextLine();
        int[] unicodeSimbols = new int[65535];
        char[] inputStringSimbols = inputString.toCharArray();
        for (int it = 0; it < inputString.length(); ++it) {
            unicodeSimbols[(int) inputStringSimbols[it]] += 1;
        }

        int[] frequentSimbols = new int[10];
        char[] serchingSimbols = new char[10];

        int maxCount = 0, index = 0;
        char maxChar = 0;

        for (int it = 0; it < frequentSimbols.length; ++it) {
            for (int j = 0; j < unicodeSimbols.length; ++j) {
                if (unicodeSimbols[j] > maxCount) {
                    maxCount = unicodeSimbols[j];
                    maxChar = (char) j;
                    index = j;
                }
            }

            frequentSimbols[it] = unicodeSimbols[index];
            serchingSimbols[it] = maxChar;

            unicodeSimbols[index] = 0;
            maxCount = 0;
            maxChar = ' ';
        }

        if (resultValidation(frequentSimbols) == true) {
            printResultData(serchingSimbols, frequentSimbols);
        }
        myScanner.close();
    }

    public static void printResultData(char[] simbols, int[] frequence) {
        System.out.println();

        int maxNum = frequence[0];
        for (int i = 0; i < simbols.length; ++i) {
            if (frequence[i] == maxNum) {
                System.out.printf("  %d  %n", frequence[i]);
            }
        }

        for (int i = frequence.length; i > 0; --i) {
            for (int j = 0; j < frequence.length; ++j) {
                if (frequence[j] * 10 / maxNum >= i) {
                    System.out.printf("   %c", '#');
                } else if (frequence[j] * 10 / maxNum == i - 1 && frequence[j] != 0) {
                    if (frequence[j] > 9) {
                        System.out.printf("  %d", frequence[j]);
                    } else {
                        System.out.printf("   %d", frequence[j]);
                    }
                }
            }
            System.out.println();
        }

        for (int i = 0; i < frequence.length; ++i) {
            System.out.printf("   %c", simbols[i]);
        }
        System.out.println();
    }

    public static boolean resultValidation(int[] frequentSimbols) {
        if (frequentSimbols[0] > 999) {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }
        return true;
    }
}








