package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.controller.command.translator.exception.RequestTranslationException;
import by.javatr.finance.controller.command.translator.impl.WalletRequestTranslator;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

import java.math.BigDecimal;

public class CreateWallet implements Command {

    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute CreateWallet command: ";
        WalletRequestTranslator walletRequestTranslator = new WalletRequestTranslator();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        WalletService walletService = serviceFactory.getWalletService();
        String response;
        try {
            Wallet wallet = walletRequestTranslator.translateToObject(request);
            walletService.create(wallet);
            response = "Wallet successfully added ";
        } catch (ServiceException | RequestTranslationException ex){
            throw new CommandException(error + ex.getMessage(), ex);
        }
        return response;
    }
}
