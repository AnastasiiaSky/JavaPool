
package ex00;
class User {
    private int userId;
    private String userName;
    private double userBalance;
    private static int usersCount = 0;

    public User(String name, double balance) {
        ++usersCount;
        userId = usersCount;
        userName = name;
        if (balance >= 0) userBalance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public static int getUsersCount() {
        return usersCount;
    }

    void setUserName(String name) {
        userName = name;
    }

    void setUserBalance(double balance) {
        if (balance < 0) {
            balance = 0;
        } else {
            userBalance = balance;
        }
    }
    @Override
    public String toString() {
        return "USER{"
                + "user id = " + userId
                + ", user name = " + userName
                + ", user balance = " + userBalance
                + '}';
    }
}
