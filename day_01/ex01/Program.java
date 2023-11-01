package ex01;
class Program {
    public static void main(String[] args) {
        UserIdsGenerator first = UserIdsGenerator.getInstance();
        UserIdsGenerator second = UserIdsGenerator.getInstance();
        printSingletonInfo(first, second);

        System.out.println("Создаем несколько новых пользователей и проверяем уникальность " +
                "id у каждого из них");
        System.out.println("---------------------------------------------------------------" +
                "-------------------");

        User person1 = new User("Анна Иванова", 5555);
        User person2 = new User("Иван Петров", 55678);
        User person3 = new User("Алексей Смирнов", 14325);
        User person4 = new User("Дмитрий Тихонов", 6987);
        User person5 = new User("Владимир Путин", 6987);
        User person6 = new User("Дмитрий Медведев", 6987);
        User person7 = new User("Александр Блок", 6987);
        User person8 = new User("Федор Достоевский", 6987);
        User person9 = new User("Сергей Есенин", 6987);
        User person10 = new User("Борис Акунин", 6987);
        User person11 = new User("Антон Чехов", 6987);
        User person12 = new User("Александр Пушкин", 6987);
        User person13 = new User("Сергей Минаев", 6987);
        User person14 = new User("Андрей Рублев", 6987);

        printInfoAboutUser(person1);
        printInfoAboutUser(person2);
        printInfoAboutUser(person3);
        printInfoAboutUser(person4);
        printInfoAboutUser(person5);
        printInfoAboutUser(person6);
        printInfoAboutUser(person7);
        printInfoAboutUser(person8);
        printInfoAboutUser(person9);
        printInfoAboutUser(person10);
        printInfoAboutUser(person11);
        printInfoAboutUser(person12);
        printInfoAboutUser(person13);
        printInfoAboutUser(person14);
    }

    public static void printInfoAboutUser(User user) {
        System.out.printf("%-20s%-5s%d\n", user.getName(), "ID - ",
                user.getId());

    }

    public static void printSingletonInfo(UserIdsGenerator first, UserIdsGenerator second) {
        System.out.println("Подтверждение, что генератор ID создается только 1 раз.");
        System.out.println("При выводе текстового представления объекта мы видим что две " +
                "ссылочные переменные ссылаются на один объект в памяти");
        System.out.println("first      " + first.toString());
        System.out.println("second     " + second.toString());
    }
}