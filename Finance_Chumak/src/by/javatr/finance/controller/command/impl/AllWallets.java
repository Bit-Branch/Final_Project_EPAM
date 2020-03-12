package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.view.tableview.impl.WalletTableView;

import java.util.List;

public class AllWallets implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute AllWallets command: ";
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        WalletService walletService = serviceFactory.getWalletService();
        try {
           List<Wallet> wallets =  walletService.getAll();
           response = new WalletTableView().outputTable(wallets);
        } catch (ServiceException e) {
            String message = error + e.getMessage();
            throw new CommandException(message, e);
        }
        return response;
    }
}
