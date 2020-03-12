package by.javatr.finance.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.dao.converter.impl.WalletConverter;
import by.javatr.finance.dao.WalletDAO;
import by.javatr.finance.dao.datasource.DataSource;
import by.javatr.finance.dao.datasource.exception.DataSourceException;
import by.javatr.finance.dao.exception.DAOException;
import by.javatr.finance.dao.validator.DAOValidator;


public class FileWalletDAO implements WalletDAO, DAOValidator {

	private DataSource<Wallet> dataSource;
	private WalletConverter walletConverter;

	public FileWalletDAO(){
		dataSource = new DataSource<>("src/resources/Wallets.txt");
		walletConverter = new WalletConverter();
	}

	public FileWalletDAO(DataSource<Wallet> dataSource, WalletConverter walletConverter){
		this.dataSource = dataSource;
		this.walletConverter = walletConverter;
	}

	public DataSource<Wallet> getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource<Wallet> dataSource) {
		this.dataSource = dataSource;
	}

	public WalletConverter getWalletConverter() {
		return walletConverter;
	}

	public void setWalletConverter(WalletConverter walletConverter) {
		this.walletConverter = walletConverter;
	}

	@Override
	public List<Wallet> getAll() throws DAOException {
		List<Wallet> wallets;
		try {
			wallets = dataSource.read(walletConverter);
		} catch (DataSourceException e) {
			String error = "Can not get all records in FileWalletDAO: " + e.getMessage();
			throw new DAOException(error, e);
		}
		return wallets;
	}

	@Override
	public Wallet getById(Integer id) throws DAOException {
		String error= "Unable to get wallet by id : ";
		if (isNull(id)){
			throw new DAOException(error + "id has null value");
		}
		Wallet profile = null;
		List<Wallet> wallets = getAll();
		for (Wallet wallet : wallets
		) {
			if (wallet.getId().equals(id)) {
				profile = wallet;
				break;
			}
		}
		return profile;
	}

	@Override
	public Integer add(Wallet wallet) throws DAOException {
		String error= "Unable to add wallet: ";
		if (isNull(wallet)){
			throw new DAOException(error + "wallet has null value");
		}
		try {
			dataSource.write(wallet,true,walletConverter);
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
		return wallet.getId();
	}

	@Override
	public void update(Wallet object) throws DAOException {
		String error= "Unable to update wallet: ";
		if (isNull(object)){
			throw new DAOException(error + "object has null value");
		}
		List<Wallet> wallets = getAll();
		Iterator<Wallet> walletIterator = wallets.iterator();
		while(walletIterator.hasNext()) {
			Wallet nextWallet = walletIterator.next();
			if (nextWallet.getId().equals(object.getId())) {
				nextWallet.setBudget(object.getBudget());
			}
		}
		try {
			dataSource.write(wallets,walletConverter);
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Integer id) throws DAOException {
		String error= "Unable to delete wallet: ";
		if (isNull(id)){
			throw new DAOException(error + "object has null value");
		}
		List<Wallet> wallets = getAll();
		Iterator<Wallet> walletIterator = wallets.iterator();
		while(walletIterator.hasNext()) {
			Wallet nextWallet = walletIterator.next();
			if (nextWallet.getId().equals(id)) {
				walletIterator.remove();
			}
		}
		try {
			dataSource.write(wallets,walletConverter);
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
	}

	@Override
	public List<Wallet> getAllByUserId(Integer idUser) throws DAOException {
		String error= "Unable to get wallets by id: ";
		if (isNull(idUser)){
			throw new DAOException(error + "idUser has null value");
		}
		List<Wallet> dataSourceWalletList;
		List<Wallet> walletList = new ArrayList<>();
		try{
			dataSourceWalletList = dataSource.read(walletConverter);
			for (Wallet wallet:dataSourceWalletList
			) {
				if (idUser.equals(wallet.getUserId())){
					walletList.add(wallet);
				}
			}
		} catch (DataSourceException e) {
			throw new DAOException(error + e.getMessage(), e);
		}
		return walletList;
	}
}