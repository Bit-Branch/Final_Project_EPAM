package by.javatr.finance.controller.command.translator.impl;

import by.javatr.finance.bean.User;
import by.javatr.finance.bean.UserType;
import by.javatr.finance.controller.command.translator.RequestTranslator;
import by.javatr.finance.controller.command.translator.exception.RequestTranslationException;

public class UserRequestTranslator implements RequestTranslator<User> {

    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String FIRSTNAME = "FIRSTNAME";
    private static final String LASTNAME = "LASTNAME";
    private static final String USERTYPE = "USERTYPE";

    @Override
    public User translate(String request) throws RequestTranslationException {
        if (request == null) {
            throw new RequestTranslationException("Request has null value.");
        }

        String login = null;
        String password = null;
        String[] args = request.split("[$&]");
        for (String arg : args) {
            int beginIndex = arg.indexOf('=');
            if (beginIndex > -1){
            String argName = arg.substring(0, beginIndex);
            String argValue = arg.substring(beginIndex + 1);
            switch (argName.toUpperCase()) {
                case LOGIN:
                    login = argValue;
                    break;
                case PASSWORD:
                    password = argValue;
                    break;
                }
            }
        }
        return new User(login, password);
    }

    @Override
    public User translateToObject(String request) throws RequestTranslationException {
        if (request == null) {
            throw new RequestTranslationException("Request has null value.");
        }
        String login = null;
        String password = null;
        String firstName = null;
        String lastName = null;
        UserType userType = null;
        String[] args = request.split("[$&]");
        for (String arg : args) {
            int beginIndex = arg.indexOf('=');
            if (beginIndex > -1){
                String argName = arg.substring(0, beginIndex);
                String argValue = arg.substring(beginIndex + 1);
                switch (argName.toUpperCase()) {
                    case FIRSTNAME:{
                        firstName = argValue;
                        break;
                    }
                    case LASTNAME:{
                        lastName = argValue;
                        break;
                    }
                    case USERTYPE:{
                        userType = UserType.valueOf(argValue);
                        break;
                    }
                    case LOGIN:
                        login = argValue;
                        break;
                    case PASSWORD:
                        password = argValue;
                        break;
                }
            }
        }
        return new User(login, password,firstName,lastName,userType);
    }
}
