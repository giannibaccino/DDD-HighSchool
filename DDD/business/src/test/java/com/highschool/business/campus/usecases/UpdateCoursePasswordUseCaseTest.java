package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.campus.commands.UpdateCoursePassword;
import com.highschool.domain.campus.events.CampusCreated;
import com.highschool.domain.campus.events.CourseAdded;
import com.highschool.domain.campus.events.CoursePasswordUpdated;
import com.highschool.domain.campus.values.*;
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
class UpdateCoursePasswordUseCaseTest {

    @InjectMocks
    private UpdateCoursePasswordUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateCoursePasswordTest() {
        CampusURL campusURL = CampusURL.of("XXXX");
        CourseID courseID = CourseID.of("YYYY");
        CoursePassword coursePassword = new CoursePassword("1111");
        var command = new UpdateCoursePassword(campusURL, courseID, coursePassword);

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new CampusCreated(new CampusStatus(CampusStatusEnum.ONLINE)),
                new CourseAdded(CourseID.of("YYYY"), new CourseName("AAAA"), new CoursePassword("2222"))
        ));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getCampusURL().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (CoursePasswordUpdated) events.get(0);
        assertEquals("YYYY", event.getCourseID().value());
        assertEquals("1111", event.getCoursePassword().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}