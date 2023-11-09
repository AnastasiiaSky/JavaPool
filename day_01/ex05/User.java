package ex05;

import ex05.exceptions.UserNotFoundException;
import ex05.exceptions.TransactionNotFoundException;
import ex05.exceptions.IllegalTransactionException;

class User {
    private final int id;
    private String name;
    private double balance;
    TransactionsLinkedList userTransactions;


    public User(String name, double balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = 0;
        if (balance >= 0) this.balance = balance;
        userTransactions = new TransactionsLinkedList();
    }

    public TransactionsLinkedList getUserTransactions() {
        return this.userTransactions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTransaction(Transaction current) {
        if (!checkTransactionPossibility(current)) {
            throw new IllegalTransactionException();
        } else {
            userTransactions.addTransaction(current);
        }
    }

    public Transaction[] returnTransactionsArray() {
        Transaction[] tranList = new Transaction[userTransactions.getSize()];
        try {
            tranList = this.userTransactions.toArray();
        } catch (TransactionNotFoundException e) {
            System.out.println(e + ": Списке транзакций данного пользователя еще нет транзакций.");
        }
        return tranList;
    }

    public boolean checkTransactionPossibility(Transaction current) {
        if (current.getSender().getId() == this.id) {
            if (current.getAmount() > this.balance) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "User {"
                + "user id = " + id
                + ", user name = " + name
                + ", user balance = " + balance
                + '}';
    }
}
