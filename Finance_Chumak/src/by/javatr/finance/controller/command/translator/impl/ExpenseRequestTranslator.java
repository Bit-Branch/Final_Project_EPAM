package by.javatr.finance.controller.command.translator.impl;

import by.javatr.finance.bean.Category;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.controller.command.translator.RequestTranslator;
import by.javatr.finance.controller.command.translator.exception.RequestTranslationException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ExpenseRequestTranslator implements RequestTranslator<Expense> {
    private static final String AMOUNT = "AMOUNT";
    private static final String CATEGORY = "CATEGORY";
    private static final String WALLETID = "WALLETID";
    private static final String DATE = "DATE";

    @Override
    public Expense translateToObject(String request) throws RequestTranslationException {
        if (request == null) {
            throw new RequestTranslationException("Request has null value.");
        }
        Integer walledId = null;
        Date dateTime = null;
        BigDecimal amount = null;
        Category category = null;
        String[] args = request.split("[$&]");
        for (String arg : args) {
            int beginIndex = arg.indexOf('=');
            if (beginIndex > -1) {
                String argName = arg.substring(0, beginIndex);
                String argValue = arg.substring(beginIndex + 1);
                switch (argName.toUpperCase()) {
                    case WALLETID: {
                        walledId = Integer.parseInt(argValue);
                        break;
                    }
                    case DATE: {
                        SimpleDateFormat ft = new SimpleDateFormat (
                                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
                        try {
                            dateTime = ft.parse(argValue);
                        } catch (ParseException e) {
                            throw new RequestTranslationException(e.getMessage(),e);
                        }
                        break;
                    }
                    case AMOUNT:
                        amount = BigDecimal.valueOf(Double.parseDouble(argValue));
                        break;
                    case CATEGORY:
                        category = Category.valueOf(argValue);
                        break;
                }
            }
        }
        return new Expense(walledId,dateTime,amount, category);
    }

    @Override
    public Expense translate(String request) throws RequestTranslationException {
        if (request == null) {
            throw new RequestTranslationException("Request has null value.");
        }
        BigDecimal amount = null;
        Category category = null;
        String[] args = request.split("[$&]");
        for (String arg : args) {
            int beginIndex = arg.indexOf('=');
            if(beginIndex > -1) {
                String argName = arg.substring(0, beginIndex);
                String argValue = arg.substring(beginIndex + 1);
                switch (argName.toUpperCase()) {
                    case AMOUNT:
                        amount = BigDecimal.valueOf(Double.parseDouble(argValue));
                        break;
                    case CATEGORY:
                        category = Category.valueOf(argValue);
                        break;
                }
            }
        }
        return new Expense(amount, category);
    }


}
