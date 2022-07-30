package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.values.Email;
import com.highschool.domain.campus.values.UserID;
import com.highschool.domain.campus.values.UserPassword;
import com.highschool.domain.campus.values.Username;

public class UserAdded extends DomainEvent {

    private final UserID userID;
    private final Username username;
    private final UserPassword userPassword;
    private final Email email;

    public UserAdded(UserID userID, Username username, UserPassword userPassword, Email email) {
        super("com.highschool.domain.campus.useradded");
        this.userID = userID;
        this.username = username;
        this.userPassword = userPassword;
        this.email = email;
    }

    public UserID getUserID() {
        return userID;
    }

    public Username getUsername() {
        return username;
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }

    public Email getEmail() {
        return email;
    }
}
