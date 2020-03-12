package by.javatr.finance.dao.factory;

import by.javatr.finance.dao.WalletDAO;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.UserDAO;
import by.javatr.finance.dao.converter.Converter;
import by.javatr.finance.dao.datasource.DataSource;
import by.javatr.finance.dao.impl.FileWalletDAO;
import by.javatr.finance.dao.impl.FileExpenseDAO;
import by.javatr.finance.dao.impl.FileUserDAO;


public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private final UserDAO userDAO = new FileUserDAO();
	private final WalletDAO walletDAO = new FileWalletDAO();
	private final ExpenseDAO expenseDAO = new FileExpenseDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public WalletDAO getWalletDAO() {
		return walletDAO;
	}

	public ExpenseDAO getExpenseDAO() {
		return expenseDAO;
	}
}