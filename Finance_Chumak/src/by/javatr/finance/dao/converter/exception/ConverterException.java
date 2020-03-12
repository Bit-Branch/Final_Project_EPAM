package by.javatr.finance.dao.converter.exception;

public class ConverterException extends Exception {
    private static final long serialVersionUID = 1686383656347357345L;

    public ConverterException() {
        super();
    }

    public ConverterException(String message) {
        super(message);
    }

    public ConverterException(Exception e) {
        super(e);
    }

    public ConverterException(String message, Exception e) {
        super(message, e);
    }
}