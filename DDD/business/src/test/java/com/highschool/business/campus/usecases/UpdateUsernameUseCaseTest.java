package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.campus.commands.UpdateUsername;
import com.highschool.domain.campus.events.CampusCreated;
import com.highschool.domain.campus.events.UserAdded;
import com.highschool.domain.campus.events.UsernameUpdated;
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
class UpdateUsernameUseCaseTest {

    @InjectMocks
    private UpdateUsernameUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateUsernameTest() {
        CampusURL campusURL = CampusURL.of("XXXX");
        UserID userID = UserID.of("YYYY");
        Username username = new Username("AAAA");
        var command = new UpdateUsername(campusURL, userID, username);

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(
                new CampusCreated(new CampusStatus(CampusStatusEnum.ONLINE)),
                new UserAdded(UserID.of("YYYY"), new Username("BBBB"), new UserPassword("1111"), new Email("CCC"))
        ));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(command.getCampusURL().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (UsernameUpdated) events.get(0);
        assertEquals("YYYY", event.getUserID().value());
        assertEquals("AAAA", event.getUsername().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}