package by.javatr.finance.controller.command;

import by.javatr.finance.controller.command.exception.CommandException;

public interface Command {
    String execute(String request) throws CommandException;
}
