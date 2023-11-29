package ex03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Downloader {
    private final HashMap<Integer, String> urls;
    private final String sourceFilename;
    private int counter;

    public Downloader(HashMap<Integer, String> urls, String sourceFilename) {
        this.urls = urls;
        this.sourceFilename = sourceFilename;
        this.counter = 1;
    }

    public synchronized String getUrl() {
        if (urls.isEmpty()) System.exit(0);
        String url = urls.get(counter);
        urls.remove(counter);
        ++counter;
        return url;
    }

    public boolean urlsIsEmpty() {
        return urls.isEmpty();
    }

    public void downloadFile(String url) {
        try {
            System.out.println("Поток " + Thread.currentThread().getName() + " начал скачивание файла " + url);
            URL urlCurr = new URL(url);
            String outputPath = getOutputPath(url);
            downloadingProcess(urlCurr, outputPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void downloadingProcess(URL url, String path) {
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream out = new FileOutputStream(path)) {
            out.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            System.out.println("Поток " + Thread.currentThread().getName() + " закончил скачивание файла " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getOutputPath(String url) {
        Path path = Paths.get(url);
        String filename = String.valueOf(path.getFileName());
        return Path.of(sourceFilename).getParent().normalize() + "/" + "DownloadedFiles" + "/" + filename;
    }
}
