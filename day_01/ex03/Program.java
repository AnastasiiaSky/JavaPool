package ex03;

class Program {
    public static void main(String[] args) {
        User person1 = new User("Анна Иванова", 5555);
        User person2 = new User("Иван Петров", 55678);
        User person3 = new User("Алексей Смирнов", 14325);

        Transaction first = new Transaction(person1, person2, -500);
        Transaction second = new Transaction(person1, person3, -500);


        TransactionsLinkedList test = new TransactionsLinkedList();

        test.addTransaction(first);
        test.addTransaction(second);

        System.out.println(test.getTransactionNumber());
        System.out.println(test.getStart().getNext().getCurrent().getIdentifier());
        System.out.println(test.getStart().getNext().getCurrent().getSender().getName());
        System.out.println(test.getStart().getNext().getCurrent().getRecipient().getName());

//        System.out.println(test.getTransactionNumber());
//        System.out.println(test.getFinish().getPrev().getCurrent().getIdentifier());
//        System.out.println(test.getFinish().getPrev().getCurrent().getSender().getName());
//        System.out.println(test.getFinish().getPrev().getCurrent().getRecipient().getName());

    }
}