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
        System.out.println(testList.toString());
        testList.addUser(person1);
        testList.addUser(person2);
        testList.addUser(person3);
        System.out.println("Добавляем новых пользователей в UsersArrayList");
        System.out.println(testList.toString());
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
        System.out.println(testList.toString());
        System.out.println("Получаем пользователя по индексу!");
        findUserByIndex(testList, 3);
        findUserByIndex(testList, 5);
        findUserByIndex(testList, 25);
        System.out.println("Получаем пользователя по id");
        findUserById(testList, 3);
        findUserById(testList, 5);
        findUserById(testList, 25);
    }

    public static void findUserById(UsersArrayList testList, int id) {
        try {
            User target = testList.getUserById(id);
            System.out.println("Id is " + id);
            System.out.println(target.toString());
        } catch (UserNotFoundException e) {
            System.out.println("UserNotFoundException");
            System.out.println(testList.toString() + " Id is - " + id);
        }
    }

    public static void findUserByIndex(UsersArrayList testList, int index) {
        try {
            User target = testList.getUserByIndex(index);
            System.out.println("Index is " + index);
            System.out.println(target.toString());
        } catch (UserNotFoundException e) {
            System.out.println("UserNotFoundException: ");
            System.out.println(testList.toString() + " Index is - " + index);
        }
    }
}