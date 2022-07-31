package com.highschool.domain.classroom;

import co.com.sofka.domain.generic.EventChange;
import com.highschool.domain.classroom.entities.Student;
import com.highschool.domain.classroom.entities.Test;
import com.highschool.domain.classroom.events.*;
import com.highschool.domain.classroom.values.ClassDate;

import java.time.LocalDate;
import java.util.HashSet;

public class ClassChange extends EventChange {

    ClassChange(Class classroom) {
        apply((ClassCreated event) -> {
            classroom.studentSet = new HashSet<>();
            classroom.teacher = event.getTeacher();
            classroom.testSet = new HashSet<>();
            classroom.classDate = new ClassDate(LocalDate.now());
        });

        apply((StudentAdded event) -> {
            Student student = new Student(event.getStudentID(), event.getStudentName());
            classroom.studentSet.add(student);
        });

        apply((StudentNameUpdated event) -> {
            Student student = classroom.findStudentById(event.getStudentID()).orElseThrow();
            student.updateName(event.getStudentName());
        });

        apply((TeacherAvailabilityUpdated event) -> classroom.teacher.updateAvailability(event.getAvailability()));

        apply((TeacherAvailabilityUpdatedByEvent event) -> classroom.teacher.updateAvailability(event.getAvailability()));

        apply((TeacherNameUpdated event) -> classroom.teacher.updateName(event.getTeacherName()));

        apply((TestAdded event) -> {
            Test test = new Test(event.getTestID(), event.getTestDate());
            classroom.testSet.add(test);
        });

        apply((TestDateUpdated event) -> {
            Test test = classroom.findTestById(event.getTestID()).orElseThrow();
            test.updateDate(event.getTestDate());
        });
    }
}
