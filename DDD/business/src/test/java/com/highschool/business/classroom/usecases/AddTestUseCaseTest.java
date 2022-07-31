package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.commands.AddTest;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.events.ClassCreated;
import com.highschool.domain.classroom.events.TestAdded;
import com.highschool.domain.classroom.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddTestUseCaseTest {

    @InjectMocks
    private AddTestUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void addTestTest() {

        ClassID classID = ClassID.of("XXXX");
        TestDate testDate = new TestDate(LocalDate.now());
        var command = new AddTest(classID, testDate);

        when(repository.getEventsBy("XXXX")).thenReturn(history());
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getClassID().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (TestAdded) events.get(0);
        assertEquals(LocalDate.now(), event.getTestDate().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }

    private List<DomainEvent> history() {
        Teacher teacher = new Teacher(TeacherID.of("YYYY"), new TeacherFullName("AAAA", "BBBB"), new Availability(AvailabilityEnum.AVAILABLE));
        var event = new ClassCreated(teacher);

        event.setAggregateRootId("XXXX");
        return List.of(event);
    }
}