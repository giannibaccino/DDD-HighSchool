package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.values.UserID;
import com.highschool.domain.campus.values.Username;

public class UsernameUpdated extends DomainEvent {

    private final UserID userID;
    private final Username username;

    public UsernameUpdated(UserID userID, Username username) {
        super("com.highschool.domain.campus.usernameupdated");
        this.userID = userID;
        this.username = username;
    }

    public UserID getUserID() {
        return userID;
    }

    public Username getUsername() {
        return username;
    }
}
