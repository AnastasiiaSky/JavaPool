package ex04;

import ex04.exceptions.TransactionNotFoundException;
import ex04.exceptions.UserNotFoundException;
import ex04.exceptions.IllegalTransactionException;

class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();
        System.out.println("Добавили новых пользователей");
        userAdding(service);
        firstTransaction(service);

    }

    public static void firstTransaction(TransactionsService service) {
        System.out.println("Добавляем транзакцию для сделующих пользователей");
        System.out.println(service.getUserList().getUserById(1).toString());
        System.out.println(service.getUserList().getUserById(6).toString());
        System.out.println();
        service.addNewTransaction(service.getUserList().getUserById(1).getId(), service.getUserList().getUserById(6).getId(), 2000);
        Transaction[] sender = service.getUserList().getUserById(1).returnTransactionsArray();
        for (int it = 0; it < sender.length; ++it) {
            System.out.println(sender[it].transactionInfo());
        }
        Transaction[] getter = service.getUserList().getUserById(6).returnTransactionsArray();
        for (int it = 0; it < getter.length; ++it) {
            System.out.println(getter[it].transactionInfo());
        }
        System.out.println("Проверка баланса после транзакции");
        System.out.println();
        System.out.println("Для отправителя " + service.getUserBalance(1) + ". Для получателя " + service.getUserBalance(6));
        System.out.println();
        System.out.println(service.getUserList().getUserById(1).toString());
        System.out.println(service.getUserList().getUserById(6).toString());

    }



    public static void userAdding(TransactionsService service) {
        service.addUser("Анна Иванова", 4000.5);
        service.addUser("Иван Петров", 55000.88);
        service.addUser("Алексей Смирнов", 35698.99);
        service.addUser("Сергей Есенин", 45987.22);
        service.addUser("Борис Акунин", 10000.5);
        service.addUser("Иван Максимов", 0);
        service.addUser("Антон Чехов", 9999.9);
        service.addUser("Джон Сноу", 35);
        service.addUser("Арья Старк", 57859.00);
        System.out.println(service.getUserList().toString());
    }

}
