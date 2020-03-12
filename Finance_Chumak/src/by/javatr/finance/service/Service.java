package by.javatr.finance.service;

import by.javatr.finance.service.exception.ServiceException;

import java.util.List;

public interface Service<T> {
    List<T> getAll() throws ServiceException;
    T getById(Integer id) throws ServiceException;
    Integer create(T obj) throws ServiceException;
    void update(T obj) throws ServiceException;
    void delete(Integer id) throws ServiceException;
}
