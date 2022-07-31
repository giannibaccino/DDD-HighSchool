package com.highschool.domain.classroom.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.classroom.values.ClassID;
import com.highschool.domain.classroom.values.TestDate;

public class AddTest extends Command {

    private final ClassID classID;
    private final TestDate testDate;

    public AddTest(ClassID classID, TestDate testDate) {
        this.classID = classID;
        this.testDate = testDate;
    }

    public ClassID getClassID() {
        return classID;
    }

    public TestDate getTestDate() {
        return testDate;
    }
}
