package ex00;

class Program {
    public static void main(String[] args) {
        User person1 = new User("Anna", 5000.00);
        User person2 = new User("Anton", 300.00);

        System.out.println("USER 1:");
        System.out.println("-------");
        System.out.printf("%-10s%15d\n%-10s%15s\n%-10s%15.2f\n",  "ID", person1.getUserId(), "NAME",
                person1.getUserName(), "BALANCE", person1.getUserBalance());
        person1.setUserName("Anna Ivanova");
        person1.setUserBalance(person1.getUserBalance() + 20.5);
        System.out.println("After using setters:");
        System.out.println("--------------------");
        System.out.printf("%-10s%15d\n%-10s%15s\n%-10s%15.2f\n",  "ID", person1.getUserId(), "NAME",
                person1.getUserName(), "BALANCE", person1.getUserBalance());
        System.out.println();
        System.out.println("USER 2:");
        System.out.println("-------");
        System.out.printf("%-10s%15d\n%-10s%15s\n%-10s%15.2f\n",  "ID", person2.getUserId(), "NAME",
                person2.getUserName(), "BALANCE", person2.getUserBalance());
        person2.setUserName("Anton Petrov");
        person2.setUserBalance(person2.getUserBalance() + 12550.5);
        System.out.println("After using setters:");
        System.out.println("--------------------");
        System.out.printf("%-10s%15d\n%-10s%15s\n%-10s%15.2f\n",  "ID", person2.getUserId(), "NAME",
                person2.getUserName(), "BALANCE", person2.getUserBalance());
        System.out.println();

        Transaction first = new Transaction(person1, person2, -500);


        System.out.println("FIRST TRANSACTION");
        System.out.println("-----------------");
        System.out.printf("%-15s%s\n%-15s%s (balance %10.2f)\n%-15s%s (balance %10.2f)\n%-15s%s\n%-15s%-10.2f\n",
                "Identifier", first.getIdentifier(), "Recipient", first.getRecipient().getUserName(),
                first.getRecipient().getUserBalance(), "Sender", first.getSender().getUserName(),
                first.getSender().getUserBalance(), "Category", first.getCategory(), "Amount", first.getAmount());

        first.setRecipient(person2);
        first.setSender(person1);
        first.setAmount(10000);
        System.out.println("After using setters");
        System.out.println("-------------------");
        System.out.printf("%-15s%s\n%-15s%s (balance %10.2f)\n%-15s%s (balance %10.2f)\n%-15s%s\n%-15s%-10.2f\n",
                "Identifier", first.getIdentifier(), "Recipient", first.getRecipient().getUserName(),
                first.getRecipient().getUserBalance(), "Sender", first.getSender().getUserName(),
                first.getSender().getUserBalance(), "Category", first.getCategory(), "Amount", first.getAmount());











    }
}