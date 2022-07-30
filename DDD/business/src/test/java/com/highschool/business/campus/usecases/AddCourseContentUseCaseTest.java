package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.commands.AddCourseContent;
import com.highschool.domain.campus.events.CampusCreated;
import com.highschool.domain.campus.events.CourseContentAdded;
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
class AddCourseContentUseCaseTest {


    @InjectMocks
    private AddCourseContentUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void addCourseContentTest() {

        CampusURL campusURL = CampusURL.of("XXXX");
        CourseID courseID = CourseID.of("YYYY");
        CourseContent courseContent = new CourseContent("AAAA");
        var command = new AddCourseContent(campusURL, courseID, courseContent);

        when(repository.getEventsBy("XXXX")).thenReturn(history());
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getCampusURL().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (CourseContentAdded) events.get(0);
        assertEquals("AAAA", event.getCourseContent().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }

    private List<DomainEvent> history() {
        CampusStatus campusStatus = new CampusStatus(CampusStatusEnum.ONLINE);
        var event = new CampusCreated(campusStatus);

        event.setAggregateRootId("XXXX");
        return List.of(event);
    }
}