package ex00;

class Program {
    public static void main(String[] args) {
        User person1 = new User("Anna", 5000.00);
        User person2 = new User("Anton", 300.00);

        System.out.println(person1.toString());
        System.out.println(person2.toString());

        person1.setUserName("Anna Ivanova");
        person1.setUserBalance(person1.getUserBalance() + 20.5);
        person2.setUserName("Anton Petrov");
        person2.setUserBalance(person2.getUserBalance() + 12550.5);

        System.out.println("After using setters:");
        System.out.println(person1.toString());
        System.out.println(person2.toString());

        Transaction first = new Transaction(person1, person2, -500);

        System.out.println(first.toString());
        first.setRecipient(person2);
        first.setSender(person1);
        first.setAmount(10000);
        System.out.println("After using setters");
        System.out.println(first.toString());
    }
}