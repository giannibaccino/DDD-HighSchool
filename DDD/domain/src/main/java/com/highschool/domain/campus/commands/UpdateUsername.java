package com.highschool.domain.campus.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.campus.values.*;

public class UpdateUsername extends Command {

    private final CampusURL campusURL;
    private final UserID userID;
    private final Username username;

    public UpdateUsername(CampusURL campusURL, UserID userID, Username username) {
        this.campusURL = campusURL;
        this.userID = userID;
        this.username = username;
    }

    public CampusURL getCampusURL() {
        return campusURL;
    }

    public UserID getUserID() {
        return userID;
    }

    public Username getUsername() {
        return username;
    }
}
