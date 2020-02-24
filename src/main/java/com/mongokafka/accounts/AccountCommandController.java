package com.mongokafka.accounts;

import com.mongokafka.accounts.adapters.AccountAdapter;
import com.mongokafka.accounts.adapters.AccountCreateRequest;
import com.mongokafka.accounts.adapters.MoneyCreditRequest;
import com.mongokafka.accounts.commands.CreditMoneyCommand;
import com.mongokafka.accounts.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor
public class AccountCommandController {

    private final AccountService accountService;
    private final AccountAdapter accountAdapter;


    @PostMapping
    public CompletableFuture<String> createAccount(@RequestBody @Validated AccountCreateRequest accountCreateRequest) {
        return accountService.createAccount(accountAdapter.adapt(accountCreateRequest));
    }


    @PutMapping(value = "/{accountId}/credit")
    public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountId") String accountId,
                                                          @RequestBody MoneyCreditRequest moneyCreditRequest) {

        CreditMoneyCommand creditMoneyCommand = accountAdapter.adapt(accountId, moneyCreditRequest);

        return accountService.creditMoneyToAccount(creditMoneyCommand);
    }
}
