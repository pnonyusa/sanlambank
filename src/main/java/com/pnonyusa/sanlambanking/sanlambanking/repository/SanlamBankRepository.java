package com.pnonyusa.sanlambanking.sanlambanking.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author Phaphamani Nonyusa
 * Retrieves the current balance of a specific bank account.
 *
 * @param accountId the ID of the account to query
 * @return the current account balance
 * @throws IllegalArgumentException if the account is not found in the database
 *
 * Uses JdbcTemplate to execute a SQL SELECT statement.
 */

@Repository
@RequiredArgsConstructor
public class SanlamBankRepository {

    private final JdbcTemplate jdbcTemplate;

    public BigDecimal getBalance(Long accountId) {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, BigDecimal.class, accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Account not found");
        }
    }

    public void debitAccount(Long accountId, BigDecimal amount) {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        int updated = jdbcTemplate.update(sql, amount, accountId);
        if (updated != 1) {
            throw new IllegalStateException("Account update failed");
        }
    }
}
