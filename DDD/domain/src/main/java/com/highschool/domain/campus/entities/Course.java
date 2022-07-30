package com.highschool.domain.campus.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschool.domain.campus.values.CourseContent;
import com.highschool.domain.campus.values.CourseID;
import com.highschool.domain.campus.values.CourseName;
import com.highschool.domain.campus.values.CoursePassword;

import java.util.Objects;
import java.util.Set;

public class Course extends Entity<CourseID> {

    private CourseName courseName;
    private CoursePassword coursePassword;
    private Set<CourseContent> contentSet;

    public Course(CourseID courseID, CourseName courseName, CoursePassword coursePassword) {
        super(courseID);
        this.courseName = courseName;
        this.coursePassword = coursePassword;
    }

    public void updateName(CourseName courseName) {
        this.courseName = Objects.requireNonNull(courseName);
    }
    public void updatePassword(CoursePassword coursePassword) {
        this.coursePassword = Objects.requireNonNull(coursePassword);
    }

    public void addContent(CourseContent courseContent) {
        this.contentSet.add(courseContent);
    }

    public CourseName getCourseName() {
        return courseName;
    }

    public CoursePassword getCoursePassword() {
        return coursePassword;
    }

    public Set<CourseContent> getContentSet() {
        return contentSet;
    }
}
