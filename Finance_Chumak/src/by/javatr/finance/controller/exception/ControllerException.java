package by.javatr.finance.controller.exception;

public class ControllerException extends Exception {
    private static final long serialVersionUID = -2324234253463464678L;

    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Exception e) {
        super(e);
    }

    public ControllerException(String message, Exception e) {
        super(message, e);
    }
}
