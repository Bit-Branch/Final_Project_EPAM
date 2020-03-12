package by.javatr.finance.service.impl;

import by.javatr.finance.bean.Expense;
import by.javatr.finance.bean.Wallet;
import by.javatr.finance.dao.ExpenseDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.dao.factory.DAOFactory;
import by.javatr.finance.service.ExpenseService;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.exception.ServiceException;
import by.javatr.finance.service.validator.ServiceValidator;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService, ServiceValidator {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private ExpenseDAO expenseDAO = daoFactory.getExpenseDAO();

    @Override
    public List<Expense> getAll(Integer idWallet) throws ServiceException {
        try{
            return expenseDAO.getAllByWalletId(idWallet);
        }catch (DAOException ex){
            String error = "Can not get all records in ExpenseServiceImpl: " + ex.getMessage();
            throw new ServiceException(error);
        }
    }

    @Override
    public List<Expense> getAll() throws ServiceException {
        try{
            return expenseDAO.getAll();
        }catch (DAOException ex){
            String error = "Can not get all records in ExpenseServiceImpl: " + ex.getMessage();
            throw new ServiceException(error);
        }
    }

    @Override
    public Expense getById(Integer id) throws ServiceException {
        String error= "Unable to get expense by id : ";
        if (isNull(id)){
            throw new ServiceException(error + "id has null value");
        }
        try{
            return expenseDAO.getById(id);
        }catch (DAOException ex){
            throw new ServiceException(error + ex.getMessage());
        }
    }

    @Override
    public Integer create(Expense obj) throws ServiceException {
        String error= "Unable to add expense: ";
        if (isNull(obj)){
            throw new ServiceException(error + "expense has null value");
        }
        try{
            Integer id = getAll().size();
            obj.setId(id);
            WalletService walletService = new WalletServiceImpl();
            Wallet wallet = walletService.getById(obj.getIdWallet());
            wallet.setBudget(wallet.getBudget().add(obj.getAmount().negate()));
            walletService.update(wallet);
            return expenseDAO.add(obj);
        }catch (DAOException ex){
            throw new ServiceException(error + ex.getMessage());
        }
    }

    @Override
    public void update(Expense obj) throws ServiceException {
        String error= "Unable to update expense: ";
        if (isNull(obj)){
            throw new ServiceException(error + "object has null value");
        }
        try{
            expenseDAO.update(obj);
        }catch (DAOException ex){
            throw new ServiceException(error + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        String error= "Unable to delete expense: ";
        if (isNull(id)){
            throw new ServiceException(error + "object has null value");
        }
        try{
            expenseDAO.delete(id);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }
}
