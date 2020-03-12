package by.javatr.finance.view.tableview;

import java.util.List;

public interface TableView<T> {
    String outputTable(List<T> items);
}
