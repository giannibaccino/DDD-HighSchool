package com.highschool.domain.campus.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.campus.values.CampusURL;
import com.highschool.domain.campus.values.CourseID;
import com.highschool.domain.campus.values.CourseName;

public class UpdateCourseName extends Command {

    private final CampusURL campusURL;
    private final CourseID courseID;
    private final CourseName courseName;

    public UpdateCourseName(CampusURL campusURL, CourseID courseID, CourseName courseName) {
        this.campusURL = campusURL;
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public CampusURL getCampusURL() {
        return campusURL;
    }

    public CourseID getCourseID() {
        return courseID;
    }

    public CourseName getCourseName() {
        return courseName;
    }
}
