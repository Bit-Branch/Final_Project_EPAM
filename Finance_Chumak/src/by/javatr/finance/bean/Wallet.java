package by.javatr.finance.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Wallet implements Serializable {

    private static final long serialVersionUID = -3179563363404580964L;

    private Integer id;
    private Integer userId;
    private String name;
    private BigDecimal budget;

    public Wallet() {
    }

    public Wallet(String name, BigDecimal budget) {
        this.name = name;
        this.budget = budget;
    }

    public Wallet(Integer userId, String name, BigDecimal budget) {
        this.userId = userId;
        this.name = name;
        this.budget = budget;
    }

    public Wallet(Integer id, Integer userId, String name, BigDecimal budget) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.budget = budget;
    }

    public Wallet(Integer id, BigDecimal budget) {
        this.id = id;
        this.budget = budget;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return prime * result + ((budget == null) ? 0 : budget.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Wallet other = (Wallet) obj;
        return (id != null && other.getId() != null) ?
                id.equals(other.getId()) : id == other.getId() &&
                (userId != null && other.getUserId() != null) ?
                userId.equals(other.getUserId()) : userId == other.getUserId() &&
                (name != null && other.getName() != null) ?
                name.equals(other.getName()) : name == other.getName() &&
                (budget != null && other.getBudget() != null) ?
                budget.equals(other.getBudget()) : budget == other.getBudget();
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + id + ", " + budget;
    }
}