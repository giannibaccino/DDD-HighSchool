package com.highschool.domain.classroom.commands;

import co.com.sofka.domain.generic.Command;
import com.highschool.domain.classroom.values.ClassID;
import com.highschool.domain.classroom.values.TestDate;
import com.highschool.domain.classroom.values.TestID;

public class UpdateTestDate extends Command {

    private final ClassID classID;
    private final TestID testID;
    private final TestDate testDate;

    public UpdateTestDate(ClassID classID, TestID testID, TestDate testDate) {
        this.classID = classID;
        this.testID = testID;
        this.testDate = testDate;
    }

    public ClassID getClassID() {
        return classID;
    }

    public TestID getTestID() {
        return testID;
    }

    public TestDate getTestDate() {
        return testDate;
    }
}
