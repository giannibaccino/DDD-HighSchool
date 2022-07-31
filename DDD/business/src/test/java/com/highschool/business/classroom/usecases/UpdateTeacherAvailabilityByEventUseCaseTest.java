package com.highschool.business.classroom.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import com.highschool.domain.classroom.entities.Teacher;
import com.highschool.domain.classroom.events.ClassCreated;
import com.highschool.domain.classroom.events.TeacherAvailabilityUpdatedByEvent;
import com.highschool.domain.classroom.values.Availability;
import com.highschool.domain.classroom.values.AvailabilityEnum;
import com.highschool.domain.classroom.values.TeacherFullName;
import com.highschool.domain.classroom.values.TeacherID;
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
class UpdateTeacherAvailabilityByEventUseCaseTest {

    @InjectMocks
    private UpdateTeacherAvailabilityByEventUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateTeacherAvailabilityByEventTest() {

        TeacherID teacherID = TeacherID.of("YYYY");
        TeacherFullName teacherFullName = new TeacherFullName("AAAA", "BBBB");
        Availability availability = new Availability(AvailabilityEnum.AVAILABLE);
        Teacher teacher = new Teacher(teacherID, teacherFullName, availability);
        var event = new ClassCreated(teacher);

        event.setAggregateRootId("XXXX");

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(event));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor("XXXX")
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var update = (TeacherAvailabilityUpdatedByEvent) events.get(0);
        assertEquals(AvailabilityEnum.UNAVILABLE, update.getAvailability().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}