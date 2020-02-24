package com.mongokafka.accounts;

import com.mongokafka.accounts.commands.CreateAccountCommand;
import com.mongokafka.accounts.commands.CreditMoneyCommand;
import com.mongokafka.accounts.events.AccountActivatedEvent;
import com.mongokafka.accounts.events.AccountCreatedEvent;
import com.mongokafka.accounts.events.MoneyCreditedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

@Aggregate
@Getter
@Slf4j
@NoArgsConstructor
public class AccountAggregate {

    @AggregateIdentifier
    public String id;

    public BigDecimal accountBalance;

    public String currency;

    public String status;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        log.info("Handling CreateAccountCommand");
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(), createAccountCommand.accountBalance,
                createAccountCommand.currency));
    }

    @CommandHandler
    public AccountAggregate(CreditMoneyCommand creditMoneyCommand) {
        log.info("Handling CreditMoneyCommand for Account {}", creditMoneyCommand.getId());

        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.getId(), creditMoneyCommand.amount));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent) {

        log.info("Handling AccountCreatedEvent for Account {}", accountCreatedEvent.id);

        this.id = accountCreatedEvent.id;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = String.valueOf(Status.CREATED);
    }

    @EventSourcingHandler
    protected void on(MoneyCreditedEvent moneyCreditedEvent) {

        log.info("Handling MoneyCreditedEvent for Account {}", moneyCreditedEvent.id);

        this.accountBalance = this.accountBalance.add(moneyCreditedEvent.amount);
        if (this.accountBalance.add(moneyCreditedEvent.amount).compareTo(BigDecimal.ZERO) > 0) {
            log.info("Account {} Should be activated", moneyCreditedEvent.id);

            AggregateLifecycle.apply(new AccountActivatedEvent(this.id));
        }
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("Handling AccountActivatedEvent for Account {}", accountActivatedEvent.id);
        this.status = String.valueOf(accountActivatedEvent.status);
    }

}
