package edu.school21.ORM.app;


import edu.school21.ORM.manager.OrmManager;
import edu.school21.ORM.models.Cat;
import edu.school21.ORM.models.User;

public class Program {
    public static void main(String[] args) {
        OrmManager ormManager = new OrmManager();

        User user = new User("Ivan", "Ivanov", 22);
        User user1 = new User("Petr", "Petrov", 23);

        Cat cat = new Cat("Musia", "grey", 2);
        Cat cat1 = new Cat("Barsik", "red", 7);

        ormManager.save(user);
        ormManager.save(user1);
        ormManager.save(cat);
        ormManager.save(cat1);

        user.setAge(25);
        cat.setColor("dark_gr");

        ormManager.update(user);
        ormManager.update(cat);

        DataSourceConfiguration.closeConnection();


    }
}
