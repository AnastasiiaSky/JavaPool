package ex00;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class FormatDefiner {
    int sizeC = -1;
    int signalLength;
    private List<String> formats;
    Map<List<String>, String> formatPairs;


    public FormatDefiner() {
        this.formats = getFormatsFromFile("./ex00/signal.txt");
        if (this.formats == null) throw new RuntimeException("Некорректные данные в файле signal.txt");
        this.formatPairs = parseSignatures(formats);

    }

    private List<String> getFormatsFromFile(String path) {

        List<String> fileData = new ArrayList<String>();
        try (InputStream fin = new FileInputStream(path)) {
            StringBuilder inputData = new StringBuilder();
            while (fin.available() > 0) {
                inputData.append((char) fin.read());
            }

            StringBuilder curLine = new StringBuilder();
            for (int it = 0; it < inputData.length(); ++it) {
                if (inputData.charAt(it) == '\n') {
                    fileData.add(curLine.toString());
                    curLine = new StringBuilder();
                } else curLine.append(inputData.charAt(it));
            }
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }

    private Map<List<String>, String> parseSignatures(List<String> formats) {
        Map<List<String>, String> formatPairs = new HashMap<>();
        for (String element : formats) {
            String[] data = element.split(", ");
            String[] signals = data[1].split(" ");
            List<String> byteList = new ArrayList<>();
            for (String part : signals) {
                byteList.add(part);
            }
            if(this.sizeC < byteList.size()) this.sizeC = byteList.size();
            formatPairs.put(byteList, data[0]);
        }
        return formatPairs;
    }


    public void execute() {
        Scanner sc = new Scanner(System.in);
        List<String> results = new ArrayList<>();
        while (true) {
            System.out.println("Введите путь к файлу или введите 42 для выхода");
            System.out.println("->");
            String path = sc.nextLine().trim();
            if (path.equals("42")) System.exit(0);
            try (InputStream inputFile = new FileInputStream(path)){
                String result = "";

                List<String> checkingBytes = new ArrayList<>();
                int count = this.sizeC;
                while(inputFile.available() > 0 && count >= 0) {
                    --count;
                    checkingBytes.add(String.format("%02X", inputFile.read()));
                }
                result = getFormatFromMap(checkingBytes);
                if (result == null) {
                    System.out.println("UNDEFINED");
                } else {
                    System.out.println("PROCESSED");
                }
                writeToFile(result);
            } catch (IOException e) {
                System.out.println(e.getMessage() + ": Файл не найден!");
            }

        }
    }

    private String getFormatFromMap(List<String> checkingBytes) {
        String result = "";
        for(Map.Entry<List<String>, String> entry : formatPairs.entrySet()) {
            List<String> signature = entry.getKey();
            String name = entry.getValue();
            for(int it = 0; it < signature.size(); ++it) {
                if(!checkingBytes.get(it).equals(signature.get(it))) break;
                if(it + 1 == signature.size()) {
                    result = name;
                    break;
                }
            }
        }
        return result;
    }

    private void writeToFile(String results) {
        try (OutputStream fos = new FileOutputStream("ex00/result.txt", true)) {
                fos.write(results.getBytes());
                fos.write("\n".getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
