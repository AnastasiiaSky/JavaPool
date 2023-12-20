package Game.src.main.java;

import java.io.*;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<String> getDataFromFile() {
        ArrayList<String> allData = new ArrayList<>();
        File file = new File(".");
        String absolut = file.getAbsolutePath();
        String sourceFile = absolut.substring(0, absolut.length() - 1)
                + "src/main/resources/application-production.properties";
        try (FileReader fileReader = new FileReader(sourceFile);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String data = reader.readLine();
            while (data != null) {
                String[] result = data.split("=");
                if (result.length > 1) {
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
