package com.lambdaschool.money.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "expense")
public class Expense
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long expenseid;

    @Column(nullable = false)
    private String expensename;

    private int amount;

    private String category;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userExpenses")
    private User user;

    public Expense() {
    }

    public Expense(String expensename, int amount, String category, User user) {
        this.expensename = expensename;
        this.amount = amount;
        this.category = category;
        this.user = user;
    }

    public long getExpenseid() {
        return expenseid;
    }

    public void setExpenseid(long expenseid) {
        this.expenseid = expenseid;
    }

    public String getExpensename() {
        return expensename;
    }

    public void setExpensename(String expensename) {
        this.expensename = expensename;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Expense{" + "expenseid=" + expenseid + ", expensename='" + expensename + '\'' + ", amount=" + amount + ", category='" + category + '\'' + ", user=" + user + '}';
    }
}
