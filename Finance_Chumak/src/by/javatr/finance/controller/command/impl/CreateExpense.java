package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Category;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.bean.User;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.controller.command.translator.exception.RequestTranslationException;
import by.javatr.finance.controller.command.translator.impl.ExpenseRequestTranslator;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateExpense implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute CreateExpense command: ";
        ExpenseRequestTranslator expenseRequestTranslator = new ExpenseRequestTranslator();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ExpenseService expenseService = serviceFactory.getExpenseService();
        String response;
        try {
            Expense expense = expenseRequestTranslator.translateToObject(request);
            expenseService.create(expense);
            response = "Expense successfully added ";
        } catch (ServiceException | RequestTranslationException ex){
            throw new CommandException(error + ex.getMessage(), ex);
        }
        return response;
    }
}
