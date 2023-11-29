package ex03;


public class MyThreads extends Thread {
    private final Downloader downloader;


    public MyThreads(Downloader downloader) {
        this.downloader = downloader;
    }

    @Override
    public void run() {
        while (!downloader.urlsIsEmpty()) {
            downloader.downloadFile(downloader.getUrl());
        }
    }
}