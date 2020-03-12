package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

import java.math.BigDecimal;

public class AddMoney implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute AddMoney command: ";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        WalletService walletService = serviceFactory.getWalletService();
        String response;
        Integer id = null;
        BigDecimal amount = null;
        String[] args = request.split("[$&]");
        for (String arg : args) {
            int beginIndex = arg.indexOf('=');
            if (beginIndex > -1) {
                String argName = arg.substring(0, beginIndex);
                String argValue = arg.substring(beginIndex + 1);
                switch (argName.toUpperCase()) {
                    case "ID":
                        id = Integer.parseInt(argValue);
                        break;
                    case "AMOUNT":
                        amount = BigDecimal.valueOf(Double.parseDouble(argValue));
                        break;
                }
            }
        }
        try {
            Wallet wallet = walletService.getById(id);
            wallet.setBudget(wallet.getBudget().add(amount));
            walletService.update(wallet);
            response = "Money successfully added ";
        } catch (ServiceException ex){
            throw new CommandException(error + ex.getMessage(), ex);
        }
        return response;
    }
}
