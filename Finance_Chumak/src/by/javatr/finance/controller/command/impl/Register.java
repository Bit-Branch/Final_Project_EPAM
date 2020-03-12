package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.User;
import by.javatr.finance.bean.UserType;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.controller.command.translator.exception.RequestTranslationException;
import by.javatr.finance.controller.command.translator.impl.UserRequestTranslator;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

public class Register implements Command {

    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute Register command: ";
        UserRequestTranslator userRequestTranslator = new UserRequestTranslator();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        String response;
        try {
            User user = userRequestTranslator.translateToObject(request);
            userService.register(user);
            response = "Successfully registered ";
        } catch (ServiceException | RequestTranslationException ex){
            throw new CommandException(error + ex.getMessage(), ex);
        }
        return response;
    }
}
