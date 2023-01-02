package com.an.assignment.mapper;

import com.an.assignment.domain.TransactionDetails;
import com.an.assignment.entity.Account;
import com.an.assignment.entity.Customer;
import com.an.assignment.entity.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionMapper {
    public Transaction map(Customer customer, Account account, BigDecimal change) {
        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setAccount(account);
        transaction.setChange(change);
        return transaction;
    }

    public TransactionDetails map(Transaction transaction) {
        return TransactionDetails.builder()
                .id(transaction.getId())
                .customerId(transaction.getCustomer().getId())
                .accountId(transaction.getAccount().getId())
                .change(transaction.getChange())
                .build();
    }
}
