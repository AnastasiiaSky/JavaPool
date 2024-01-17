package edu.school21.Spring.printer;

import edu.school21.Spring.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "";
    }

    @Override
    public void print(String data) {
        renderer.showData(prefix + " " + data);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
