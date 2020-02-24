package com.mongokafka.accounts.events;

import com.mongokafka.common.BaseEvent;

import java.math.BigDecimal;


public class MoneyCreditedEvent extends BaseEvent<String> {

    public final BigDecimal amount;

    public MoneyCreditedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
