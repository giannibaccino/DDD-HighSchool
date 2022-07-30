package com.highschool.domain.campus.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.campus.values.CampusURL;
import com.highschool.domain.campus.values.CourseContent;
import com.highschool.domain.campus.values.CourseID;

public class AddCourseContent extends Command {

    private final CampusURL campusURL;
    private final CourseID courseID;
    private final CourseContent courseContent;

    public AddCourseContent(CampusURL campusURL, CourseID courseID, CourseContent courseContent) {
        this.campusURL = campusURL;
        this.courseID = courseID;
        this.courseContent = courseContent;
    }

    public CampusURL getCampusURL() {
        return campusURL;
    }

    public CourseID getCourseID() {
        return courseID;
    }

    public CourseContent getCourseContent() {
        return courseContent;
    }
}
