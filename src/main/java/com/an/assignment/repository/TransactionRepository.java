package com.an.assignment.repository;

import com.an.assignment.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByCustomerIdAndAccountId(Long customerId, Long accountId, Pageable pageable);

    @Query("select sum(change) from Transaction t where t.customer.id = :customerId and t.account.id = :accountId")
    BigDecimal sumOfChangesForAccount(Long customerId, Long accountId);
}
