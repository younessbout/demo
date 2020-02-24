package com.mongokafka.accounts.adapters;

import com.mongokafka.accounts.commands.CreateAccountCommand;
import com.mongokafka.accounts.commands.CreditMoneyCommand;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountAdapter {
    public CreateAccountCommand adapt(AccountCreateRequest accountCreateRequest) {
        return new CreateAccountCommand(accountCreateRequest.getInitialBalance(), accountCreateRequest.getCurrency());
    }

    public CreditMoneyCommand adapt(String accountId, MoneyCreditRequest moneyCreditRequest) {
        return new CreditMoneyCommand(accountId, moneyCreditRequest.getAmount());
    }
}
