package com.highschool.domain.classroom;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.entities.Student;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.entities.Test;
import com.highschool.domain.classroom.events.*;
import com.highschool.domain.classroom.values.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Class extends AggregateEvent<ClassID> {

    protected Set<Student> studentSet;
    protected Teacher teacher;
    protected Set<Test> testSet;
    protected ClassDate classDate;

    public Class(ClassID classID, Teacher teacher) {
        super(classID);
        appendChange(new ClassCreated(teacher)).apply();
        subscribe(new ClassChange(this));
    }

    private Class(ClassID classID) {super(classID);}

    public static Class from(ClassID classID, List<DomainEvent> events) {
        Class classroom = new Class(classID);
        events.forEach(classroom::applyEvent);
        return classroom;
    }

    public void addStudent(StudentFullName studentName) {
        StudentID studentID = new StudentID();
        appendChange(new StudentAdded(studentID, studentName)).apply();
    }

    public void updateStudentName(StudentID studentID, StudentFullName studentName) {
        appendChange(new StudentNameUpdated(studentID, studentName)).apply();
    }

    public void updateTeacherAvailability(TeacherID teacherID,Availability availability) {
        appendChange(new TeacherAvailabilityUpdated(teacherID, availability)).apply();
    }

    public void updateTeacherName(TeacherID teacherID, TeacherFullName teacherName) {
        appendChange(new TeacherNameUpdated(teacherID, teacherName)).apply();
    }

    public void addTest(TestDate testDate) {
        TestID testID = new TestID();
        appendChange(new TestAdded(testID, testDate)).apply();
    }

    public void updateTestDate(TestID testID, TestDate testDate) {
        appendChange(new TestDateUpdated(testID, testDate)).apply();
    }

    protected Optional<Student> findStudentById(StudentID studentID) {
        return this.studentSet.stream().filter(student -> student.identity().equals(studentID)).findFirst();
    }

    protected Optional<Test> findTestById(TestID testID) {
        return this.testSet.stream().filter(test -> test.identity().equals(testID)).findFirst();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ClassDate getClassDate() {
        return classDate;
    }
}