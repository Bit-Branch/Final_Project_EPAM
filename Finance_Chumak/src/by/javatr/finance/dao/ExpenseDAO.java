package by.javatr.finance.dao;

import java.util.List;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.dao.exception.DAOException;

public interface ExpenseDAO extends DAO<Expense>{
	List<Expense> getAllByWalletId(Integer idWallet) throws DAOException;
}
