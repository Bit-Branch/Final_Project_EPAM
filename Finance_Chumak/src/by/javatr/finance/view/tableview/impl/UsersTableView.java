package by.javatr.finance.view.tableview.impl;

import by.javatr.finance.bean.Category;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.bean.User;
import by.javatr.finance.bean.UserType;
import by.javatr.finance.view.tableview.TableView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class UsersTableView implements TableView<User> {
    @Override
    public String outputTable(List<User> users) {
        String sizes = "|%-10s|%-20s|%-15s|%-10s|%-15s|%-10s|%n";
        StringBuilder sb = new StringBuilder(String.format(sizes, "ID","Login", "Password", "FirstName", "LastName", "Type"));
        if (users != null) {
            for (User user : users) {
                Integer id = user.getId();
                String login = user.getLogin();
                String password = user.getPassword();
                String firstName = user.getFirstName();
                String lastName = user.getLastName();
                UserType userType = user.getUserType();
                sb.append(String.format(sizes, id, login, password, firstName, lastName, userType));
            }
        }
        return sb.toString();
    }
}
