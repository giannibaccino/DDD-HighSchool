package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.classroom.commands.CreateClass;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.events.ClassCreated;
import com.highschool.domain.classroom.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateClassUseCaseTest {

    private CreateClassUseCase useCase;

    @BeforeEach
    public void setup() {useCase = new CreateClassUseCase();}

    @Test
    void createClassTest() {
        ClassID classID = ClassID.of("XXXX");
        Teacher teacher = new Teacher(TeacherID.of("YYYY"), new TeacherFullName("AAAA", "BBBB"), new Availability(AvailabilityEnum.AVAILABLE));
        var command = new CreateClass(classID, teacher);

        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var createdClass = (ClassCreated) events.get(0);
        assertEquals("XXXX", createdClass.aggregateRootId());
        assertEquals(AvailabilityEnum.AVAILABLE, createdClass.getTeacher().getAvailability().value());
    }

}