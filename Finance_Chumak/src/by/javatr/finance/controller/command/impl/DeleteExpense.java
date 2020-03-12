package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Category;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeleteExpense implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute DeleteExpense command: ";
        String[] args = request.split("[$]");
        Integer id = Integer.parseInt(args[1]);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ExpenseService expenseService = serviceFactory.getExpenseService();
        String response;
        try {
            expenseService.delete(id);
            response = "Expense successfully deleted ";
        } catch (ServiceException ex){
            throw new CommandException(error + ex.getMessage(), ex);
        }
        return response;
    }
}
