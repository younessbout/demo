package com.mongokafka.accounts.events;

import com.mongokafka.accounts.Status;
import com.mongokafka.common.BaseEvent;
import lombok.Getter;

import java.util.UUID;


public class AccountActivatedEvent extends BaseEvent<String> {

    public final Status status;

    public AccountActivatedEvent(String id) {
        super(id);
        this.status = Status.ACTIVATED;
    }
}
