package com.mongokafka.accounts.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class AccountCreateRequest {
    @JsonProperty(value = "initial_balance")
    @NotNull
    private BigDecimal initialBalance;

    @JsonProperty(value = "currency")
    @NotNull
    private String currency;
}
