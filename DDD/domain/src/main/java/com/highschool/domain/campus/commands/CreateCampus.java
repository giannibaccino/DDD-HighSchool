package com.highschool.domain.campus.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.campus.values.CampusStatus;
import com.highschool.domain.campus.values.CampusURL;

public class CreateCampus extends Command {

    private final CampusURL campusURL;
    private final CampusStatus campusStatus;

    public CreateCampus(CampusURL campusURL, CampusStatus campusStatus) {
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
