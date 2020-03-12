package by.javatr.finance.view.tableview.impl;

import by.javatr.finance.bean.Wallet;
import by.javatr.finance.view.tableview.TableView;

import java.math.BigDecimal;
import java.util.List;

public class WalletTableView implements TableView<Wallet> {
    @Override
    public String outputTable(List<Wallet> wallets) {
        String sizes = "|%-10s|%-20s|%-15s|%-10s|%n";
        StringBuilder sb = new StringBuilder(String.format(sizes, "ID","Name", "User ID", "Balance"));

        if (wallets != null) {
            for (Wallet wallet : wallets) {
                Integer id = wallet.getId();
                String name = wallet.getName();
                Integer userId = wallet.getUserId();
                BigDecimal budget = wallet.getBudget();
                sb.append(String.format(sizes, id, name, userId, budget));
            }
        }
        return sb.toString();
    }
}
