package com.an.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hibernate_seq")
    @SequenceGenerator(name = "hibernate_seq", sequenceName = "hibernate_seq", initialValue = 1, allocationSize=1)
    private Long id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;
    private BigDecimal change;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(customer, that.customer) && Objects.equals(account, that.account) && Objects.equals(change, that.change);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, account, change);
    }
}
