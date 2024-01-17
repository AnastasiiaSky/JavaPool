package edu.school21.Spring.printer;


import edu.school21.Spring.renderer.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;
    private LocalDateTime dateAndTime;


    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
        this.dateAndTime = LocalDateTime.now();
//        this.dateAndTime.format(DateTimeFormatter.ofPattern("dd:MM:yyyy hh:mm:ss"));
    }

    @Override
    public void print(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
        String formattedDateTime = this.dateAndTime.format(formatter);
        renderer.showData(formattedDateTime + " " + data);
    }
}
