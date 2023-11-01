package ex03;

class Program {
    public static void main(String[] args) {
        User person1 = new User("Анна Иванова", 5000);
        User person2 = new User("Иван Петров", 55000);
        User person3 = new User("Алексей Смирнов", 1000);
        User person4 = new User("Сергей Есенин", 300);
        User person5 = new User("Борис Акунин", 10000);

        Transaction trans1 = new Transaction(person1, person2, -500);
        Transaction trans2 = new Transaction(person1, person3, 500);
        Transaction trans3 = new Transaction(person1, person4, -200);
        Transaction trans4 = new Transaction(person3, person5, 3500);
        Transaction trans5 = new Transaction(person2, person1, -15);
        Transaction trans6 = new Transaction(person2, person3, 150);
        Transaction trans7 = new Transaction(person2, person4, -350);
        Transaction trans8 = new Transaction(person2, person5, 99.9);

        TransactionsLinkedList test = new TransactionsLinkedList();

        test.addTransaction(trans1);
        test.addTransaction(trans2);
        test.addTransaction(trans3);
        test.addTransaction(trans4);
        System.out.println("После добавления транзакций в TransactionsLinkedList");
        printAddedTransactions(test);

        test.deleteTransactionByUUID(trans3.getIdentifier());

        System.out.println("После удаления транзакции из середины TransactionsLinkedList");
        printAddedTransactions(test);

        test.deleteTransactionByUUID(trans1.getIdentifier());
        System.out.println("После удаления транзакции из начала TransactionsLinkedList");
        printAddedTransactions(test);

//        test.deleteTransactionByUUID(trans8.getIdentifier());
//        System.out.println("После удаления транзакции из конца TransactionsLinkedList");
//        printAddedTransactions(test);




    }

    public static void printAddedTransactions(TransactionsLinkedList test) {
        System.out.println("Размер списка: " + test.getSize());
        Transaction current = test.getHead();
        while(current != null) {
            System.out.printf("%7s%35s\t%15s%15s\t%15s%15s\t%15s%15s\t%15s%.2f\n", "UUID: ", current.getIdentifier(),
                    "Отправитель: ", current.getSender().getName(), "Получатель: ", current.getRecipient().getName(),
                    "Тип: ", current.getCategory(), "Сумма: ", current.getAmount());
            current = current.getNext();
        }
    }
}
//
//        while(current != tail) {
//                if(current.getIdentifier().equals(uuid)) {
//                isFound = true;
//                Transaction tmpNext = current.getNext();
//                Transaction tmpPrev = current.getPrev();
//                tmpPrev.setNext(tmpNext);
//                tmpNext.setPrev(tmpPrev);
//                current.setNext = null;
//                current.setPrev = null;
//                } else {
//                current = current.getNext();
//                }
//                }