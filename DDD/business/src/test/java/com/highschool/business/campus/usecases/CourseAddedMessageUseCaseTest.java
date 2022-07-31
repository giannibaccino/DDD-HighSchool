package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import com.highschool.domain.campus.events.CourseAdded;
import com.highschool.domain.campus.events.CourseAddedMessage;
import com.highschool.domain.campus.values.CourseID;
import com.highschool.domain.campus.values.CourseName;
import com.highschool.domain.campus.values.CoursePassword;
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
class CourseAddedMessageUseCaseTest {

    @InjectMocks
    private CourseAddedMessageUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void courseAddedMessageTest() {

        var event = new CourseAdded(CourseID.of("AAAA"), new CourseName("BBBB"), new CoursePassword("CCCC"));
        event.setAggregateRootId("XXXX");

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(event));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor("XXXX")
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var msg = (CourseAddedMessage) events.get(0);
        assertEquals("Course was succesfully added", msg.getMessage());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}