package com.an.assignment.service;

import com.an.assignment.domain.AccountDetails;
import com.an.assignment.domain.CreateAccountRequest;
import com.an.assignment.entity.Account;
import com.an.assignment.entity.Customer;
import com.an.assignment.entity.Transaction;
import com.an.assignment.exception.NotFoundException;
import com.an.assignment.mapper.AccountMapper;
import com.an.assignment.repository.AccountRepository;
import com.an.assignment.repository.CustomerRepository;
import com.an.assignment.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository repository, CustomerRepository customerRepository, TransactionRepository transactionRepository, AccountMapper accountMapper) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDetails createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerRepository.getReferenceById(createAccountRequest.getCustomerId());
        Account account = repository.save(accountMapper.map(customer, createAccountRequest));
        saveInitialBalance(createAccountRequest, customer, account);
        return accountMapper.map(account, createAccountRequest.getBalance());
    }

    private void saveInitialBalance(CreateAccountRequest createAccountRequest, Customer customer, Account account) {
        if (!BigDecimal.ZERO.equals(createAccountRequest.getBalance())) {
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setCustomer(customer);
            transaction.setChange(createAccountRequest.getBalance());
            transactionRepository.save(transaction);
        }
    }

    @Override
    public Page<AccountDetails> getAccounts(Long customerId, Pageable pageable) {
        return repository.findByCustomerId(customerId, pageable)
                .map(a -> {
                    BigDecimal sum = transactionRepository.sumOfChangesForAccount(customerId, a.getId());
                    return accountMapper.map(a, sum);
                });
    }

    @Override
    public AccountDetails getAccount(Long customerId, Long accountId) {
        return repository.findByCustomerIdAndId(customerId, accountId)
                .map(a -> {
                    BigDecimal sum = transactionRepository.sumOfChangesForAccount(customerId, accountId);
                    return accountMapper.map(a, sum);
                }
                )
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }
}
