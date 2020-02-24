package com.mongokafka.accounts.commands;

import com.mongokafka.common.BaseCommand;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateAccountCommand extends BaseCommand<String> {

    public final BigDecimal accountBalance;

    public final String currency;

    public CreateAccountCommand(BigDecimal accountBalance, String currency) {
        super(UUID.randomUUID().toString());
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
