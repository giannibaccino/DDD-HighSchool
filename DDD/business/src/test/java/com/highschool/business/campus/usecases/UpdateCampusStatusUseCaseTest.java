package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.campus.commands.UpdateCampusStatus;
import com.highschool.domain.campus.events.CampusCreated;
import com.highschool.domain.campus.events.CampusStatusUpdated;
import com.highschool.domain.campus.values.CampusStatus;
import com.highschool.domain.campus.values.CampusStatusEnum;
import com.highschool.domain.campus.values.CampusURL;
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
class UpdateCampusStatusUseCaseTest {

    @InjectMocks
    private UpdateCampusStatusUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void updateCampusStatusTest() {
        CampusURL campusURL = CampusURL.of("XXXX");
        CampusStatus campusStatus = new CampusStatus(CampusStatusEnum.MAINTENANCE);
        var command = new UpdateCampusStatus(campusURL, campusStatus);

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

        var event = (CampusStatusUpdated) events.get(0);
        assertEquals(CampusStatusEnum.MAINTENANCE, event.getCampusStatus().value());
        Mockito.verify(repository).getEventsBy("XXXX");
    }
}