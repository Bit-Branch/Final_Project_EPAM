package by.javatr.finance.dao;

import by.javatr.finance.bean.User;
import by.javatr.finance.dao.exception.DAOException;

public interface UserDAO extends DAO<User> {

	User signIn(String login, String password) throws DAOException;
	void register(User user) throws DAOException;
	boolean isLoginReserved(String login) throws DAOException;
}
