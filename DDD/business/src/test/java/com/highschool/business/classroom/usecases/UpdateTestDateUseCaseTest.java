package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.classroom.commands.UpdateTestDate;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.events.ClassCreated;
import com.highschool.domain.classroom.events.TestAdded;
import com.highschool.domain.classroom.events.TestDateUpdated;
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
class UpdateTestDateUseCaseTest {

    @InjectMocks
    private UpdateTestDateUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateTestDateTest() {
        ClassID classID = ClassID.of("XXXX");
        TestID testID = TestID.of("YYYY");
        TestDate testDate = new TestDate(LocalDate.now().plusDays(3));
        var command = new UpdateTestDate(classID, testID, testDate);

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new ClassCreated(new Teacher(TeacherID.of("ZZZZ"),
                        new TeacherFullName("AAAA","BBBB"),
                        new Availability(AvailabilityEnum.AVAILABLE))),
                new TestAdded(TestID.of("YYYY"), new TestDate(LocalDate.now()))
        ));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getClassID().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (TestDateUpdated) events.get(0);
        assertEquals("YYYY", event.getTestID().value());
        assertEquals(LocalDate.now().plusDays(3), event.getTestDate().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}