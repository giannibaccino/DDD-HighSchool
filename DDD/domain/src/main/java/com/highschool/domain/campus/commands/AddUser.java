package com.highschool.domain.campus.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.campus.values.CampusURL;
import com.highschool.domain.campus.values.Email;
import com.highschool.domain.campus.values.UserPassword;
import com.highschool.domain.campus.values.Username;

public class AddUser extends Command {

    private final CampusURL campusURL;
    private final Username username;
    private final UserPassword userPassword;
    private final Email email;

    public AddUser(CampusURL campusURL, Username username, UserPassword userPassword, Email email) {
        this.campusURL = campusURL;
        this.username = username;
        this.userPassword = userPassword;
        this.email = email;
    }

    public CampusURL getCampusURL() {
        return campusURL;
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
