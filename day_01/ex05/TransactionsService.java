package ex05;

import ex05.exceptions.TransactionNotFoundException;
import ex05.exceptions.UserNotFoundException;
import ex05.exceptions.IllegalTransactionException;
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

    public void addUser(User user) {
        counter += 1;
        userList.addUser(user);
    }
    public double getUserBalance(int id) {
        return userList.getUserById(id).getBalance();
    }

    public void addNewTransaction(int senderId, int recipientId, double amount) throws IllegalTransactionException {
        if(usersValidation(senderId) == true && usersValidation(recipientId)) {
                double amount1 = amount * -1;
                Transaction recipientTransaction = new Transaction(userList.getUserById(senderId), userList.getUserById(recipientId), amount1);
                Transaction senderTransaction = new Transaction(userList.getUserById(senderId), userList.getUserById(recipientId), amount);
                recipientTransaction.setIdentifier(senderTransaction.getIdentifier());
                workWithUserAfterMakingTransaction(senderId, amount1);
                workWithUserAfterMakingTransaction(recipientId, amount);
                userList.getUserById(senderId).addTransaction(senderTransaction);
                userList.getUserById(recipientId).addTransaction(recipientTransaction);
        }
    }

    public boolean usersValidation(int id) {
        boolean valid = false;
        try {
            userList.getUserById(id);
            valid = true;
        } catch (UserNotFoundException e) {
            System.out.println(e + ": Невозможно выполнить операцию. В списке пользователей нету отпрвителя с таким id или получателя с таким id");
        }
        return valid;
    }

    public void workWithUserAfterMakingTransaction(int userId, double amount) {
        double currenBalance = userList.getUserById(userId).getBalance();
        currenBalance += amount;
        userList.getUserById(userId).setBalance(currenBalance);
    }

    public void deleteTransaction(int id, String uuid) {

        boolean validation = usersValidation(id);
        if(validation == true) {
            try{
                userList.getUserById(id).getUserTransactions().deleteTransactionByUUID(uuid);
            } catch (TransactionNotFoundException e) {
                System.out.println(e + ": У данного пользователя не существует транзакции с таким UUID");
            }
        }
    }

    public Transaction[] getUnpairedTransactions() {
        Transaction[] allTransactions = allTransactions();
        TransactionsList unpaired = new TransactionsLinkedList();
        for(int i = 0; i < allTransactions.length; ++i) {
            boolean ans = false;
            for(int j = 0; j < allTransactions.length; ++j) {
                if(j != i) {
                    if(allTransactions[i].getIdentifier().equals(allTransactions[j].getIdentifier()))
                        ans = true;
                }
            }
            if(ans == false) unpaired.addTransaction(allTransactions[i]);
        }
        return unpaired.toArray();
    }

    public Transaction[] allTransactions() {
        TransactionsList allTransactions = new TransactionsLinkedList();
        for(int i = 0; i < userList.getUsersCount(); ++i) {
            Transaction[] thisUserTransactions = userList.getUserByIndex(i).getUserTransactions().toArray();
            for(int j = 0; j < thisUserTransactions.length; ++j) {
                allTransactions.addTransaction(thisUserTransactions[j]);
            }
        }
        Transaction[] result = allTransactions.toArray();
        return result;
    }
}