package by.javatr.finance.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 8734714214640688834L;

    public ServiceException(){
        super();
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Exception e){
        super(e);
    }

    public ServiceException(String message, Exception e){
        super(message,e);
    }
}
