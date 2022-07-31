package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.classroom.commands.AddStudent;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.events.ClassCreated;
import com.highschool.domain.classroom.events.StudentAdded;
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
class AddStudentUseCaseTest {

    @InjectMocks
    private AddStudentUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void addStudentTest() {

        ClassID classID = ClassID.of("XXXX");
        StudentFullName studentName = new StudentFullName("AAAA", "BBBB");
        var command = new AddStudent(classID, studentName);

        when(repository.getEventsBy("XXXX")).thenReturn(history());
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getClassID().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (StudentAdded) events.get(0);
        assertEquals("AAAA BBBB", event.getStudentName().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }

    private List<DomainEvent> history() {
        Teacher teacher = new Teacher(TeacherID.of("YYYY"), new TeacherFullName("AAAA", "BBBB"), new Availability(AvailabilityEnum.AVAILABLE));
        var event = new ClassCreated(teacher);

        event.setAggregateRootId("XXXX");
        return List.of(event);
    }
}