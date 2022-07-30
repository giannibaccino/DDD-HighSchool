package com.highschool.domain.campus;

import co.com.sofka.domain.generic.EventChange;
import com.highschool.domain.campus.events.CampusCreated;

import java.util.HashSet;

public class CampusChange extends EventChange {

    CampusChange(Campus campus) {
        apply((CampusCreated event) -> {
            campus.userSet = new HashSet<>();
            campus.courseSet = new HashSet<>();
            campus.campusStatus = getCampusStatus();
        });
    }
}
