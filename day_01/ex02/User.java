package ex02;

class User {
    private int id;
    private String name;
    private double balance;


    public User(String name, double balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance >= 0) this.balance = balance;
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

    @Override
    public String toString() {
        return "User{"
                + "user id = " + id
                + ", user name = " + name
                + ", user balance = " + balance
                + '}';
    }
}
