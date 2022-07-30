package com.highschool.domain.campus.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschool.domain.campus.values.Email;
import com.highschool.domain.campus.values.UserID;
import com.highschool.domain.campus.values.UserPassword;
import com.highschool.domain.campus.values.Username;

import java.util.Objects;

public class User extends Entity<UserID> {

    private Username username;
    private UserPassword userPassword;
    private Email email;

    public User(UserID entityId, Username username, UserPassword userPassword, Email email) {
        super(entityId);
        this.username = username;
        this.userPassword = userPassword;
        this.email = email;
    }

    public void updateUsername(Username username) {
        this.username = Objects.requireNonNull(username);
    }

    public void updateUserPassword(UserPassword userPassword) {
        this.userPassword = Objects.requireNonNull(userPassword);
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
