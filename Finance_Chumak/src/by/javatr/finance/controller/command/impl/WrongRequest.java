package by.javatr.finance.controller.command.impl;

import by.javatr.finance.controller.command.Command;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        String defaultResponse = "Wrong Request";
        return (request == null) ? defaultResponse : request;
    }
}
