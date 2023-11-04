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
        ifUserNotFound(service);
        ifBalanceNull(service);
        usersTransactions(service);
        unpairedTransactions(service);

    }

    public static void unpairedTransactions(TransactionsService service) {
        Transaction[] unp = service.getUnpairedTransactions();
        System.out.println();
        System.out.println("Не парные транзакции");
        for(int it = 0; it < unp.length; ++it) {
            System.out.println(unp[it].transactionInfo());
        }
    }

    public static void usersTransactions(TransactionsService service) {
        System.out.println();
        System.out.println("Добавляем транзакции для сделующего пользователя");
        System.out.println(service.getUserList().getUserById(9).toString());
        service.addNewTransaction(service.getUserList().getUserById(9).getId(), service.getUserList().getUserById(1).getId(), 2000);
        service.addNewTransaction(service.getUserList().getUserById(9).getId(), service.getUserList().getUserById(2).getId(), 2000);
        service.addNewTransaction(service.getUserList().getUserById(1).getId(), service.getUserList().getUserById(9).getId(), 2000);
        service.addNewTransaction(service.getUserList().getUserById(2).getId(), service.getUserList().getUserById(9).getId(), 2000);
        System.out.println();
        System.out.println("Получаем список транзакций этого пользователя");
        System.out.println();
        Transaction[] sender = service.getUserList().getUserById(9).returnTransactionsArray();
        for (int it = 0; it < sender.length; ++it) {
            System.out.println(sender[it].transactionInfo());
        }
        System.out.println();
        String uuid = service.getUserList().getUserById(9).returnTransactionsArray()[2].getIdentifier();
        System.out.println("Удаляем транзакции у пользователя");
        service.deleteTransaction(9, uuid);
        sender = service.getUserList().getUserById(9).returnTransactionsArray();
        for (int it = 0; it < sender.length; ++it) {
            System.out.println(sender[it].transactionInfo());
        }


    }
    public static void ifBalanceNull(TransactionsService service) {
        System.out.println("Добавляем транзакцию для сделующих пользователей(У отправителя нулевой балланс)");
        System.out.println(service.getUserList().getUserById(8).toString());
        System.out.println(service.getUserList().getUserById(6).toString());
        service.addNewTransaction(service.getUserList().getUserById(8).getId(), service.getUserList().getUserById(6).getId(), 2000);


    }
    public static void ifUserNotFound(TransactionsService service) {
        System.out.println("Добавляем транзакцию для сделующих пользователей(Отправителя не существует в списке)");
        try {
            service.addNewTransaction(service.getUserList().getUserById(12).getId(), service.getUserList().getUserById(6).getId(), 2000);
        } catch(UserNotFoundException e) {
            System.out.println(e + ": Невозможно выполнить транзакцию. В списке пользователей нету отпрвителя с таким id или получателя с таким id");
        }
        System.out.println();
        System.out.println("(Получателя не существует в списке)");
        try {
            service.addNewTransaction(service.getUserList().getUserById(1).getId(), service.getUserList().getUserById(66).getId(), 2000);
        } catch(UserNotFoundException e) {
            System.out.println(e + ": Невозможно выполнить транзакцию. В списке пользователей нету отпрвителя с таким id или получателя с таким id");
        }
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
        service.addUser("Джон Сноу", 0);
        service.addUser("Арья Старк", 57859.00);
        System.out.println(service.getUserList().toString());
    }

}
