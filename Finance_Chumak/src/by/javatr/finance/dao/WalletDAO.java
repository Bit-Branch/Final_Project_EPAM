package by.javatr.finance.dao;

import java.util.List;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.dao.exception.DAOException;

public interface WalletDAO extends DAO<Wallet>{
	List<Wallet> getAllByUserId(Integer idUser) throws DAOException;
}
