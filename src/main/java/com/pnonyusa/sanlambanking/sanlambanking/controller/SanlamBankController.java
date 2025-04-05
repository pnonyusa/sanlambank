package com.pnonyusa.sanlambanking.sanlambanking.controller;

import com.pnonyusa.sanlambanking.sanlambanking.exception.InsufficientFundsException;
import com.pnonyusa.sanlambanking.sanlambanking.service.SanlamBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Phaphamani Nonyusa
 * Handles withdrawal requests from a bank account.
 *
 * @param accountId the ID of the account to withdraw from
 * @param amount the amount to withdraw
 * @return a ResponseEntity with a success message or appropriate error message and HTTP status
 *
 * The method delegates the withdrawal logic to the SanlamBankService.
 * - Returns 200 OK if withdrawal is successful.
 * - Returns 400 Bad Request if the account has insufficient funds.
 * - Returns 500 Internal Server Error for any other unexpected failures.
 */
@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class SanlamBankController {

    private final SanlamBankService bankAccountService;

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        try {
            bankAccountService.withdraw(accountId, amount);
            return ResponseEntity.ok("Withdrawal successful");
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Withdrawal failed");
        }
    }

}
