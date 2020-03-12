package by.javatr.finance.controller.command.exception;

public class CommandException extends Exception{
    private static final long serialVersionUID = 3132493363408300964L;

    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Exception e) {
        super(e);
    }

    public CommandException(String message, Exception e) {
        super(message, e);
    }
}
