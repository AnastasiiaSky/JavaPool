package ex01;

class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Ошибка! Необходимо подать два аргумента для выполнения(аргумент = путь к файлу)");
            System.exit(-1);
        }
        try {
            String file1 = args[0];
            String file2 = args[1];
            FileChecking checker = new FileChecking(args[0], args[1]);
            System.out.println("Simularity = " + checker.getSimularity());
        } catch (Exception e) {
            System.err.println(e.getMessage() + "Не существует таких файлов");
            System.exit(-1);
        }
    }
}