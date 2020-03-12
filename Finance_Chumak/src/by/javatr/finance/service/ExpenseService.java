package by.javatr.finance.service;

import by.javatr.finance.bean.Expense;
import by.javatr.finance.service.exception.ServiceException;

import java.util.List;

public interface ExpenseService extends Service<Expense> {
    List<Expense> getAll(Integer idWallet) throws ServiceException;
}
