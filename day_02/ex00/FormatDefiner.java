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


//    private static void fillSignature(Map<String, String> signature, InputStream in) {
//        Scanner sc = new Scanner(in);
//        while (sc.hasNext()) {
//            StringBuilder sb = new StringBuilder();
//            String[] line = sc.nextLine().split(", ");
//            for (String s : line[1].split(" ")) {
//                sb.append(s);
//            }
//            signature.put(line[0], sb.toString());
//        }
//        sc.close();
//    }
   private Map<String, List<Byte>> parseSignatures(List<String> formats)  {
       Map<String, List<Byte>> formatPairs = new HashMap<>();
       for(String element : formats) {
           String[] data = element.split(", ");
           String[] signals = data[1].split(" ");
           List<Byte> byteList = new ArrayList<>();
           for(String part : signals) {
               char[] partSimb = part.toCharArray();
               int first = hexToByte(partSimb[0]);
               int second = hexToByte(partSimb[1]);
               if(first == -1 || second == -1) return null;
                    byteList.add((byte)((first << 4) + second));
           }
           formatPairs.put(data[0], byteList);
           if(byteList.size() > sizeC) sizeC = byteList.size();
       }
       return formatPairs;
   }

    private static int hexToByte(char hex){
       char[] hexes = {'0', '1', '2', '3', '4','5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        for(int it = 0; it < hexes.length; ++it) {
            if(hex == hexes[it]) return it + 1;
        }
        return -1;
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
