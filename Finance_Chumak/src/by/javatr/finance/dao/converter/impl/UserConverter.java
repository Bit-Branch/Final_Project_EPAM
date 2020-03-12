package by.javatr.finance.dao.converter.impl;

import by.javatr.finance.bean.User;
import by.javatr.finance.bean.UserType;
import by.javatr.finance.dao.converter.Converter;
import by.javatr.finance.dao.converter.exception.ConverterException;


public class UserConverter implements Converter<User> {

    @Override
    public User getFromString(String str) throws ConverterException {
        if (isNull(str)){
            throw new ConverterException("Unable to get User from string: string has null value");
        }
        String[] splits = str.split("~");
        Integer id = Integer.valueOf(splits[0].replace("id: ",""));
        String login = splits[1].replace("login: ", "");
        String password = splits[2].replace("password: ", "");
        String firstName = splits[3].replace("firstName: ","");
        String lastName = splits[4].replace("lastName: ", "");
        UserType userType = UserType.valueOf(splits[5].replace("userType: ",""));
        return new User(id,login,password,firstName,lastName,userType);
    }

    @Override
    public String convertToString(User obj) throws ConverterException {
        if (isNull(obj)){
            throw new ConverterException("Unable to convert User to string: user has null value");
        }
        return  "id: " + obj.getId() + "~" +
                "login: " + obj.getLogin() + "~" +
                "password: " + obj.getPassword() + "~" +
                "firstName: " + obj.getFirstName() + "~" +
                "lastName: " + obj.getLastName() + "~" +
                "userType: " + obj.getUserType() + "\n";
    }
}
