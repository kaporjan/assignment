package com.an.assignment.web;

import com.an.assignment.domain.CreateTransactionRequest;
import com.an.assignment.domain.TransactionDetails;
import com.an.assignment.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer/{customerId}/account/{accountId}/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping
    Page<TransactionDetails> getTransaction(@PathVariable("customerId") Long customerId,
                                         @PathVariable("accountId") Long accountId, Pageable pageable) {
        return transactionService.getTransactions(customerId, accountId, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TransactionDetails createTransaction(@Valid @RequestBody CreateTransactionRequest createTransactionRequest) {
        return transactionService.createTransaction(createTransactionRequest);
    }

}
