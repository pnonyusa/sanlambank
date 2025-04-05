package com.pnonyusa.sanlambanking.sanlambanking.service;

import com.pnonyusa.sanlambanking.sanlambanking.exception.InsufficientFundsException;
import com.pnonyusa.sanlambanking.sanlambanking.model.WithdrawalEvent;
import com.pnonyusa.sanlambanking.sanlambanking.repository.SanlamBankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Phaphamani Nonyusa
 * Performs a withdrawal operation:
 * 1. Checks if the account has sufficient balance.
 * 2. Debits the account by the specified amount.
 * 3. Publishes a withdrawal event.
 *
 * This method is transactional to ensure atomicity—if any step fails, the transaction rolls back.
 *
 * @param accountId the ID of the account to withdraw from
 * @param amount the amount to be withdrawn
 * @throws InsufficientFundsException if the account does not have enough funds
 */
@Service
@RequiredArgsConstructor
public class SanlamBankService {

    private final SanlamBankRepository accountRepository;
    private final EventPublisher eventPublisher;

    @Transactional
    public void withdraw(Long accountId, BigDecimal amount) {
        BigDecimal balance = accountRepository.getBalance(accountId);
        if (balance == null || balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance");
        }

        accountRepository.debitAccount(accountId, amount);

        WithdrawalEvent event = new WithdrawalEvent(accountId, amount, "SUCCESSFUL");
        eventPublisher.publish(event);
    }

}
