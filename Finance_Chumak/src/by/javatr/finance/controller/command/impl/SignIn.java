package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.User;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.translator.exception.RequestTranslationException;
import by.javatr.finance.controller.command.translator.impl.UserRequestTranslator;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

public class SignIn implements Command {
    @Override
    public String execute(String request) {
        String response = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try {
            UserRequestTranslator userRequestTranslator = new UserRequestTranslator();
            User user = userRequestTranslator.translate(request);
            user = userService.signIn(user.getLogin(),user.getPassword());
            response = user.getId() + "," + user.getLogin() + "," + user.getPassword() + "," + user.getUserType();
        } catch (ServiceException | RequestTranslationException ex){
            response = "Error during sign in";
        }
        return response;
    }
}
