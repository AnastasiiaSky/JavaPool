package ex02;

import ex02.exceptions.*;
import java.io.File;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class FileManager {
    private File pwd;
    public FileManager(String path) {
        this.pwd = new File(path);
        if(!pwd.exists()) throw new NoSuchFileOrDirectory();
        if(!pwd.isDirectory()) throw new NoSuchFileOrDirectory();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("-> ");
            String command = sc.nextLine();
            chooseCommand(command);
        }
    }

    private void chooseCommand(String command) {
        if(command.length()<1) return;
        String[] arguments = command.split(" ");
        if(arguments[0].equals("cd")) {
            ChangeDirectory(arguments);
        } else if(arguments[0].equals("ls")) {
            ListInformation();
        } else if(arguments[0].equals("mv")) {
            moveOrRename(arguments);
        } else if(arguments[0].equals("exit")) {
            exitProgramm();
        } else throw new WrongCommandException();
    }

    private void exitProgramm() {
        System.exit(0);
    }

    private void moveOrRename(String[] arguments) {
        if(arguments.length != 3) return;
        Path firstA = Paths.get(arguments[1]);
        Path secondA = Paths.get(arguments[2]);
        try {
            if (!firstA.isAbsolute()) firstA = Paths.get(this.pwd.getAbsolutePath() + "/" + firstA);
            File source = firstA.toAbsolutePath().normalize().toFile();
            if (!source.exists()) {
                System.out.println(arguments[1] + " do not exists");
            }
            if (!secondA.isAbsolute()) secondA = Paths.get(this.pwd.getAbsolutePath() + "/" + secondA);
            File destanation = secondA.toAbsolutePath().normalize().toFile();
            if (!destanation.exists()) {
                source.renameTo(destanation);
            } else if (destanation.isFile()) {
                System.out.println("File " + arguments[2] + " already exists");
            } else if (destanation.isDirectory()) {
                destanation = Paths.get(destanation.getAbsolutePath() + "/" + source.getName()).toAbsolutePath().normalize().toFile();
                source.renameTo(destanation);
            }
        } catch (NoSuchFileOrDirectory | InvalidPathException e) {
            System.out.println(e);
        }
    }

    private void ChangeDirectory(String[] arguments) {
        try {
            if (arguments.length != 2) return;
            String path = arguments[1];
            Path testPath = Paths.get(path);
            if (!testPath.isAbsolute()) testPath = Paths.get(this.pwd.getAbsolutePath() + "/" + testPath);
            File newPwd = testPath.toAbsolutePath().normalize().toFile();
            if (!newPwd.exists()) throw new NoSuchFileOrDirectory();
            if (!newPwd.isDirectory()) throw new NoSuchFileOrDirectory();
            this.pwd = newPwd;
            System.out.println(testPath);
        } catch (NoSuchFileOrDirectory | InvalidPathException e) {
            System.out.println(e);
        }
    }

    private void ListInformation() {
        File[] files = this.pwd.listFiles();
        for(int it = 0; it < files.length; ++it) {
            double fileSize = getFileSize(files[it]);
            System.out.printf("%-30s\t%10.4f KB\n", files[it].getName(), fileSize);
        }
    }

    private double getFileSize(File file) {
        return (double) file.length() / 1024;
    }
}