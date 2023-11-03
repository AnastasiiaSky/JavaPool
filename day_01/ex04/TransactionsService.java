package ex04;

import ex04.exceptions.TransactionNotFoundException;
import ex04.exceptions.UserNotFoundException;
import ex04.exceptions.IllegalTransactionException;
public class TransactionsService {
    UsersArrayList userList;
    int counter;

    TransactionsService() {
        this.userList = new UsersArrayList();
        this.counter = 0;
    }

    public UsersArrayList getUserList() {
        return this.userList;
    }


    public int getCounter() {
        return counter;
    }
    public void addUser(String name, double balance) {
        counter += 1;
        User newUser = new User(name, balance);
        userList.addUser(newUser);
    }
    public double getUserBalance(int id) {
        return userList.getUserById(id).getBalance();
    }

    public void addNewTransaction(int senderId, int recipientId, double amount) {
        // отправитель
        try {
            userList.getUserById(senderId);
            userList.getUserById(recipientId);
            try {
                double amount1 = amount * -1;
                Transaction recipientTransaction = new Transaction(userList.getUserById(senderId), userList.getUserById(recipientId), amount1);
                Transaction senderTransaction = new Transaction(userList.getUserById(senderId), userList.getUserById(recipientId), amount);
                recipientTransaction.setIdentifier(senderTransaction.getIdentifier());
                workWithUserAfterMakingTransaction(senderId, amount1);
                workWithUserAfterMakingTransaction(recipientId, amount);
                userList.getUserById(senderId).addTransaction(senderTransaction);
                userList.getUserById(recipientId).addTransaction(recipientTransaction);
            } catch (IllegalTransactionException e) {
                System.out.println(e + ": Невозможно выполнить транзакцию. Баланс отправителя меньше отправляемой суммы");
            }
        } catch (UserNotFoundException e) {
            System.out.println(e + ": Невозможно выполнить транзакцию. В списке пользователей нету отпрвителя с таким id или получателя с таким id");
        }
    }

    public void workWithUserAfterMakingTransaction(int userId, double amount) {
        double currenBalance = userList.getUserById(userId).getBalance();
        currenBalance += amount;
        userList.getUserById(userId).setBalance(currenBalance);
    }
}