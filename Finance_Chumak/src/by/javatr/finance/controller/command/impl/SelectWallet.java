package by.javatr.finance.controller.command.impl;

import by.javatr.finance.controller.command.Command;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

public class SelectWallet implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] args = request.split("[$]");
        Integer id = Integer.parseInt(args[1]);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        WalletService walletService = serviceFactory.getWalletService();
        try {
            response = walletService.getById(id).toString();
        } catch (ServiceException ex){
            response = "Error during selecting wallet";
        }
        return response;
    }
}
