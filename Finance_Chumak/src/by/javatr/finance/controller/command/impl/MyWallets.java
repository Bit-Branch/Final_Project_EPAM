package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.view.tableview.impl.WalletTableView;

import java.math.BigDecimal;
import java.util.List;

public class MyWallets implements Command {
    @Override
    public String execute(String request) {
        String response = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        WalletService walletService = serviceFactory.getWalletService();
        String[] args = request.split("[$]");
        Integer id = Integer.parseInt(args[1]);
        try {
            List<Wallet> wallets = walletService.getAll(id);
            response = new WalletTableView().outputTable(wallets);
        } catch (ServiceException ex){
            response = "Error during getting all wallets";
        }
        return response;
    }
}
