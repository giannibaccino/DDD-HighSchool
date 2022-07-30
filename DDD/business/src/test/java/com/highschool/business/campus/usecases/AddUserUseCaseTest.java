package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import com.highschool.domain.campus.commands.AddUser;
import com.highschool.domain.campus.events.CampusCreated;
import com.highschool.domain.campus.events.UserAdded;
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
class AddUserUseCaseTest {

    @InjectMocks
    private AddUserUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void addUserTest() {

        CampusURL campusURL = CampusURL.of("XXXX");
        Username username = new Username("AAAA");
        UserPassword userPassword = new UserPassword("BBBB");
        Email email = new Email("CCCC");
        var command = new AddUser(campusURL, username, userPassword, email);

        when(repository.getEventsBy("XXXX")).thenReturn(history());
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getCampusURL().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (UserAdded) events.get(0);
        assertEquals("AAAA", event.getUsername().value());
        assertEquals("BBBB", event.getUserPassword().value());
        assertEquals("CCCC", event.getEmail().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }

    private List<DomainEvent> history() {
        CampusStatus campusStatus = new CampusStatus(CampusStatusEnum.ONLINE);
        var event = new CampusCreated(campusStatus);

        event.setAggregateRootId("XXXX");
        return List.of(event);
    }
}