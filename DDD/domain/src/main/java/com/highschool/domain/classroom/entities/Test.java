package com.highschool.domain.classroom.entities;

import co.com.sofka.domain.generic.Entity;
import com.highschool.domain.classroom.values.TestDate;
import com.highschool.domain.classroom.values.TestID;

import java.time.LocalDate;
import java.util.Objects;

public class Test extends Entity<TestID> {

    private TestDate testDate;

    public Test(TestID entityId, TestDate testDate) {
        super(entityId);
        this.testDate = testDate;
    }

    public void updateDate(TestDate testDate) {
        this.testDate = Objects.requireNonNull(testDate);
    }

    public TestDate getTestDate() {
        return testDate;
    }
}
