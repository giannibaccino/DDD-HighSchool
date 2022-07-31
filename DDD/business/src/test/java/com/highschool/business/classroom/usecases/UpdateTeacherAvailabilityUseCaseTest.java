package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.classroom.commands.UpdateTeacherAvailability;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.events.ClassCreated;
import com.highschool.domain.classroom.events.TeacherAvailabilityUpdated;
import com.highschool.domain.classroom.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTeacherAvailabilityUseCaseTest {

    @InjectMocks
    private UpdateTeacherAvailabilityUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateTeacherAvailabilityTest() {
        ClassID classID = ClassID.of("XXXX");
        TeacherID teacherID = TeacherID.of("YYYY");
        Availability availability = new Availability(AvailabilityEnum.UNAVILABLE);
        var command = new UpdateTeacherAvailability(classID, teacherID, availability);

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new ClassCreated(new Teacher(TeacherID.of("YYYY"),
                        new TeacherFullName("AAAA","BBBB"),
                        new Availability(AvailabilityEnum.AVAILABLE)))
        ));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getClassID().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (TeacherAvailabilityUpdated) events.get(0);
        assertEquals("YYYY", event.getTeacherID().value());
        assertEquals(AvailabilityEnum.UNAVILABLE, event.getAvailability().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}