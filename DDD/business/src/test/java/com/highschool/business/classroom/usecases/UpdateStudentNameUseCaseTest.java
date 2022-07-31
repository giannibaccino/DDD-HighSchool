package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.classroom.commands.UpdateStudentName;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.events.ClassCreated;
import com.highschool.domain.classroom.events.StudentAdded;
import com.highschool.domain.classroom.events.StudentNameUpdated;
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
class UpdateStudentNameUseCaseTest {

    @InjectMocks
    private UpdateStudentNameUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateStudentNameTest() {
        ClassID classID = ClassID.of("XXXX");
        StudentID studentID = StudentID.of("YYYY");
        StudentFullName studentName = new StudentFullName("AAAA", "BBBB");
        var command = new UpdateStudentName(classID, studentID, studentName);

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new ClassCreated(new Teacher(TeacherID.of("ZZZZ"),
                        new TeacherFullName("CCCC","DDDD"),
                        new Availability(AvailabilityEnum.AVAILABLE))),
                new StudentAdded(StudentID.of("YYYY"), new StudentFullName("EEEE", "FFFF"))
        ));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getClassID().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (StudentNameUpdated) events.get(0);
        assertEquals("YYYY", event.getStudentID().value());
        assertEquals("AAAA BBBB", event.getStudentName().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}