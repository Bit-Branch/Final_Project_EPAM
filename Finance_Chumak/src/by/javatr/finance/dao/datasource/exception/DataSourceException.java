package by.javatr.finance.dao.datasource.exception;

public class DataSourceException extends Exception{
    private static final long serialVersionUID = 3253454326546567541L;

    public DataSourceException(){
        super();
    }

    public DataSourceException(String message){
        super(message);
    }

    public DataSourceException(Exception e){
        super(e);
    }

    public DataSourceException(String message, Exception e){
        super(message,e);
    }

}