package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Category;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;

public class SelectExpense implements Command {
    @Override
    public String execute(String request) {
        String response;
        String[] args = request.split("[$]");
        Integer id = Integer.parseInt(args[1]);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ExpenseService expenseService = serviceFactory.getExpenseService();
        try {
            response = expenseService.getById(id).toString();
        } catch (ServiceException ex){
            response = "Error during selecting expense";
        }
        return response;
    }
}
