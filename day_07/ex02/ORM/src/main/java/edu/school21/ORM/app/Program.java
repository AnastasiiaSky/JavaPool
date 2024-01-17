package edu.school21.ORM.app;


import edu.school21.ORM.manager.OrmManager;
import edu.school21.ORM.models.Cat;
import edu.school21.ORM.models.User;

public class Program {
    public static void main(String[] args) {
        OrmManager ormManager = new OrmManager();
        System.out.println("\nCreating data for the tables\n");
        User user = new User("Ivan", "Ivanov", 22);
        User user1 = new User("Petr", "Petrov", 23);
        Cat cat = new Cat("Musia", "grey", 2);
        Cat cat1 = new Cat("Barsik", "red", 7);



        ormManager.save(user);
        ormManager.save(user1);
        ormManager.save(cat);
        ormManager.save(cat1);




        System.out.println("\nUpdating data for the tables\n");
        user.setAge(25);
        cat.setColor("dark_gr");

        ormManager.update(user);
        ormManager.update(cat);

        System.out.println("\nGetting data from the tables to create an object\n");
        User foundUser = ormManager.findById(1L, User.class);
        System.out.println(foundUser.toString());
        Cat foundCat = ormManager.findById(2L, Cat.class);
        System.out.println(foundCat.toString());
        DataSourceConfiguration.closeConnection();
    }
}
