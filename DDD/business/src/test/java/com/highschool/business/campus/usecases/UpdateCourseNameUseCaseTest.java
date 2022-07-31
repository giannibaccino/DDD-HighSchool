package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.campus.commands.UpdateCourseName;
import com.highschool.domain.campus.events.CampusCreated;
import com.highschool.domain.campus.events.CourseAdded;
import com.highschool.domain.campus.events.CourseNameUpdated;
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
class UpdateCourseNameUseCaseTest {

    @InjectMocks
    private UpdateCourseNameUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateCourseNameTest() {

        CampusURL campusURL = CampusURL.of("XXXX");
        CourseID courseID = CourseID.of("YYYY");
        CourseName courseName = new CourseName("AAAA");
        var command = new UpdateCourseName(campusURL, courseID, courseName);

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new CampusCreated(new CampusStatus(CampusStatusEnum.ONLINE)),
                new CourseAdded(CourseID.of("YYYY"), new CourseName("BBBB"), new CoursePassword("1111"))
        ));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getCampusURL().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (CourseNameUpdated) events.get(0);
        assertEquals("AAAA", event.getCourseName().value());
        assertEquals("YYYY", event.getCourseID().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}