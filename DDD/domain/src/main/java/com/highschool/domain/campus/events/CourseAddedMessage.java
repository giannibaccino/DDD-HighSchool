package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;

public class CourseAddedMessage extends DomainEvent {

    private final String message;

    public CourseAddedMessage(String message) {
        super("com.highschool.domain.campus.courseaddedmessage");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
