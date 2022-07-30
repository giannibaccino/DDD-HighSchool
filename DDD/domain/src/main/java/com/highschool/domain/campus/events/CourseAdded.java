package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.values.CourseID;
import com.highschool.domain.campus.values.CourseName;
import com.highschool.domain.campus.values.CoursePassword;

public class CourseAdded extends DomainEvent {

    private final CourseID courseID;
    private final CourseName courseName;
    private final CoursePassword coursePassword;

    public CourseAdded(CourseID courseID, CourseName courseName, CoursePassword coursePassword) {
        super("com.highschool.domain.campus.courseadded");
        this.courseID = courseID;
        this. courseName = courseName;
        this.coursePassword = coursePassword;
    }

    public CourseID getCourseID() {
        return courseID;
    }

    public CourseName getCourseName() {
        return courseName;
    }

    public CoursePassword getCoursePassword() {
        return coursePassword;
    }
}
