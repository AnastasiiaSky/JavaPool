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
        test.addTransaction(trans5);
        test.addTransaction(trans6);
        test.addTransaction(trans7);

        System.out.println("После добавления транзакций в TransactionsLinkedList");
        printAddedTransactions(test);
        System.out.println("После удаления транзакции из середины TransactionsLinkedList");
        transactionDel(test, trans3.getIdentifier());
        System.out.println("После удаления транзакции из начала TransactionsLinkedList");
        transactionDel(test, trans1.getIdentifier());
        System.out.println("После удаления транзакции из конца TransactionsLinkedList");
        transactionDel(test, trans7.getIdentifier());
        System.out.println("Попытка удаления транзакции отсутсвующей в списке транзакций из TransactionsLinkedList");
        transactionDel(test, trans8.getIdentifier());

        System.out.println("Преобразуем TransactionsLinkedList в массив");
        transArr(test);
    }

    public static void transArr(TransactionsLinkedList test) {
        System.out.println();
        Transaction[] testArr = test.toArray();
        for (int it = 0; it < testArr.length; ++it) {
            System.out.println("Transaction[" + it + "] - " + testArr[it].toString());
        }
        System.out.println();
    }

    public static void transactionDel(TransactionsLinkedList test, String uuid) {
        try {
            test.deleteTransactionByUUID(uuid);
            printAddedTransactions(test);
            System.out.println();
        } catch (TransactionNotFoundException e) {
            System.out.println();
            System.out.println("Transaction not found exception");
        }
    }

    public static void printAddedTransactions(TransactionsLinkedList test) {
        System.out.println();
        System.out.println(test.toString());
        Transaction current = test.getHead();
        while(current != null) {
            System.out.println(current.toString());
            current = current.getNext();
        }
    }
}
