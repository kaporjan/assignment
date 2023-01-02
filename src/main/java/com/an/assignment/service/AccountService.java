package com.an.assignment.service;

import com.an.assignment.domain.AccountDetails;
import com.an.assignment.domain.CreateAccountRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    AccountDetails createAccount(CreateAccountRequest createAccountRequest);

    Page<AccountDetails> getAccounts(Long customerId, Pageable pageable);

    AccountDetails getAccount(Long customerId, Long accountId);

    void deleteAccount(Long id);
}
