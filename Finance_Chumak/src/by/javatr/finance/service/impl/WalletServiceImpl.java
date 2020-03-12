package by.javatr.finance.service.impl;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.dao.WalletDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.dao.factory.DAOFactory;
import by.javatr.finance.service.WalletService;
import by.javatr.finance.service.exception.ServiceException;

import java.util.List;

public class WalletServiceImpl implements WalletService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private WalletDAO expenseDAO = daoFactory.getWalletDAO();

    @Override
    public List<Wallet> getAll(Integer idUser) throws ServiceException {
        try{
            return expenseDAO.getAllByUserId(idUser);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public List<Wallet> getAll() throws ServiceException {
        try{
            return expenseDAO.getAll();
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public Wallet getById(Integer id) throws ServiceException {
        try{
            return expenseDAO.getById(id);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public Integer create(Wallet obj) throws ServiceException {
        try{
            Integer id = getAll().size();
            obj.setId(id);
            return expenseDAO.add(obj);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public void update(Wallet obj) throws ServiceException {
        try{
            expenseDAO.update(obj);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try{
            expenseDAO.delete(id);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }
}
