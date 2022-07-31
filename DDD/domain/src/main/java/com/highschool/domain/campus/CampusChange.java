package com.highschool.domain.campus;

import co.com.sofka.domain.generic.EventChange;
import com.highschool.domain.campus.entities.Course;
import com.highschool.domain.campus.entities.User;
import com.highschool.domain.campus.events.*;

import java.util.HashSet;

public class CampusChange extends EventChange {

    CampusChange(Campus campus) {
        apply((CampusCreated event) -> {
            campus.userSet = new HashSet<>();
            campus.courseSet = new HashSet<>();
            campus.campusStatus = event.getCampusStatus();
        });

        apply((CampusStatusUpdated event) -> campus.campusStatus = event.getCampusStatus());

        apply((CourseAdded event) -> {
            Course course = new Course(event.getCourseID(), event.getCourseName(), event.getCoursePassword());
            campus.courseSet.add(course);
        });

        apply((CourseContentAdded event) -> {
            Course course = campus.findCourseById(event.getCourseID()).orElseThrow();
            course.addContent(event.getCourseContent());
        });

        apply((CourseNameUpdated event) -> {
            Course course = campus.findCourseById(event.getCourseID()).orElseThrow();
            course.updateName(event.getCourseName());
        });

        apply((CoursePasswordUpdated event) -> {
            Course course = campus.findCourseById(event.getCourseID()).orElseThrow();
            course.updatePassword(event.getCoursePassword());
        });

        apply((UserAdded event) -> {
            User user = new User(event.getUserID(), event.getUsername(), event.getUserPassword(), event.getEmail());
            campus.userSet.add(user);
        });

        apply((UsernameUpdated event) -> {
            User user = campus.findUserById(event.getUserID()).orElseThrow();
            user.updateUsername(event.getUsername());
        });

        apply((UserPasswordUpdated event) -> {
            User user = campus.findUserById(event.getUserID()).orElseThrow();
            user.updateUserPassword(event.getUserPassword());
        });
    }
}
