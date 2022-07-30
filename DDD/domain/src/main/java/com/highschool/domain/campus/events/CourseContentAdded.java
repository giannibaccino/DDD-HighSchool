package com.highschool.domain.campus.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.values.CourseContent;
import com.highschool.domain.campus.values.CourseID;

public class CourseContentAdded extends DomainEvent {

    private final CourseID courseID;
    private final CourseContent courseContent;

    public CourseContentAdded(CourseID courseID, CourseContent courseContent) {
        super("com.highschool.domain.campus.coursecountentadded");
        this.courseID = courseID;
        this.courseContent = courseContent;
    }

    public CourseID getCourseID() {
        return courseID;
    }

    public CourseContent getCourseContent() {
        return courseContent;
    }
}
