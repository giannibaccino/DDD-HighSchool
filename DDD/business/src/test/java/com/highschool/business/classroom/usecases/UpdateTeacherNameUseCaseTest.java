package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.classroom.commands.UpdateTeacherName;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.events.ClassCreated;
import com.highschool.domain.classroom.events.TeacherNameUpdated;
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
class UpdateTeacherNameUseCaseTest {

    @InjectMocks
    private UpdateTeacherNameUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateTeacherNameTest() {
        ClassID classID = ClassID.of("XXXX");
        TeacherID teacherID = TeacherID.of("YYYY");
        TeacherFullName teacherName = new TeacherFullName("AAAA", "BBBB");
        var command = new UpdateTeacherName(classID, teacherID, teacherName);

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new ClassCreated(new Teacher(TeacherID.of("YYYY"),
                        new TeacherFullName("CCCC","DDDD"),
                        new Availability(AvailabilityEnum.AVAILABLE)))
        ));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getClassID().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (TeacherNameUpdated) events.get(0);
        assertEquals("YYYY", event.getTeacherID().value());
        assertEquals("AAAA BBBB", event.getTeacherName().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }

}