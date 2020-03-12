package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.controller.command.translator.impl.WalletRequestTranslator;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

import java.math.BigDecimal;

public class DeleteWallet implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute DeleteWallet command: ";
        String[] args = request.split("[$]");
        Integer id = Integer.parseInt(args[1]);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        WalletService walletService = serviceFactory.getWalletService();
        String response;
        try {
            walletService.delete(id);
            response = "Wallet successfully deleted ";
        } catch (ServiceException ex){
            throw new CommandException(error + ex.getMessage(), ex);
        }
        return response;
    }
}
