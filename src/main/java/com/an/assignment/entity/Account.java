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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hibernate_seq")
    @SequenceGenerator(name = "hibernate_seq", sequenceName = "hibernate_seq", initialValue = 1, allocationSize=1)
    private Long id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    private String name;
    private BigDecimal balance;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(customer, account.customer) && Objects.equals(name, account.name) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, name, balance);
    }
}
