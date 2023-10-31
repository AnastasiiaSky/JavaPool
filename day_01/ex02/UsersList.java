package ex02;

public interface UsersList {
    void addUser(User person);
    User getUserById(int personId);
    User getUserByIndex(int index);
    int getUsersCount();
}