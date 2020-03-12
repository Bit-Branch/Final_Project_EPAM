package by.javatr.finance.dao.validator;

public interface DAOValidator {
    default boolean isNull(Object object){
        return (object == null);
    }
}
