package ex04;

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

    public int getId() {return id;}
    public String getName() {return name;}
    public double getBalance() {return balance;}

    public void setBalance(double balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public void setName(String name) {this.name = name;}
    public void addTransaction(Transaction current) {
        if(!checkTransactionPossibility(current)) {
            throw new IllegalTransactionException();
        } else {
            userTransactions.addTransaction(current);
        }
    }

    public void delTransaction(String uuid) {
        try {
            this.userTransactions.deleteTransactionByUUID(uuid);
        } catch (TransactionNotFoundException e) {
            System.out.println("У данного пользователя не существует транзакции с таким UUID");
        }
    }

    public Transaction[] returnTransactionsArray() {
        return this.userTransactions.toArray();
    }

    public boolean checkTransactionPossibility(Transaction current) {
        if(current.getSender().getId() == this.id) {
            if(current.getAmount() > this.balance) {
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
