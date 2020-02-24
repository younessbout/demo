package com.mongokafka.accounts.services;

import com.mongokafka.accounts.commands.CreateAccountCommand;
import com.mongokafka.accounts.commands.CreditMoneyCommand;

import java.util.concurrent.CompletableFuture;

public interface AccountService {
    CompletableFuture<String> createAccount(CreateAccountCommand accountCreateDTO);

    CompletableFuture<String> creditMoneyToAccount(CreditMoneyCommand creditMoneyCommand);
}
