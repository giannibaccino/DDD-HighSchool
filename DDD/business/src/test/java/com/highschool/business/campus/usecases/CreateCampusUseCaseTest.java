package com.highschool.business.campus.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.highschool.domain.campus.commands.CreateCampus;
import com.highschool.domain.campus.events.CampusCreated;
import com.highschool.domain.campus.values.CampusStatus;
import com.highschool.domain.campus.values.CampusStatusEnum;
import com.highschool.domain.campus.values.CampusURL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateCampusUseCaseTest {

    private CreateCampusUseCase useCase;

    @BeforeEach
    public void setup() { useCase = new CreateCampusUseCase();}

    @Test
    void createCampusTest() {

        CampusURL campusURL = CampusURL.of("XXXX");
        CampusStatus campusStatus = new CampusStatus(CampusStatusEnum.ONLINE);
        var command = new CreateCampus(campusURL, campusStatus);

        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var createdCampus = (CampusCreated) events.get(0);
        assertEquals("XXXX", createdCampus.aggregateRootId());
        assertEquals(CampusStatusEnum.ONLINE, createdCampus.getCampusStatus().value());
    }
}