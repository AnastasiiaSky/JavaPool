package ex00;

class Program {
    public static void main(String[] args) {
        User person1 = new User("Anna", 5000.00);
        User person2 = new User("Anton", 300.00);

        System.out.println("User 1:");
        printInfoAboutUser(person1);
        person1.setUserName("Anna Ivanova");
        person1.setUserBalance(person1.getUserBalance() + 20.5);
        System.out.println("After using setters:");
        printInfoAboutUser(person1);
        System.out.println("User 2:");
        printInfoAboutUser(person2);
        person2.setUserName("Anton Petrov");
        person2.setUserBalance(person2.getUserBalance() + 12550.5);
        System.out.println("After using setters:");
        printInfoAboutUser(person2);

        Transaction first = new Transaction(person1, person2, -500);

        System.out.println("TRANSACTION 1");
        printInfoAboutTransaction(first);
        first.setRecipient(person2);
        first.setSender(person1);
        first.setAmount(10000);
        System.out.println("After using setters");
        printInfoAboutTransaction(first);

    }

    public static void printInfoAboutUser(User user) {
        System.out.println();
        System.out.printf("%-10s%15d\n%-10s%15s\n%-10s%15.2f\n",  "ID", user.getUserId(), "NAME",
                user.getUserName(), "BALANCE", user.getUserBalance());
        System.out.println("------------------------------------------------------------");


    }

    public static void printInfoAboutTransaction(Transaction transaction) {
        System.out.println();
        System.out.printf("%-15s%s\n%-15s%s (balance %10.2f)\n%-15s%s (balance %10.2f)\n%-15s%s\n%-15s%-10.2f\n",
                "Identifier", transaction.getIdentifier(), "Recipient", transaction.getRecipient().getUserName(),
                transaction.getRecipient().getUserBalance(), "Sender", transaction.getSender().getUserName(),
                transaction.getSender().getUserBalance(), "Category", transaction.getCategory(), "Amount",
                transaction.getAmount());
        System.out.println("------------------------------------------------------------");
    }
}