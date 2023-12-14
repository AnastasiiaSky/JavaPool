package Game;

import Game.Exceptions.IncorrectApplicationProductionData;

import java.io.*;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<String> getDataFromFile() {
        ArrayList<String> allData = new ArrayList<>();
        File file = new File(".");
        String absolut = file.getAbsolutePath();
        String sourceFile = absolut.substring(0, absolut.length() - 1)
                + "resources/application-production.properties";
//        System.out.println(absolut.substring(0, absolut.length() - 1));
        try (FileReader fileReader = new FileReader("/Users/qylenett/Desktop/Java_Bootcamp.Team00-1/src/ex00/src/main/resources/application-production.properties");
             BufferedReader reader = new BufferedReader(fileReader)) {
            String data = reader.readLine();
            while (data != null) {
                String[] result = data.split("=");
                if(result.length > 1) {
                    allData.add(result[1].trim());
                } else {
                    allData.add(" ");
                }
                data = reader.readLine();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return allData;
    }
}
