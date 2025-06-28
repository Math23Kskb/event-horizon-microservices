package org.mck.eventservice.service;

import lombok.RequiredArgsConstructor;
import org.mck.eventservice.domain.Event;
import org.mck.eventservice.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public Event createEvent(Event event) {

        String userServiceUrl = "http://localhost:8080/api/v1/users/" + event.getOrganizerUserId();

        System.out.println(userServiceUrl);

        try{
            restTemplate.getForEntity(userServiceUrl, String.class);
            return eventRepository.save(event);
        }catch(HttpClientErrorException.NotFound ex){
            throw new RuntimeException("Oganizador com ID " + event.getOrganizerUserId() + " não encontrado.");
        }
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

}
