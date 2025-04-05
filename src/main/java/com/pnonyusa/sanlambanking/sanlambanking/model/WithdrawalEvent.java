package com.pnonyusa.sanlambanking.sanlambanking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class WithdrawalEvent {
    private Long accountId;
    private BigDecimal amount;
    private String status;
}
