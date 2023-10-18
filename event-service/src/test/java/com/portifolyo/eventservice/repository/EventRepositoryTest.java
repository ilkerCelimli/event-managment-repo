package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventDescription;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.portifolyo.requests.eventservice.enums.EventType;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
public class EventRepositoryTest extends BaseRepositoryTest {

    @Mock
    EventRepository eventRepository;


    @Test
    public void saveEventTest(){
        Event event = new Event("TEST", LocalDateTime.of(2023,11,20,16,40),false,false, EventType.MEETING,50,new EventDescription("Deneme",null));
        when(eventRepository.save(event)).thenReturn(event);
    }

}
