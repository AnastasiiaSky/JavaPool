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

        System.out.println();
        System.out.println("Создаем несколько новых пользователей и проверяем уникальность " +
                "id у каждого из них");
        System.out.println("---------------------------------------------------------------" +
                "-------------------");

        User person1 = new User("Анна Иванова", 5555);
        User person2 = new User("Иван Петров", 55678);
        User person3 = new User("Алексей Смирнов", 14325);
        User person4 = new User("Дмитрий Тихонов", 6987);

        System.out.printf("%-10s%-20s%-5s%d\n",  "person1", person1.getName(), "ID - ",
                person1.getId());
        System.out.printf("%-10s%-20s%-5s%d\n",  "person2", person2.getName(), "ID - ",
                person2.getId());
        System.out.printf("%-10s%-20s%-5s%d\n",  "person3", person3.getName(), "ID - ",
                person3.getId());
        System.out.printf("%-10s%-20s%-5s%d\n",  "person4", person4.getName(), "ID - ",
                person4.getId());





    }
}