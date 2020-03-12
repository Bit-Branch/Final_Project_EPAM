package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.User;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.view.tableview.impl.UsersTableView;

import java.util.List;

public class AllUsers implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute AllUsers command: ";
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try {
            List<User> users =  userService.getAll();
            response = new UsersTableView().outputTable(users);
        } catch (ServiceException e) {
            String message = error + e.getMessage();
            throw new CommandException(message, e);
        }
        return response;
    }
}
