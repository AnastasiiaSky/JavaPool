package ex02;

class Program {
    public static void main(String[] args) {
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

        UsersArrayList testList = new UsersArrayList();
        System.out.println("UsersArrayList после создания.");
        printInfoAboutUsersList(testList);

        testList.addUser(person1);
        testList.addUser(person2);
        testList.addUser(person3);

        System.out.println("Добавляем новых пользователей в UsersArrayList");
        printInfoAboutUsersList(testList);

        System.out.println("Добавляем больше 10 новых пользователей в UsersArrayList");
        testList.addUser(person4);
        testList.addUser(person5);
        testList.addUser(person6);
        testList.addUser(person7);
        testList.addUser(person8);
        testList.addUser(person9);
        testList.addUser(person10);
        testList.addUser(person11);
        testList.addUser(person12);
        testList.addUser(person13);
        testList.addUser(person14);
        printInfoAboutUsersList(testList);


        System.out.println("Получаем пользователя по индексу 3 и по индексу 10");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-23s%s ID %d\n",  "UsersArrayList[3] - ",
                testList.getUserByIndex(3).getName(), testList.getUserByIndex(3).getId());
        System.out.printf("%-23s%s ID %d\n",  "UsersArrayList[11] - ",
                testList.getUserByIndex(10).getName(), testList.getUserByIndex(10).getId());
        System.out.println();
        System.out.println("Получаем пользователя по id 4 и по индексу 11");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-23s%s ID %d\n",  "UsersArrayList[3] - ",
                testList.getUserById(4).getName(), testList.getUserById(4).getId());
        System.out.printf("%-23s%s ID %d\n",  "UsersArrayList[11] - ",
                testList.getUserById(11).getName(), testList.getUserById(11).getId());4
    }


    public static void printInfoAboutUsersList(UsersArrayList testList) {
        System.out.printf("%-23s%d\n%-23s%d\n",  "Колличество пользователей - ", testList.getUsersCount(),
                "Размер массива - ", testList.getCapacity());
    }
}