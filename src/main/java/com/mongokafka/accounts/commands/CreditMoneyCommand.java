package com.mongokafka.accounts.commands;

import com.mongokafka.common.BaseCommand;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditMoneyCommand extends BaseCommand<String> {

    public final BigDecimal amount;

    public CreditMoneyCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
