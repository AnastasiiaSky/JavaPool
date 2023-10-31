package ex01;

// Singlton - дает гарантию что у класса будет всего один экземпляр класса.
// Предоставляет глобальную точку доступа к экземпляру данного класса
public final class UserIdsGenerator {

    private static final UserIdsGenerator INSTANCE = new UserIdsGenerator();

    private static int id = 0;

    private UserIdsGenerator() {}

    public static UserIdsGenerator getInstance()	{
        return INSTANCE;
    }

    public int generateId()	{
        id++;
        return (id);
    }
}