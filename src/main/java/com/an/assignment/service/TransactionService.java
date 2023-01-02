package com.an.assignment.service;

import com.an.assignment.domain.CreateTransactionRequest;
import com.an.assignment.domain.TransactionDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    TransactionDetails createTransaction(CreateTransactionRequest createTransactionRequest);

    Page<TransactionDetails> getTransactions(Long customerId, Long accountId, Pageable pageable);

}
