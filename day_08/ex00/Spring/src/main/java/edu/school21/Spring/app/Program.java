package edu.school21.Spring.app;


import edu.school21.Spring.printer.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("PrinterWithDateTimeStandartUpper", Printer.class);

        printer.print("Hello!");


//        "PrinterWithDateTimeStandartLower"
//        "PrinterWithDateTimeErrUpper"
//        "PrinterWithDateTimeErrLower"
//        "PrinterWithPrefixStandartUpper"
//        "PrinterWithPrefixStandartLower"
//        "PrinterWithPrefixErrUpper"
//        "PrinterWithPrefixErrLower"
        context.close();

    }
}


