package com.an.assignment.service;

import com.an.assignment.domain.CreateTransactionRequest;
import com.an.assignment.domain.TransactionDetails;
import com.an.assignment.entity.Account;
import com.an.assignment.entity.Customer;
import com.an.assignment.entity.Transaction;
import com.an.assignment.mapper.TransactionMapper;
import com.an.assignment.repository.AccountRepository;
import com.an.assignment.repository.CustomerRepository;
import com.an.assignment.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDetails createTransaction(CreateTransactionRequest createTransactionRequest) {
        Customer customer = customerRepository.getReferenceById(createTransactionRequest.getCustomerId());
        Account account = accountRepository.getReferenceById(createTransactionRequest.getAccountId());
        Transaction saved = transactionRepository.save(transactionMapper.map(customer, account, createTransactionRequest.getChange()));

        return transactionMapper.map(saved);
    }

    @Override
    public Page<TransactionDetails> getTransactions(Long customerId, Long accountId, Pageable pageable) {
        return transactionRepository.findByCustomerIdAndAccountId(customerId, accountId, pageable)
                .map(transactionMapper::map);
    }
}
