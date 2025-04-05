package com.pnonyusa.sanlambanking.sanlambanking.service;

import com.pnonyusa.sanlambanking.sanlambanking.model.WithdrawalEvent;

public interface EventPublisher {
    void publish(WithdrawalEvent event);
}
