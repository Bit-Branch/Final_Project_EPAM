package by.javatr.finance.bean;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    FOOD(1),
    TRANSPORT(2),
    SHOPPING(3),
    COFFEE(4),
    CLOTHING(5),
    EXERCISE(6),
    EVENT(7),
    DATE(8);

    private static Map<Integer, Category> categoryMap = new HashMap<>();
    private int value;

    Category(int value) {
        this.value = value;
    }

    static {
        for (Category category : Category.values()) {
            categoryMap.put(category.value, category);
        }
    }

    public static Category valueOf(int categoryNumber) {
        return categoryMap.get(categoryNumber);
    }

    public int getValue() {
        return value;
    }

}
