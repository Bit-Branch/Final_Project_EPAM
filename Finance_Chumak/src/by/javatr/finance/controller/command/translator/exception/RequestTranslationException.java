package by.javatr.finance.controller.command.translator.exception;

public class RequestTranslationException extends Exception{
    private static final long serialVersionUID = 1232132363408300964L;

    public RequestTranslationException() {
        super();
    }

    public RequestTranslationException(String message) {
        super(message);
    }

    public RequestTranslationException(Exception e) {
        super(e);
    }

    public RequestTranslationException(String message, Exception e) {
        super(message, e);
    }
}
