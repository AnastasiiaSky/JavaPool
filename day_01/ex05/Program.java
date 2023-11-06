package ex05;

import ex05.exceptions.TransactionNotFoundException;
import ex05.exceptions.UserNotFoundException;
import ex05.exceptions.IllegalTransactionException;
import ex05.exceptions.UnknownArgumentException;


class Program {
    public static void main(String[] args) {
        try{
            boolean menuMode = false;
            System.out.println("Если Вы хотите открыть программу в режиме разработчика используйте аргумент" +
                    " --profile=dev");
            if(args.length > 0 && args[0].equals("--profile=dev")) {
                menuMode = true;
                System.out.println("Вы запускаете программу в режиме разработчика. Вам доступно удаление транзакций" +
                        " по ID и поиск непарных транзакций");
            } else if(args.length == 0) {
                menuMode = false;
                System.out.println("Вы запускаете программу в режиме пользователя. Вам не доступно удаление транзакций" +
                        " по ID и поиск непарных транзакций");
            }
            Menu myMenu = new Menu(menuMode);
            myMenu.consoleMenu();
        } catch (UnknownArgumentException e) {
            System.out.println(e + ": При запуске указан неправильный аргумент!");
        }
    }
}
