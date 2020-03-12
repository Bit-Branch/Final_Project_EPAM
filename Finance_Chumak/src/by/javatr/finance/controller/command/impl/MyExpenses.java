package by.javatr.finance.controller.command.impl;

import by.javatr.finance.bean.Expense;
import by.javatr.finance.controller.command.Command;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.factory.ServiceFactory;
import by.javatr.finance.view.tableview.impl.ExpensesTableView;

import java.util.List;

public class MyExpenses implements Command {
    @Override
    public String execute(String request) {
        String[] args = request.split("[$]");
        Integer id = Integer.parseInt(args[1]);
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ExpenseService expenseService = serviceFactory.getExpenseService();
        try {
            List<Expense> expenses = expenseService.getAll(id);
            response = new ExpensesTableView().outputTable(expenses);
        } catch (ServiceException ex){
            response = "Error during getting all expenses";
        }
        return response;
    }
}
