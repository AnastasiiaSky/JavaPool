package ex03;

class Program {
    public static void main(String[] args) {
        User person1 = new User("Анна Иванова", 5555);
        User person2 = new User("Иван Петров", 55678);
        User person3 = new User("Алексей Смирнов", 14325);

        Transaction first = new Transaction(person1, person2, -500);
        Transaction second = new Transaction(person1, person3, -500);
        Transaction third = new Transaction(person3, person2, -500);



        TransactionsLinkedList test = new TransactionsLinkedList();

        test.addTransaction(first);
        printAddedTransaction(test);
        test.addTransaction(second);
        printAddedTransaction(test);

        test.addTransaction(third);
        printAddedTransaction(test);


    }

    public static void printAddedTransaction(TransactionsLinkedList test) {
        System.out.println("Размер списка: " + test.getSize());
//        System.out.printf("%30s%30\t%30s%30\n", "Указатель на следущую транзакцию: ", test.getHead().getNext(),
//                "Указатель на предыдущую транзакцию: ", test.getHead().getPrev());

        System.out.printf("%7s%30s\t%15s%15s\t%15s%15s\t%15s%15s\t%15s%.2f\n", "UUID: ", test.getHead().getIdentifier(),
                "Отправитель: ", test.getTail().getSender().getName(), "Получатель: ",
                test.getTail().getRecipient().getName(), "Тип: ", test.getTail().getCategory(),
                "Сумма: ", test.getTail().getAmount());
    }
}