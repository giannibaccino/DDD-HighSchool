package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.values.UserID;
import com.highschool.domain.campus.values.UserPassword;

public class UserPasswordUpdated extends DomainEvent {

    private final UserID userID;
    private final UserPassword userPassword;

    public UserPasswordUpdated(UserID userID, UserPassword userPassword) {
        super("com.highschool.domain.campus.userpasswordupdated");
        this.userID = userID;
        this.userPassword = userPassword;
    }

    public UserID getUserID() {
        return userID;
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }
}
