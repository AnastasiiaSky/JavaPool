package ex02;

class Program {
    public static void main(String[] args) {
        User person1 = new User("Анна Иванова", 5555);
        User person2 = new User("Иван Петров", 55678);
        User person3 = new User("Алексей Смирнов", 14325);
        User person4 = new User("Дмитрий Тихонов", 6987);
        User person5 = new User("Юлия Кулакова", 3333);
        User person6 = new User("Иван Злобин", 4444);
        User person7 = new User("Максим Чкалов", 6666);
        User person8 = new User("Дмитрий Менделеев", 7777);
        User person9 = new User("Борис Акунин", 8888);
        User person10 = new User("Николай Островаский", 9999);
        User person11 = new User("Владимир Путин", 10000);

        UsersArrayList testList = new UsersArrayList();
        System.out.println("UsersArrayList после создания.");
        System.out.println("------------------------------");

        System.out.printf("%-23s%d\n%-23s%d\n",  "Список пользователей - ", testList.getUsersCount(), "Размер массива - ",
                testList.getCapacity());
        System.out.println();

        testList.addUser(person1);
        System.out.println("Добавляем нового пользователя в UsersArrayList");
        System.out.println("----------------------------------------------");
        System.out.printf("%-23s%d\n%-23s%d\n%-23s%s ID %d\n",  "Список пользователей - ", testList.getUsersCount(),
                "Размер массива - ", testList.getCapacity(), "Добавленный пользователь - ",
                testList.getUserByIndex(0).getName(), testList.getUserByIndex(0).getId());
        testList.addUser(person2);
        testList.addUser(person3);
        testList.addUser(person4);
        testList.addUser(person5);
        testList.addUser(person6);
        testList.addUser(person7);
        testList.addUser(person8);
        testList.addUser(person9);
        testList.addUser(person10);
        testList.addUser(person11);
        System.out.println();
        System.out.println("Добавляем еще 10 пользователей в UsersArrayList");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-23s%d\n%-23s%d\n",  "Список пользователей - ", testList.getUsersCount(), "Размер массива - ",
                testList.getCapacity());
        System.out.println();
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
                testList.getUserById(11).getName(), testList.getUserById(11).getId());


    }
}