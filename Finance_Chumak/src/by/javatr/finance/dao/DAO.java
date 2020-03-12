package by.javatr.finance.dao;


import by.javatr.finance.dao.exception.DAOException;

import java.util.List;

public interface DAO<T>{
    List<T> getAll() throws DAOException;

    T getById(Integer id) throws DAOException;

    Integer add(T object) throws DAOException;

    void update(T object) throws DAOException;

    void delete(Integer id) throws DAOException;
}
