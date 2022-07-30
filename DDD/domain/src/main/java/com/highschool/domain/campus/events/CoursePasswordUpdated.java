package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.values.CourseID;
import com.highschool.domain.campus.values.CourseName;
import com.highschool.domain.campus.values.CoursePassword;

public class CoursePasswordUpdated extends DomainEvent {

    private final CourseID courseID;
    private final CoursePassword coursePassword;

    public CoursePasswordUpdated(CourseID courseID, CoursePassword coursePassword) {
        super("com.highschool.domain.campus.coursepasswordupdated");
        this.courseID = courseID;
        this.coursePassword = coursePassword;
    }

    public CourseID getCourseID() {
        return courseID;
    }

    public CoursePassword getCoursePassword() {
        return coursePassword;
    }
}
