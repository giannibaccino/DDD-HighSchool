package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.values.CourseID;
import com.highschool.domain.campus.values.CourseName;

public class CourseNameUpdated extends DomainEvent {

    private final CourseID courseID;
    private final CourseName courseName;

    public CourseNameUpdated(CourseID courseID, CourseName courseName) {
        super("com.highschool.domain.campus.coursenameupdated");
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public CourseID getCourseID() {
        return courseID;
    }

    public CourseName getCourseName() {
        return courseName;
    }
}
