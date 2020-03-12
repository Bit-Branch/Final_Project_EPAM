package by.javatr.finance.service.factory;

import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.impl.ExpenseServiceImpl;
import by.javatr.finance.service.impl.UserServiceImpl;
import by.javatr.finance.service.impl.WalletServiceImpl;


public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();

    private final ExpenseService expenseService = new ExpenseServiceImpl();

    private final WalletService walletService = new WalletServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }

    public ExpenseService getExpenseService(){
        return expenseService;
    }

    public WalletService getWalletService(){
        return walletService;
    }

}
