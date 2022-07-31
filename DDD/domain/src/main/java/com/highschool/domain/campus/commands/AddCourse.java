package com.highschool.domain.campus.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.campus.values.CampusURL;
import com.highschool.domain.campus.values.CourseName;
import com.highschool.domain.campus.values.CoursePassword;

public class AddCourse extends Command {

    private final CampusURL campusURL;
    private final CourseName courseName;
    private final CoursePassword coursePassword;

    public AddCourse(CampusURL campusURL, CourseName courseName, CoursePassword coursePassword) {
        this.campusURL = campusURL;
        this.courseName = courseName;
        this.coursePassword = coursePassword;
    }

    public CampusURL getCampusURL() {
        return campusURL;
    }

    public CourseName getCourseName() {
        return courseName;
    }

    public CoursePassword getCoursePassword() {
        return coursePassword;
    }
}
