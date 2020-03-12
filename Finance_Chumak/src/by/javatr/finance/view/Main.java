package by.javatr.finance.view;

import by.javatr.finance.bean.Category;
import by.javatr.finance.bean.User;
import by.javatr.finance.bean.UserType;
import by.javatr.finance.controller.Controller;
import by.javatr.finance.controller.command.CommandName;
import by.javatr.finance.controller.exception.ControllerException;
import by.javatr.finance.view.scanner.DataScanner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.InputMismatchException;

public class Main {

    private static User currentUser = new User();
    private static Controller controller = new Controller();

    public static void main(String[] args) {
        try {
            showLoginForm();
        } catch (InputMismatchException e){
            System.out.println(e.getMessage());
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }

    private static void showLoginForm() throws ControllerException {
        String response;
        while (true) {
            System.out.println("-------------------------");
            System.out.println("1 - Log in");
            System.out.println("2 - Register");
            System.out.println("0 - Quit");
            System.out.println("-------------------------\n");

            switch (DataScanner.enterInt()) {
                case 1: {
                    response = signIn();
                    if (response.equals("Error during sign in")){
                        System.out.println(response);
                    } else{
                        String[] data = response.split(",");
                        currentUser.setId(Integer.parseInt(data[0]));
                        currentUser.setLogin(data[1]);
                        currentUser.setPassword(data[2]);
                        currentUser.setUserType(UserType.valueOf(data[3]));
                        if (currentUser.getUserType() == UserType.USER){
                            showUserMainMenu();
                        }
                        else{
                            showAdminMainMenu();
                        }
                    }

                    break;
                }
                case 2: {
                    response = signUp();
                    if (!response.equals(CommandName.WRONG_REQUEST.name())){
                        System.out.println("You successfully registered!");
                    }
                    else {
                        System.out.println("Registration error");
                    }
                    break;
                }
                case 0: {
                    System.out.println("Leaving the program...");
                    System.exit(0);
                    break;
                }
            }
        }
    }

    private static String signIn() throws ControllerException {
        System.out.println("Enter login: ");
        String login = DataScanner.enterString();
        System.out.println("Enter password: ");
        String password = DataScanner.enterString();
        String command = CommandName.SIGN_IN.name();
        String request = String.format("%s$LOGIN=%s&PASSWORD=%s", command, login, password);
        return controller.executeTask(request);
    }

    private static String signUp() throws ControllerException {
        System.out.println("Enter login: ");
        String login = DataScanner.enterString();
        System.out.println("Enter password: ");
        String password = DataScanner.enterString();
        String command = CommandName.REGISTER.name();
        System.out.println("Enter firstName: ");
        String firstName = DataScanner.enterString();
        System.out.println("Enter lastName: ");
        String lastName = DataScanner.enterString();
        String request = String.format("%s$LOGIN=%s&PASSWORD=%s&FIRSTNAME=%s&LASTNAME=%s&USERTYPE=%s", command, login, password,firstName,lastName,UserType.USER);
        return controller.executeTask(request);
    }


    private static void showAdminMainMenu() throws ControllerException {
        while (true) {
            System.out.println("-------------------------");
            System.out.println("1 - All wallets");
            System.out.println("2 - All users");
            System.out.println("3 - Delete wallet");
            System.out.println("4 - Delete user");
            System.out.println("5 - Delete expense");
            System.out.println("0 - Quit");
            System.out.println("-------------------------\n");

            switch (DataScanner.enterInt()) {
                case 1: {
                    String request = String.format("%s$", CommandName.ALL_WALLETS);
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 2: {
                    String request = String.format("%s$", CommandName.ALL_USERS);
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 3: {
                    String request = String.format("%s$", CommandName.ALL_WALLETS);
                    String response = controller.executeTask(request);
                    System.out.println(response);
                    System.out.println("Enter id of wallet: ");
                    Integer walletId = DataScanner.enterInt();
                    request = String.format("%s$%s", CommandName.DELETE_WALLET, walletId);
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 4: {
                    String request = String.format("%s$", CommandName.ALL_USERS);
                    String response = controller.executeTask(request);
                    System.out.println(response);
                    System.out.println("Enter id of user: ");
                    Integer userId = DataScanner.enterInt();
                    request = String.format("%s$%s", CommandName.DELETE_USER, userId);
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 5:{
                    String request = String.format("%s$", CommandName.ALL_WALLETS);
                    String response = controller.executeTask(request);
                    System.out.println(response);
                    System.out.println("Enter id of wallet: ");
                    Integer walletId = DataScanner.enterInt();
                    Integer expenseId = chooseExpense(walletId);
                    request = String.format("%s$%s", CommandName.DELETE_EXPENSE, expenseId);
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 0: {
                    System.out.println("Leaving the program...");
                    System.exit(0);
                    break;
                }
            }
        }
    }

    private static void showUserMainMenu() throws ControllerException {
        while (true) {
            System.out.println("-------------------------");
            System.out.println("1 - My wallets");
            System.out.println("2 - Add wallet");
            System.out.println("3 - Add money to the wallet");
            System.out.println("4 - Delete wallet");
            System.out.println("5 - My expenses");
            System.out.println("6 - Add expense");
            System.out.println("7 - Delete expense");
            System.out.println("0 - Quit");
            System.out.println("-------------------------\n");

            switch (DataScanner.enterInt()) {
                case 1: {
                    String request = String.format("%s$%s", CommandName.MY_WALLETS, currentUser.getId());
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 2: {
                    addWallet();
                    break;
                }
                case 3:{
                    Integer walletId = chooseWallet();
                    System.out.println("Enter a sum to fill the wallet: ");
                    BigDecimal amount = DataScanner.enterBigDecimal();
                    String request = String.format("%s$ID=%s&AMOUNT=%s", CommandName.ADD_MONEY, walletId,amount);
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 4:{
                    Integer walletId = chooseWallet();
                    String request = String.format("%s$%s", CommandName.DELETE_WALLET, walletId);
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 5: {
                    Integer walletId = chooseWallet();
                    String request = String.format("%s$%s", CommandName.MY_EXPENSES, walletId);
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 6: {
                    addExpense();
                    break;
                }
                case 7:{
                    Integer walletId = chooseWallet();
                    Integer expenseId = chooseExpense(walletId);
                    String request = String.format("%s$%s", CommandName.DELETE_EXPENSE, expenseId);
                    System.out.println(controller.executeTask(request));
                    break;
                }
                case 0: {
                    System.out.println("Leaving the program...");
                    System.exit(0);
                    break;
                }
            }
        }
    }


    private static Category pickExpenseCategory() {
        System.out.println("Choose category of expense: ");
        System.out.println("1.FOOD");
        System.out.println("2.TRANSPORT");
        System.out.println("3.SHOPPING");
        System.out.println("4.COFFEE");
        System.out.println("5.CLOTHING");
        System.out.println("6.EXERCISE");
        System.out.println("7.EVENT");
        System.out.println("8.DATE");

        Category category = null;
        boolean flag;
        do {
            flag = false;
            switch (DataScanner.enterInt()) {
                case 1: {
                    category = Category.FOOD;
                    break;
                }
                case 2: {
                    category = Category.TRANSPORT;
                    break;
                }
                case 3: {
                    category = Category.SHOPPING;
                    break;
                }
                case 4: {
                    category = Category.COFFEE;
                    break;
                }
                case 5: {
                    category = Category.CLOTHING;
                    break;
                }
                case 6: {
                    category = Category.EXERCISE;
                    break;
                }
                case 7:{
                    category = Category.EVENT;
                    break;
                }
                case 8:{
                    category = Category.DATE;
                    break;
                }
                default: {
                    flag = true;
                    break;
                }
            }
        } while (flag);
        return category;
    }

    private static String addWallet() throws ControllerException {
        System.out.println("Enter wallet name:");
        String name = DataScanner.enterString();
        System.out.println("Enter a budget size:");
        BigDecimal budget = DataScanner.enterBigDecimal();
        String command = CommandName.CREATE_WALLET.name();
        String request = String.format("%s$NAME=%s&BUDGET=%s&USERID=%s", command, name, budget,currentUser.getId());
        return controller.executeTask(request);
    }

    private static String addExpense() throws ControllerException{
        System.out.println("Enter expense cost: ");
        BigDecimal cost = DataScanner.enterBigDecimal();
        String category = pickExpenseCategory().name();
        System.out.println("Enter the date and time (in format yyyy-MM-dd)");
        Date dateTime = DataScanner.enterDate();
        Integer walletId = chooseWallet();
        String request = String.format("%s$WALLETID=%s&AMOUNT=%s&CATEGORY=%s&DATE=%s", CommandName.CREATE_EXPENSE, walletId, cost, category, dateTime);
        return controller.executeTask(request);
    }

    private static Integer chooseWallet() throws ControllerException {
        String request = String.format("%s$%s", CommandName.MY_WALLETS, currentUser.getId());
        String response = controller.executeTask(request);
        System.out.println(response);
        System.out.println("Enter id of wallet: ");
        Integer walletId = DataScanner.enterInt();
        return walletId;
    }

    private static Integer chooseExpense(Integer walletId) throws ControllerException{
        String request = String.format("%s$%s", CommandName.MY_EXPENSES, walletId);
        String response = controller.executeTask(request);
        System.out.println(response);
        System.out.println("Enter id of expense: ");
        Integer expenseId = DataScanner.enterInt();
        return expenseId;
    }
}
