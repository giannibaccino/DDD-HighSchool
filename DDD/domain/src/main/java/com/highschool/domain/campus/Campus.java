package com.highschool.domain.campus;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.entities.Course;
import com.highschool.domain.campus.entities.User;
import com.highschool.domain.campus.events.*;
import com.highschool.domain.campus.values.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Campus extends AggregateEvent<CampusURL> {

    protected Set<User> userSet;
    protected Set<Course> courseSet;
    protected CampusStatus campusStatus;

    public Campus(CampusURL campusURL, CampusStatus campusStatus) {
        super(campusURL);
        appendChange(new CampusCreated(campusStatus)).apply();
        subscribe(new CampusChange(this));
    }

    private Campus(CampusURL campusURL) {
        super(campusURL);
    }

    public static Campus from(CampusURL campusURL, List<DomainEvent> events) {
        Campus campus = new Campus(campusURL);
        events.forEach(campus::applyEvent);
        return campus;
    }

    //EVENTS
    public void updateCampusStatus(CampusURL campusURL, CampusStatus campusStatus) {
        appendChange(new CampusStatusUpdated(campusURL, campusStatus)).apply();
    }

    public void addUser(Username username, UserPassword userPassword, Email email) {
        UserID userID = new UserID();
        appendChange(new UserAdded(userID, username, userPassword, email)).apply();
    }

    public void userAddedMessage(String message) {
        appendChange(new UserAddedMessage(message)).apply();
    }

    public void updateUsername(UserID userID, Username username) {
        appendChange(new UsernameUpdated(userID, username)).apply();
    }

    public void updateUserPassword(UserID userID, UserPassword userPassword) {
        appendChange(new UserPasswordUpdated(userID, userPassword)).apply();
    }

    public void addCourse(CourseName courseName, CoursePassword coursePassword) {
        CourseID courseID = new CourseID();
        appendChange(new CourseAdded(courseID, courseName, coursePassword)).apply();
    }

    public void courseAddedMessage(String message) {
        appendChange(new CourseAddedMessage(message)).apply();
    }

    public void updateCourseName(CourseID courseID, CourseName courseName) {
        appendChange(new CourseNameUpdated(courseID, courseName)).apply();
    }

    public void updateCoursePassword(CourseID courseID, CoursePassword coursePassword) {
        appendChange(new CoursePasswordUpdated(courseID, coursePassword)).apply();
    }

    public void addCourseContent(CourseID courseID, CourseContent courseContent) {
        appendChange(new CourseContentAdded(courseID, courseContent));
    }

    //GETTERS
    protected Optional<User> findUserById(UserID userID) {
        return this.userSet.stream().filter(user -> user.identity().equals(userID)).findFirst();
    }

    protected Optional<Course> findCourseById(CourseID courseID) {
        return this.courseSet.stream().filter(course -> course.identity().equals(courseID)).findFirst();
    }

    public CampusStatus getCampusStatus() {
        return campusStatus;
    }
}
