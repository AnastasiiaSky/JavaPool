package ex03;

import java.io.*;
import java.util.HashMap;

public class WorkingProcess {
    private final int threadsCount;
    private final HashMap<Integer, String> urls = new HashMap<>();
    private final String sourceFilename = "/Users/qylenett/Desktop/projects/Java_Bootcamp.Day03-1/src/ex03/files_urls.txt";


    public WorkingProcess(int threadsCount) {
        this.threadsCount = threadsCount;
        getUrls();
        execute();
    }

    public void execute() {
        Downloader downloader = new Downloader(urls, sourceFilename);
        for (int it = 0; it < threadsCount; ++it) {
            MyThreads thread = new MyThreads(downloader);
            thread.start();
        }
    }

    private void getUrls() {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilename))) {
            while (reader.ready()) {
                String urlInfo = reader.readLine();
                String[] info = urlInfo.split(" ");
                urls.put(Integer.parseInt(info[0]), info[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
