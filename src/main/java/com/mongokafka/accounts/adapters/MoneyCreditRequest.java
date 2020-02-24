package com.mongokafka.accounts.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class MoneyCreditRequest {
    @JsonProperty(value = "amount")
    @NotNull
    private BigDecimal amount;
}
