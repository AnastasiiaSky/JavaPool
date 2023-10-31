package ex01;

class Program {
    public static void main(String[] args) {
        UserIdsGenerator first = UserIdsGenerator.getInstance();
        UserIdsGenerator second = UserIdsGenerator.getInstance();

        System.out.println("Подтверждение, что генератор ID создается только 1 раз.");
        System.out.println("При выводе текстового представления объекта мы видим что две " +
                "ссылочные переменные ссылаются на один объект в памяти");
        System.out.println("--------------------------------------------------------------" +
                "------------------------------------------------------");
        System.out.println("first      " + first.toString());
        System.out.println("second     " + second.toString());



    }
}