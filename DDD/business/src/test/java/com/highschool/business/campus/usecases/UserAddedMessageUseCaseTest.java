package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import com.highschool.domain.campus.events.UserAdded;
import com.highschool.domain.campus.events.UserAddedMessage;
import com.highschool.domain.campus.values.Email;
import com.highschool.domain.campus.values.UserID;
import com.highschool.domain.campus.values.UserPassword;
import com.highschool.domain.campus.values.Username;
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
class UserAddedMessageUseCaseTest {

    @InjectMocks
    private UserAddedMessageUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void userAddedMessageTest() {

        var event = new UserAdded(UserID.of("AAAA"), new Username("BBBB"), new UserPassword("CCCC"), new Email("DDDD"));

        event.setAggregateRootId("XXXX");

        when(repository.getEventsBy("XXXX")).thenReturn(List.of(event));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor("XXXX")
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var msg = (UserAddedMessage) events.get(0);
        assertEquals("User was succesfully added", msg.getMessage());
        Mockito.verify(repository).getEventsBy("XXXX");
    }

}