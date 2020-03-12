package by.javatr.finance.service;

import by.javatr.finance.bean.User;
import by.javatr.finance.service.exception.ServiceException;


public interface UserService extends Service<User> {
    User signIn(String login, String password) throws ServiceException;
    void register(User user) throws ServiceException;
}
