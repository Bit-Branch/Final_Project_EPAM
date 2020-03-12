package by.javatr.finance.controller.command.translator.impl;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.controller.command.translator.RequestTranslator;
import by.javatr.finance.controller.command.translator.exception.RequestTranslationException;

import java.math.BigDecimal;

public class WalletRequestTranslator implements RequestTranslator<Wallet> {
    private static final String NAME = "NAME";
    private static final String BUDGET = "BUDGET";
    private static final String USERID = "USERID";

    @Override
    public Wallet translate(String request) throws RequestTranslationException {
        if (request == null) {
            throw new RequestTranslationException("Request has null value.");
        }
        String name = null;
        BigDecimal budget = null;
        String[] args = request.split("[$&]");
        for (String arg : args) {
            int beginIndex = arg.indexOf('=');
            if (beginIndex > -1) {
                String argName = arg.substring(0, beginIndex);
                String argValue = arg.substring(beginIndex + 1);
                switch (argName.toUpperCase()) {
                    case NAME:
                        name = argValue;
                        break;
                    case BUDGET:
                        budget = BigDecimal.valueOf(Double.parseDouble(argValue));
                        break;
                }
            }
        }
        return new Wallet(name, budget);
    }

    @Override
    public Wallet translateToObject(String request) throws RequestTranslationException {
        if (request == null) {
            throw new RequestTranslationException("Request has null value.");
        }
        String name = null;
        BigDecimal budget = null;
        Integer userId = null;
        String[] args = request.split("[$&]");
        for (String arg : args) {
            int beginIndex = arg.indexOf('=');
            if (beginIndex > -1) {
                String argName = arg.substring(0, beginIndex);
                String argValue = arg.substring(beginIndex + 1);
                switch (argName.toUpperCase()) {
                    case USERID:{
                        userId = Integer.parseInt(argValue);
                        break;
                    }
                    case NAME:
                        name = argValue;
                        break;
                    case BUDGET:
                        budget = BigDecimal.valueOf(Double.parseDouble(argValue));
                        break;
                }
            }
        }
        return new Wallet(userId,name, budget);
    }
}
