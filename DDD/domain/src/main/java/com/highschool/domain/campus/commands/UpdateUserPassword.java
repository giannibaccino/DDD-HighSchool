package com.highschool.domain.campus.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.campus.values.CampusURL;
import com.highschool.domain.campus.values.UserID;
import com.highschool.domain.campus.values.UserPassword;

public class UpdateUserPassword extends Command {

    private final CampusURL campusURL;
    private final UserID userID;
    private final UserPassword userPassword;

    public UpdateUserPassword(CampusURL campusURL, UserID userID, UserPassword userPassword) {
        this.campusURL = campusURL;
        this.userID = userID;
        this.userPassword = userPassword;
    }

    public CampusURL getCampusURL() {
        return campusURL;
    }

    public UserID getUserID() {
        return userID;
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }
}
