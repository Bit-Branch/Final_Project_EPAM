package by.javatr.finance.controller.command;

import by.javatr.finance.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<CommandName,Command> repository = new HashMap<>();

    public CommandProvider(){
        repository.put(CommandName.SIGN_IN,new SignIn());
        repository.put(CommandName.CREATE_EXPENSE,new CreateExpense());
        repository.put(CommandName.CREATE_WALLET, new CreateWallet());
        repository.put(CommandName.DELETE_EXPENSE, new DeleteExpense());
        repository.put(CommandName.DELETE_WALLET, new DeleteWallet());
        repository.put(CommandName.MY_EXPENSES, new MyExpenses());
        repository.put(CommandName.MY_WALLETS, new MyWallets());
        repository.put(CommandName.REGISTER, new Register());
        repository.put(CommandName.SELECT_EXPENSE, new SelectExpense());
        repository.put(CommandName.SELECT_WALLET, new SelectWallet());
        repository.put(CommandName.ALL_EXPENSES, new AllExpenses());
        repository.put(CommandName.ALL_WALLETS, new AllWallets());
        repository.put(CommandName.ADD_MONEY, new AddMoney());
        repository.put(CommandName.ALL_USERS, new AllUsers());
        repository.put(CommandName.DELETE_USER, new DeleteUser());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch (IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
