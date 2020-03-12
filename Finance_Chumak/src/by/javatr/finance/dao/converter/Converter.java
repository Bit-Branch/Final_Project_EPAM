package by.javatr.finance.dao.converter;
import by.javatr.finance.dao.converter.exception.ConverterException;
import by.javatr.finance.dao.validator.DAOValidator;

public interface Converter<T> extends DAOValidator {
    T getFromString(String str) throws ConverterException;
    String convertToString(T obj) throws ConverterException;
}
