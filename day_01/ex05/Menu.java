package ex05;
import java.util.Scanner;

import ex05.exceptions.TransactionNotFoundException;
import ex05.exceptions.UserNotFoundException;
import ex05.exceptions.IllegalTransactionException;
import ex05.exceptions.UnknownArgumentException;
class Menu {
    Scanner sc;
    boolean menuMode;
    TransactionsService service;
    public Menu (boolean menuMode) {
        this.sc = new Scanner(System.in);
        this.service = new TransactionsService();
        this.menuMode = menuMode;
    }

    public void consoleMenu() {
        boolean exit = false;
        while (true) {
            if(menuMode == false) {
                System.out.println("1. Add a user.");
                System.out.println("2. View user balances.");
                System.out.println("3. Perform a transfer.");
                System.out.println("4. View all transactions for a specific user.");
                System.out.println("5. Finish execution.");
                exit = userMode();
                if(exit == true) break;
            } else {
                System.out.println("1. Add a user.");
                System.out.println("2. View user balances.");
                System.out.println("3. Perform a transfer.");
                System.out.println("4. View all transactions for a specific user.");
                System.out.println("5. DEV – remove a transfer by ID.");
                System.out.println("6. DEV – check transfer validity.");
                System.out.println("7. Finish execution.");
                exit = devMode();
                if(exit == true) break;
            }
        }
        sc.close();
    }

    private boolean userMode() {
        boolean exit = false;
        String ch = sc.nextLine().trim();
        try {
                int choise = Integer.parseInt(ch);
                if(choiseValidation(choise) == true) {
                   exit = userModePoints(choise);
                }

        }catch (NumberFormatException e) {
            System.out.println(e + ": Введите целое число соответствуещее пункту меню.");
        }
    return exit;
    }

    private boolean devMode() {
        boolean exit = false;
        String ch = sc.nextLine().trim();
        try {
            int choise = Integer.parseInt(ch);
            if(choiseValidation(choise) == true) {
                exit = devModePoints(choise);
            }
        }catch (NumberFormatException e) {
            System.out.println(e + ": Введите целое число соответствуещее пункту меню.");
        }
        return exit;
    }

    private boolean userModePoints(int choise) {
        boolean exit = false;
        switch (choise) {
            case 1:
                userAdding();
                break;
            case 2:
                checkBalance();
                break;
            case 3:
                makeTransfer();
                break;
            case 4:
                getTransForUser();
                break;
            case 5:
               exit = true;
               break;
        }
        return exit;
    }

    private boolean devModePoints(int choise) {
        boolean exit = false;
        switch (choise) {
            case 1:
                userAdding();
                break;
            case 2:
                checkBalance();
                break;
            case 3:
                makeTransfer();
                break;
            case 4:
                getTransForUser();
                break;
            case 5:
                removeTrans();
                break;
            case 6:
                transfValid();
                break;
            case 7:
                exit = true;
                break;
        }
        return exit;
    }

    private void userAdding() {
        System.out.println("Enter a user name and a balance");
        String input = sc.nextLine().trim();
        try {
            String[] inputParts = input.split("\\s+");
            if(inputParts.length == 2) {
               String name = inputParts[0];
               double balance = Double.parseDouble(inputParts[1]);
               User user = new User(name, balance);
               service.addUser(user);
               System.out.println("User with id = " + user.getId() + " is added");
               System.out.println("---------------------------------------------------------");
            }
        } catch (UnknownArgumentException e) {
            System.out.println(e + ": Введите имя пользователя и баланс через пробел");
        }
    }

    private void checkBalance() {
        System.out.println("Enter a user ID");
        String input = sc.nextLine().trim();
        try {
            int id = Integer.parseInt(input);
            System.out.println(service.getUserList().getUserById(id).getName() + " - " +
                    service.getUserList().getUserById(id).getBalance());
            System.out.println("---------------------------------------------------------");
        } catch (UserNotFoundException e) {
            System.out.println(e + ": В списке пользователей нет пользователя с таким ID");
        }
    }

    private void makeTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String input = sc.nextLine().trim();
        try {
            String[] inputParts = input.split("\\s+");
            int send = Integer.parseInt(inputParts[0]);
            int res = Integer.parseInt(inputParts[1]);
            double balance = Double.parseDouble(inputParts[2]);
            service.addNewTransaction(send, res, balance);
            System.out.println("The transfer is completed");
            System.out.println("---------------------------------------------------------");
        } catch (UserNotFoundException | IllegalTransactionException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e + ": В списке пользователей нет пользователя с таким ID или сумма перевода" +
                    " больше, чем баланс отправителя, или некорректно введены данные.");
        }
    }

    private void getTransForUser() {
        System.out.println("Enter a user ID");
        String input = sc.nextLine().trim();
        try{
            int id = Integer.parseInt(input);
            Transaction[] userTrans = service.getUserList().getUserById(id).returnTransactionsArray();
            for (int it = 0; it < userTrans.length; ++it) {
                System.out.println(userTrans[it].transactionInfo());
            }
            System.out.println("---------------------------------------------------------");
        } catch (UserNotFoundException e) {
            System.out.println(e + ": В списке пользователей нет пользователя с таким ID.");
        }

    }

    private void removeTrans() {
        System.out.println("Enter a user ID and a transfer ID");
        String input = sc.nextLine().trim();
        try {
            String userName = null;
            int resId = 0;
            double sum = 0;
            String[] inputParts = input.split("\\s+");
            int userId = Integer.parseInt(inputParts[0]);
            String tranfId = inputParts[1];
            service.deleteTransaction(userId, tranfId);
            Transaction[] unp = service.getUnpairedTransactions();
            for(int it = 0; it < unp.length; ++it) {
                if(unp[it].getIdentifier().equals(tranfId)) {
                    userName = unp[it].getRecipient().getName();
                    resId = unp[it].getRecipient().getId();
                    sum = unp[it].getAmount();
                }
            }
            System.out.println("Transfer To " + userName + "(id = " + resId + ") " + sum + " removed");
            System.out.println("---------------------------------------------------------");
        } catch (TransactionNotFoundException | UserNotFoundException e) {
            System.out.println(e + ": Id транзакции не найден, или Id пользователя не найден.");
        }

    }

    private void transfValid() {
        System.out.println("Check results.");
        try {
            Transaction[] unp = service.getUnpairedTransactions();
            for(int it = 0; it < unp.length; ++it) {
                System.out.println(unp[it].transactionInfo());
                }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e + ": Не парных транзакций нет");

        }
    }

    private boolean choiseValidation(int choise) {
        if(this.menuMode == false) {
            if(choise < 1 || choise > 5) {
                return false;
            }
        } else {
            if(choise < 1 || choise > 7) {
                return false;
            }
        }
        return true;
    }
}