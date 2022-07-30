package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.campus.commands.UpdateUserPassword;
import com.highschool.domain.campus.events.CampusCreated;
import com.highschool.domain.campus.events.UserPasswordUpdated;
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
class UpdateUserPasswordUseCaseTest {

    @InjectMocks
    private UpdateUserPasswordUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateUsernameTest() {
        CampusURL campusURL = CampusURL.of("XXXX");
        UserID userID = UserID.of("YYYY");
        UserPassword userPassword = new UserPassword("AAAA");
        var command = new UpdateUserPassword(campusURL, userID, userPassword);

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new CampusCreated(new CampusStatus(CampusStatusEnum.ONLINE))
        ));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getCampusURL().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (UserPasswordUpdated) events.get(0);
        assertEquals("YYYY", event.getUserID().value());
        assertEquals("AAAA", event.getUserPassword().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}