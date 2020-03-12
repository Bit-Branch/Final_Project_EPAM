package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Expense;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.controller.command.exception.CommandException;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.view.tableview.impl.ExpensesTableView;

import java.util.List;

public class AllExpenses implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String error = "Unable to execute AllExpenses command: ";
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ExpenseService expenseService = serviceFactory.getExpenseService();
        try {
            List<Expense> expenses =  expenseService.getAll();
            response = new ExpensesTableView().outputTable(expenses);
        } catch (ServiceException e) {
            String message = error + e.getMessage();
            throw new CommandException(message, e);
        }
        return response;
    }
}
