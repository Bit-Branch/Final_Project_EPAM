package by.javatr.finance.dao.impl;

import by.javatr.finance.bean.Expense;
import by.javatr.finance.dao.converter.impl.ExpenseConverter;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.datasource.DataSource;
import by.javatr.finance.dao.datasource.exception.DataSourceException;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.dao.validator.DAOValidator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class FileExpenseDAO implements ExpenseDAO, DAOValidator {

	private DataSource<Expense> dataSource ;
	private ExpenseConverter expenseConverter;

	public FileExpenseDAO(){
		dataSource = new DataSource<>("src/resources/Expenses.txt");
		expenseConverter = new ExpenseConverter();
	}

	public FileExpenseDAO(DataSource<Expense> dataSource, ExpenseConverter expenseConverter){
		this.dataSource = dataSource;
		this.expenseConverter = expenseConverter;
	}

	public DataSource<Expense> getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource<Expense> dataSource) {
		this.dataSource = dataSource;
	}

	public ExpenseConverter getExpenseConverter() {
		return expenseConverter;
	}

	public void setExpenseConverter(ExpenseConverter expenseConverter) {
		this.expenseConverter = expenseConverter;
	}

	@Override
	public List<Expense> getAll() throws DAOException {
		List<Expense> expenses;
		try {
			expenses = dataSource.read(expenseConverter);
		} catch (DataSourceException e) {
			String error = "Can not get all records in FileExpenseDAO: " + e.getMessage();
			throw new DAOException(error, e);
		}
		return expenses;
	}

	@Override
	public Expense getById(Integer id) throws DAOException {
		String error= "Unable to get expense by id : ";
		if (isNull(id)){
			throw new DAOException(error + "id has null value");
		}
		Expense profile = null;
		List<Expense> expenses = getAll();
		for (Expense expense : expenses
		) {
			if (expense.getId().equals(id)) {
				profile = expense;
				break;
			}
		}
		return profile;
	}

	@Override
	public Integer add(Expense expense) throws DAOException {
		String error= "Unable to add expense: ";
		if (isNull(expense)){
			throw new DAOException(error + "expense has null value");
		}
		try {
			dataSource.write(expense,true,expenseConverter);
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
		return expense.getId();
	}

	@Override
	public void update(Expense object) throws DAOException {
		String error= "Unable to update expense: ";
		if (isNull(object)){
			throw new DAOException(error + "object has null value");
		}
		List<Expense> expenses = getAll();
		Iterator<Expense> expenseIterator = expenses.iterator();
		while(expenseIterator.hasNext()) {
			Expense nextExpense = expenseIterator.next();
			if (nextExpense.getId().equals(object.getId())) {
				nextExpense.setIdWallet(object.getIdWallet());
				nextExpense.setDate(object.getDate());
				nextExpense.setCategory(object.getCategory());
				nextExpense.setAmount(object.getAmount());
			}
		}
		try {
			dataSource.write(expenses,expenseConverter);
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Integer id) throws DAOException {
		String error= "Unable to delete expense: ";
		if (isNull(id)){
			throw new DAOException(error + "object has null value");
		}
		List<Expense> expenses = getAll();
		Iterator<Expense> expenseIterator = expenses.iterator();
		while(expenseIterator.hasNext()) {
			Expense nextExpense = expenseIterator.next();
			if (nextExpense.getId().equals(id)) {
				expenseIterator.remove();
			}
		}
		try {
			dataSource.write(expenses,expenseConverter);
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
	}

	@Override
	public List<Expense> getAllByWalletId(Integer idWallet) throws DAOException {
		String error= "Unable to get expenses by id: ";
		if (isNull(idWallet)){
			throw new DAOException(error + "idWallet has null value");
		}
		List<Expense> dataSourceExpenseList;
		List<Expense> expenseList = new ArrayList<>();
		try{
			dataSourceExpenseList = dataSource.read(expenseConverter);
			for (Expense expense:dataSourceExpenseList
			) {
				if (idWallet.equals(expense.getIdWallet())){
					expenseList.add(expense);
				}
			}
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
		return expenseList;
	}
}