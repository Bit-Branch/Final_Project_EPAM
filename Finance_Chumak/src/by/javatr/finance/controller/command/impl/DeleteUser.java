package by.javatr.finance.controller.command.impl;

import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

public class DeleteUser implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute DeleteUser command: ";
        String[] args = request.split("[$]");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        Integer id = Integer.parseInt(args[1]);
        String response;
        try {
            userService.delete(id);
            response = "User successfully deleted ";
        } catch (ServiceException ex){
            throw new CommandException(error + ex.getMessage(), ex);
        }
        return response;
    }
}
