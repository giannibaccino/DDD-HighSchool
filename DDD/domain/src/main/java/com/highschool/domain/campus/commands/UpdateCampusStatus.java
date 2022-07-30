package com.highschool.domain.campus.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.campus.values.*;

public class UpdateCampusStatus extends Command {

    private final CampusURL campusURL;
    private final CampusStatus campusStatus;

    public UpdateCampusStatus(CampusURL campusURL, CampusStatus campusStatus) {
        this.campusURL = campusURL;
        this.campusStatus = campusStatus;
    }

    public CampusURL getCampusURL() {
        return campusURL;
    }

    public CampusStatus getCampusStatus() {
        return campusStatus;
    }
}
