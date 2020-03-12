package by.javatr.finance.dao.converter.impl;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.dao.converter.Converter;
import by.javatr.finance.dao.converter.exception.ConverterException;

import java.math.BigDecimal;

public class WalletConverter implements Converter<Wallet> {
    @Override
    public Wallet getFromString(String str) throws ConverterException {
        if (isNull(str)){
            throw new ConverterException("Unable to get Wallet from string: string has null value");
        }
        String[] splits = str.split("~");
        Integer id = Integer.valueOf(splits[0].replace("id: ",""));
        Integer idUser = Integer.valueOf(splits[1].replace("idUser: ", ""));
        String name = splits[2].replace("name: ", "");
        BigDecimal budget = BigDecimal.valueOf(Double.parseDouble(splits[3].replace("budget: ","")));
        return new Wallet(id,idUser,name,budget);
    }

    @Override
    public String convertToString(Wallet obj) throws ConverterException {
        if (isNull(obj)){
            throw new ConverterException("Unable to convert Wallet to string: user has null value");
        }
        return "id: " + obj.getId() + "~" +
                "idUser: " + obj.getUserId() + "~" +
                "name: " + obj.getName() + "~" +
                "budget: " + obj.getBudget() + "\n";
    }
}
