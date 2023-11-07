package ex00;

import java.io.File;
import java.io.FileInputStream; // поток ввода для чтения из файла
import java.io.FileOutputStream; // поток вывода для записи в файл
// FileReader FileWrider - классы для символьного чтения записи, предыдущие два для байтовых данных
import java.io.IOException; // для FileNotFoundException
import java.io.InputStream; // для потоков ввода байтовых данных абстрактный класс кот описывает поток ввода
import java.io.OutputStream; // для потоков выводв байтовых данных абстрактный класс кот описывает поток вывода
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class FormatDefiner {
    int sizeC = -1;
   private List<String> formats;
   Map<String, List<String>> formatPairs;


   public FormatDefiner() {
       this.formats = getFormatsFromFile("./ex00/signal.txt");
       if(this.formats == null) throw new RuntimeException("Некорректные данные в файле signal.txt");
       this.formatPairs = parseSignatures(formats);

   }

   private List<String> getFormatsFromFile(String path) {
       List<String> fileData = new ArrayList<String>();
       try {
           InputStream fin = new FileInputStream(path);
           StringBuilder inputData = new StringBuilder();
           while(fin.available() > 0) {
               inputData.append((char)fin.read());
           }
           StringBuilder curLine = new StringBuilder();
           for(int it = 0; it < inputData.length(); ++it) {
               if(inputData.charAt(it) == '\n') {
                   fileData.add(curLine.toString());
                   curLine = new StringBuilder();
               } else curLine.append(inputData.charAt(it));
           }
           fin.close();
       } catch(IOException e) {
           e.printStackTrace();
       }
       return fileData;
   }

   private Map<String, List<String>> parseSignatures(List<String> formats)  {
       Map<String, List<String>> formatPairs = new HashMap<>();
       for(String element : formats) {
           String[] data = element.split(", ");
           String[] signals = data[1].split(" ");
           List<String> byteList = new ArrayList<>();
           for(String part : signals) {
               byteList.add(part);
           }
           formatPairs.put(data[0], byteList);

               System.out.println(formatPairs.toString());
       }
       return formatPairs;
   }

//
//               for (Map.Entry<String, String> e : signature.entrySet()) {
//        if (e.getValue().equals(sb.substring(0, e.getValue().length()))) {
//            res.add(e.getKey());
//            System.out.println("PROCESSED");
//            break;
//        }
//    }
//
//            if (size == res.size()) {
//        res.clear();
//        break;
//    }
//
//    line = sc.nextLine();
//            in.close();
//}
//        sc.close();
//                return res;
//                }
    public void execute() {
       Scanner sc = new Scanner(System.in);
        List<String> results = new LinkedList<>();
        while(true) {
            System.out.println("Введите путь к файлу или введите 42 для выхода");
            System.out.println("->");
            String path = sc.nextLine();
            if(path.equals("42")) System.exit(0);
            try {
                InputStream inputFile = new FileInputStream(path));
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    sb.append(String.format("%02X", in.read()));
                }


            } catch (IOException e){
                System.out.println(e.getMessage());
            }

        }
//        for(String s : formats) {
//            System.out.println(s);
//        }
    }

//    public void execute() {
//
//       Scanner fsc = new Scanner(System.in);
//        System.out.println("Введите путь к проверяемому файлу");
//        System.out.print("->");
//    }
}
