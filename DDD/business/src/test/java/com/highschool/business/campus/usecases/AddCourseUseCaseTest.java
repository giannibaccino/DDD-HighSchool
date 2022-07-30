package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.commands.AddCourse;
import com.highschool.domain.campus.events.CampusCreated;
import com.highschool.domain.campus.events.CourseAdded;
import com.highschool.domain.campus.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddCourseUseCaseTest {

    @InjectMocks
    private AddCourseUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void addCourseTest() {

        CampusURL campusURL = CampusURL.of("XXXX");
        CourseName courseName = new CourseName("AAAA");
        CoursePassword coursePassword = new CoursePassword("BBBB");
        var command = new AddCourse(campusURL, courseName, coursePassword);

        when(repository.getEventsBy("XXXX")).thenReturn(history());
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getCampusURL().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (CourseAdded) events.get(0);
        assertEquals("AAAA", event.getCourseName().value());
        assertEquals("BBBB", event.getCoursePassword().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }

    private List<DomainEvent> history() {
        CampusStatus campusStatus = new CampusStatus(CampusStatusEnum.ONLINE);
        var event = new CampusCreated(campusStatus);

        event.setAggregateRootId("XXXX");
        return List.of(event);
    }
}