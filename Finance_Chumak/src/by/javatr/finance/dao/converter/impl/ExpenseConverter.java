package by.javatr.finance.dao.converter.impl;



import by.javatr.finance.bean.Category;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.dao.converter.Converter;
import by.javatr.finance.dao.converter.exception.ConverterException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExpenseConverter implements Converter<Expense> {

    @Override
    public Expense getFromString(String str) throws ConverterException {
        if (isNull(str)){
            throw new ConverterException("Unable to get Expense from string: string has null value");
        }
        String[] splits = str.split("~");
        Integer id = Integer.valueOf(splits[0].replace("id: ",""));
        Integer idWallet = Integer.valueOf(splits[1].replace("idWallet: ", ""));
        SimpleDateFormat ft = new SimpleDateFormat (
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = ft.parse(splits[2].replace("date: ", ""));
        } catch (ParseException e) {
            throw new ConverterException(e.getMessage(),e);
        }
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(splits[3].replace("amount: ","")));
        Category category = Category.valueOf(splits[4].replace("category: ", ""));
        return new Expense(id,idWallet,date,amount,category);
    }

    @Override
    public String convertToString(Expense obj) throws ConverterException {
        if (isNull(obj)){
            throw new ConverterException("Unable to convert Expense to string: expense has null value");
        }
        return "id: " + obj.getId() + "~" +
                "idWallet: " + obj.getIdWallet() + "~" +
                "date: " + obj.getDate() + "~" +
                "amount: " + obj.getAmount() + "~" +
                "category: " + obj.getCategory() + "\n";
    }
}
