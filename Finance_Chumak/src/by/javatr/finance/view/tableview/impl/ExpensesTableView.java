package by.javatr.finance.view.tableview.impl;

import by.javatr.finance.bean.Category;
import by.javatr.finance.bean.Expense;
import by.javatr.finance.bean.Wallet;
import by.javatr.finance.view.tableview.TableView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ExpensesTableView implements TableView<Expense> {

    @Override
    public String outputTable(List<Expense> expenses) {
        String sizes = "|%-10s|%-20s|%-30s|%-10s|%-15s|%n";
        StringBuilder sb = new StringBuilder(String.format(sizes, "ID","Wallet ID", "Date", "Amount", "Category"));
        if (expenses != null) {
            for (Expense expense : expenses) {
                Integer id = expense.getId();
                Integer walletId = expense.getIdWallet();
                Date date = expense.getDate();
                BigDecimal amount = expense.getAmount();
                Category category = expense.getCategory();
                sb.append(String.format(sizes, id, walletId, date, amount, category));
            }
        }
        return sb.toString();
    }
}
