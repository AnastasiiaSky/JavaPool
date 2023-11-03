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
        if(usersValidation(senderId) == true && usersValidation(recipientId)) {
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

//    public Transaction[] getUnpairedTransactions() {
//        TransactionsList unpaired = new TransactionsLinkedList();
//        boolean flag = false;
//        for (int i = 0; i < userList.getUsersCount(); ++i) {
//            Transaction[] trans = userList.getUserByIndex(i).getUserTransactions().toArray();
//            for (int j = 0; j < trans.length; ++j){
//                flag = false;
//                Transaction trans1 = trans[j];
//                for (int k = 0; k < userList.getUsersCount(); ++j){
//                    if (i==k) continue;
//                    Transaction[] trans2 = userList.getUserByIndex(j).getUserTransactions().toArray();
//                    for (int p = 0; p < trans2.length; ++p){
//                        Transaction transaction2 = trans2[p];
//                        if (trans1.getIdentifier().equals(transaction2.getIdentifier())) {
//                            flag = true;
//                            break;
//                        }
//                    }
//                    if(flag) break;
//                }
//                if(!flag) unpaired.addTransaction(trans1);
//            }
//        }
//        System.out.println("ch");
//
//        return unpaired.toArray();
//    }
}