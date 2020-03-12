package by.javatr.finance.service.impl;

import by.javatr.finance.bean.User;
import by.javatr.finance.dao.UserDAO;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.dao.factory.DAOFactory;
import by.javatr.finance.service.UserService;
import by.javatr.finance.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public User signIn(String login, String password) throws ServiceException {
        if (login == null || login.isEmpty()){
            throw new ServiceException("invalid login");
        }
        if (password == null || password.isEmpty()){
            throw new ServiceException("invalid password");
        }
        try{
            return userDAO.signIn(login,password);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }


    @Override
    public void register(User user) throws ServiceException {
        if (user == null){
            throw new ServiceException("invalid password");
        }
        try{
            Integer id = getAll().size();
            user.setId(id);
            userDAO.register(user);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try{
            return userDAO.getAll();
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public User getById(Integer id) throws ServiceException {
        try{
            return userDAO.getById(id);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public Integer create(User obj) throws ServiceException {
        try{
            Integer id = getAll().size();
            obj.setId(id);
            return userDAO.add(obj);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public void update(User obj) throws ServiceException {
        try{
            userDAO.update(obj);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try{
            userDAO.delete(id);
        }catch (DAOException ex){
            throw new ServiceException();
        }
    }
}
