package ex01;

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
class FileChecking {
    private String file1;
    private String file2;
    private String wordbook;
    private List<String> dictionary;
    private Vector<Integer> vector1;
    private Vector<Integer> vector2;
    private double simularity;


    public  FileChecking(String file1, String file2) {
        this.file1 = file1;
        this.file2 = file2;
        this.wordbook = "ex01/dictionary.txt";
        this.dictionary = createWordBook();
        if(dictionary == null) System.out.println("Словарь не создан");
        this.vector1 = createVector(file1);
        this.vector2 = createVector(file2);
        this.simularity = getSimularityResult();
    }

    public double getSimularity() {
        return simularity;
    }

    private List<String> createWordBook() {
        try {
            String filename = "ex01/dictionary.txt";
            OutputStream fos = new FileOutputStream(filename, false);
            List<String> words = getWordsForWordBook();
            for (int i = 0; i < words.size(); ++i) {
                fos.write(words.get(i).getBytes());
                if(i != words.size() - 1) fos.write(", ".getBytes());
            }
            fos.close();
            return words;
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private List<String> getWordsForWordBook() {
        List<String> file1words = getWords(this.file1);
        List<String> file2words = getWords(this.file2);
        List<String> words = createAllWordsData(file1words, file2words);
        return words;
    }
    private List<String> createAllWordsData(List<String> file1words, List<String> file2words) {
        for(int j = 0; j < file2words.size(); ++j) {
            file1words.add(file2words.get(j));
        }
        Set<String> set = new HashSet<>(file1words);
        file1words.clear();
        file1words.addAll(set);
        return file1words;
    }

    private List<String> getWords(String path) {
        try(InputStream fin = new FileInputStream(path)) {
            StringBuilder word = new StringBuilder();
            while (fin.available() > 0) {
                word.append((char) fin.read());
            }
            return getSeparateWords(word);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getSeparateWords(StringBuilder words) {
        String[] simbols = words.toString().split(" ");
        List<String> correct = new ArrayList<>();
        for(int i = 0; i < simbols.length; ++i) {
            for(String c : correct) {
                if(c.equals(simbols[i])) ++i;
            }
            correct.add(simbols[i]);
        }
        return correct;
    }

    private Vector<Integer> createVector(String file) {
        int size = this.dictionary.size();
        Vector<Integer> current = new Vector<>(size);
        List<String> fileWords = getAllWords(file);
        for(int i = 0; i < this.dictionary.size(); ++i) {
            int counter = 0;
            for(int j = 0; j < fileWords.size(); ++j) {
                if(this.dictionary.get(i).equals(fileWords.get(j))) {
                    ++counter;
                }
            }
            current.add(i, counter);
        }
        return current;
    }

    private List<String> getAllWords(String file) {
        try(InputStream fin = new FileInputStream(file)) {
            StringBuilder words = new StringBuilder();
            while (fin.available() > 0) {
                words.append((char) fin.read());
            }
            String[] simbols = words.toString().split(" ");
            List<String> result = new ArrayList<>();
            for(int i = 0; i < simbols.length; ++i) {
                result.add(simbols[i]);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double getSimularityResult() {
        double num = 0;
        double sum1 = 0, sum2 = 0;
        for(int it = 0; it < this.vector1.size(); ++it) {
            num += this.vector1.get(it) * this.vector2.get(it);
        }
        for(int it = 0; it < this.vector1.size(); ++it) {
            sum1 += Math.pow(this.vector1.get(it), 2);
        }
        for(int it = 0; it < this.vector2.size(); ++it) {
            sum2 += Math.pow(this.vector2.get(it), 2);
        }
        double dnum = Math.sqrt(sum1) * Math.sqrt(sum2);
        if(Math.abs(dnum) < 1e-6 || Math.abs(num) < 1e-6) {
            return 0;
        }
        return Math.floor((num / dnum) * 100.0) / 100.0;
    }
}

