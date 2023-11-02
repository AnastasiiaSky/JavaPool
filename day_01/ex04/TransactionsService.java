package ex04;

public class TransactionsService {
    UsersArrayList userList;
    int counter;

    TransactionsService() {
        this.userList = new UsersArrayList();
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }
    public void addUser(User user) {
        user.addUser(user);
    }
    public double getUserBalance(User user) {
        user.getBalance();
    }

    public void addNewTransaction(int senderId, int recipientId, double amout) {

    }
}