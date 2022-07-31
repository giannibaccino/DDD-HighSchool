package com.highschool.domain.classroom.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.values.TestDate;
import com.highschool.domain.classroom.values.TestID;

public class TestAdded extends DomainEvent {

    private final TestID testID;
    private final TestDate testDate;

    public TestAdded(TestID testID, TestDate testDate) {
        super("com.highschool.domain.classroom.testadded");
        this.testID = testID;
        this.testDate = testDate;
    }

    public TestID getTestID() {
        return testID;
    }

    public TestDate getTestDate() {
        return testDate;
    }
}
