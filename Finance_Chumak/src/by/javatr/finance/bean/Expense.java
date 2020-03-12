package by.javatr.finance.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Expense implements Serializable {

    private static final long serialVersionUID = -3179563363408300964L;

    private Integer id;
    private Integer idWallet;
    private Date date;
    private BigDecimal amount;
    private Category category;

    public Expense() {
    }

    public Expense(BigDecimal amount, Category category) {
        this.amount = amount;
        this.category = category;
    }

    public Expense(Integer idWallet, Date date,
                   BigDecimal amount, Category category) {
        this.idWallet = idWallet;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public Expense(Integer id, Integer idWallet, Date date,
                   BigDecimal amount, Category category) {
        this.id = id;
        this.idWallet = idWallet;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public Integer getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(Integer idWallet) {
        this.idWallet = idWallet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((idWallet == null) ? 0 : idWallet.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        return prime * result + ((category == null) ? 0 : category.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Expense other = (Expense) obj;
        return (id != null && other.getId() != null) ?
                id.equals(other.getId()) : id == other.getId() &&
                (idWallet != null && other.getIdWallet() != null) ?
                idWallet.equals(other.getIdWallet()) : idWallet == other.getIdWallet() &&
                (date != null && other.getDate() != null) ?
                date.equals(other.getDate()) : date == other.getDate() &&
                (amount != null && other.getAmount() != null) ?
                amount.equals(other.getAmount()) : amount == other.getAmount() &&
                (category != null && other.getCategory() != null) ?
                category.equals(other.getCategory()) : category == other.getCategory();
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + id + ", " +
                date + ", " + amount + ", " + category + ";";
    }
}