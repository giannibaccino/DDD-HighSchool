package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;

public class UserAddedMessage extends DomainEvent {

    private final String message;

    public UserAddedMessage(String message) {
        super("com.highschool.domain.campus.useraddedmessage");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
