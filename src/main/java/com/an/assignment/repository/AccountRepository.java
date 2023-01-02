package com.an.assignment.repository;

import com.an.assignment.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findByCustomerId(Long customerId, Pageable pageable);
    Optional<Account> findByCustomerIdAndId(Long customerId, Long accountId);
}
