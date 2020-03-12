package by.javatr.finance.service;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.service.exception.ServiceException;

import java.util.List;

public interface WalletService extends Service<Wallet> {
    List<Wallet> getAll(Integer idUser) throws ServiceException;
}
