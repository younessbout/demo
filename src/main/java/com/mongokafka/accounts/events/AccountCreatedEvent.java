package com.mongokafka.accounts.events;

import com.mongokafka.common.BaseEvent;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;


public class AccountCreatedEvent extends BaseEvent<String> {

    public final BigDecimal accountBalance;

    public final String currency;


    public AccountCreatedEvent(String id, BigDecimal accountBalance, String currency) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
