package com.highschool.domain.campus.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.campus.values.CampusURL;
import com.highschool.domain.campus.values.CourseID;
import com.highschool.domain.campus.values.CoursePassword;

public class UpdateCoursePassword extends Command {

    private final CampusURL campusURL;
    private final CourseID courseID;
    private final CoursePassword coursePassword;

    public UpdateCoursePassword(CampusURL campusURL, CourseID courseID, CoursePassword coursePassword) {
        this.campusURL = campusURL;
        this.courseID = courseID;
        this.coursePassword = coursePassword;
    }

    public CampusURL getCampusURL() {
        return campusURL;
    }

    public CourseID getCourseID() {
        return courseID;
    }

    public CoursePassword getCoursePassword() {
        return coursePassword;
    }
}
