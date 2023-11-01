package ex03;

class Program {
    public static void main(String[] args) {
        User person1 = new User("Анна Иванова", 5555);
        User person2 = new User("Иван Петров", 55678);
        User person3 = new User("Алексей Смирнов", 14325);
        User person9 = new User("Сергей Есенин", 6987);
        User person10 = new User("Борис Акунин", 6987);

        Transaction first = new Transaction(person1, person2, -500);
        Transaction second = new Transaction(person1, person3, 500);
        Transaction tmpo = new Transaction(person9, person10, -500);
        Transaction tmpo1 = new Transaction(person3, person10, 3500);



        TransactionsLinkedList test = new TransactionsLinkedList();


        test.addTransaction(first);
        test.addTransaction(second);
        test.addTransaction(tmpo);
        test.addTransaction(tmpo1);

        printAddedTransactions(test);

        String uuid = tmpo.getIdentifier();
        test.deleteTransactionByUUID(uuid);
        System.out.println("After delete ----------------------");
        printAddedTransactions(test);



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