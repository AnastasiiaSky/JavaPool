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
   private List<String> formats;
   Map<String, List<Byte>> formatPairs;


   public FormatDefiner() {
       this.formats = getFormatsFromFile("./ex00/signal.txt");
       if(formats == null) throw new RuntimeException("Invalid file of signatures");
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

   private Map<String, List<Byte>> parseSignatures(List<String> formats) {

   }

//    private List<String> getFormatsFromFile(String path) {
//        List<String> fileData = new ArrayList<String>();
//        File fin = new File(path);
//        try {
//            Scanner sc = new Scanner(fin);
//            while (sc.hasNextLine()) {
//                String curLine = sc.nextLine();
//                fileData.add(curLine);
//            }
//            sc.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fileData;
//    }

    public void execute() {
        System.out.println("formats");
        for(String s : formats) {
            System.out.println(s);
        }
    }

//    public void execute() {
//
//       Scanner fsc = new Scanner(System.in);
//        System.out.println("Введите путь к проверяемому файлу");
//        System.out.print("->");
//    }
}
