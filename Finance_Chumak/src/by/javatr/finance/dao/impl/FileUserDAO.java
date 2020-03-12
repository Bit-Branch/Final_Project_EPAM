package by.javatr.finance.dao.impl;

import java.util.Iterator;
import java.util.List;

import by.javatr.finance.bean.User;
import by.javatr.finance.dao.converter.impl.UserConverter;
import by.javatr.finance.dao.UserDAO;
import by.javatr.finance.dao.datasource.DataSource;
import by.javatr.finance.dao.datasource.exception.DataSourceException;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.dao.validator.DAOValidator;


public class FileUserDAO implements UserDAO, DAOValidator {

	private DataSource<User> dataSource;
	private UserConverter userConverter;

	public FileUserDAO(){
		dataSource= new DataSource<>("src/resources/Users.txt");
		userConverter = new UserConverter();
	}

	public FileUserDAO(DataSource<User> dataSource, UserConverter userConverter){
		this.dataSource = dataSource;
		this.userConverter = userConverter;
	}

	public DataSource<User> getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource<User> dataSource) {
		this.dataSource = dataSource;
	}

	public UserConverter getUserConverter() {
		return userConverter;
	}

	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	@Override
	public User signIn(String login, String password) throws DAOException {
		User existingUser = null;
		List<User> users = getAll();
		for (User user : users
		) {
			if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
				existingUser = user;
				break;
			}
		}
		return existingUser;
	}


	@Override
	public void register(User user) throws DAOException {
			if (!isLoginReserved(user.getLogin())) {
				add(user);
			}
	}

	@Override
	public boolean isLoginReserved(String login) throws DAOException {
		boolean isReserved = false;
		List<User> users = getAll();
		for (User user : users
		) {
			if (user.getLogin().equals(login)) {
				isReserved = true;
				break;
			}
		}
		return isReserved;
	}

	@Override
	public List<User> getAll() throws DAOException {
		List<User> users;
		try {
			users = dataSource.read(userConverter);
		} catch (DataSourceException e) {
			String error = "Can not get all records in FileUserDAO: " + e.getMessage();
			throw new DAOException(error, e);
		}
		return users;
	}

	@Override
	public User getById(Integer id) throws DAOException {
		String error= "Unable to get user by id : ";
		if (isNull(id)){
			throw new DAOException(error + "id has null value");
		}
		User profile = null;
		List<User> users = getAll();
		for (User user : users
		) {
			if (user.getId().equals(id)) {
				profile = user;
				break;
			}
		}
		return profile;
	}

	@Override
	public Integer add(User object) throws DAOException {
		String error= "Unable to add user: ";
		if (isNull(object)){
			throw new DAOException(error + "user has null value");
		}
		try {
				dataSource.write(object, true, userConverter);
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
		return object.getId();
	}

	@Override
	public void update(User object) throws DAOException {
		String error= "Unable to update user: ";
		if (isNull(object)){
			throw new DAOException(error + "object has null value");
		}
		List<User> users = getAll();
		Iterator<User> userIterator = users.iterator();
		while(userIterator.hasNext()) {
			User nextUser = userIterator.next();
			if (nextUser.getId().equals(object.getId())) {
				nextUser.setUserType(object.getUserType());
				nextUser.setLogin(object.getLogin());
				nextUser.setPassword(object.getPassword());
				nextUser.setFirstName(object.getFirstName());
				nextUser.setLastName(object.getLastName());
			}
		}
		try {
			dataSource.write(users,userConverter);
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Integer id) throws DAOException {
		String error= "Unable to delete user: ";
		if (isNull(id)){
			throw new DAOException(error + "object has null value");
		}
		List<User> users = getAll();
		Iterator<User> userIterator = users.iterator();
		while(userIterator.hasNext()) {
			User nextUser = userIterator.next();
			if (nextUser.getId().equals(id)) {
				userIterator.remove();
			}
		}
		try {
			dataSource.write(users,userConverter);
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
	}
}