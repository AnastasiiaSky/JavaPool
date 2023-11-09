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
    private List<String> formats;
    Map<List<String>, String> formatPairs;


    public FormatDefiner() {
        this.formats = getFormatsFromFile("./ex00/signal.txt");
        if (this.formats == null) throw new RuntimeException("Некорректные данные в файле signal.txt");
        this.formatPairs = parseSignatures(formats);

    }

    private List<String> getFormatsFromFile(String path) {
        List<String> fileData = new ArrayList<String>();
        try {
            InputStream fin = new FileInputStream(path);
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
            try {
                InputStream inputFile = new FileInputStream(path);
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    str.append(String.format("%02X", inputFile.read()));
                    if (i != 2) str.append(",");
                }
                String[] firstBytes = str.toString().split(",");
                String answer = getFormatFromMap(firstBytes);
                if (answer == null) {
                    System.out.println("UNDEFINED");
                    results = new ArrayList<>();
                } else {
                    System.out.println("PROCESSED");
                    results.add(answer);
                }
                writeToFile(results);
            } catch (IOException e) {
                System.out.println(e.getMessage() + ": Файл не найден!");
            }

        }
    }

    private String getFormatFromMap(String[] firstBytes) {
        for (Map.Entry<List<String>, String> element : formatPairs.entrySet()) {
            List<String> bytes = element.getKey();
            for (int i = 0; i < bytes.size(); ++i) {
                if (bytes.get(i).equals(firstBytes[i]) && bytes.get(i + 1).equals(firstBytes[i + 1])
                        && bytes.get(i + 2).equals(firstBytes[i + 2])) {
                    return element.getValue();
                }
            }
        }
        return null;
    }

    private void writeToFile(List<String> results) {
        try {
            OutputStream fos = new FileOutputStream("ex00/result.txt", true);
            for (int i = 0; i < results.size(); ++i) {
                fos.write(results.get(i).getBytes());
                fos.write("\n".getBytes());
            }
            fos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
