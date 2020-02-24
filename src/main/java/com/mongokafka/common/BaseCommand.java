package com.mongokafka.common;


import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
public class BaseCommand<T> {

    @TargetAggregateIdentifier
    protected final T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
