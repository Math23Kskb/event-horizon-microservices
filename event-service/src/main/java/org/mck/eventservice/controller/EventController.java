package org.mck.eventservice.controller;


import lombok.RequiredArgsConstructor;
import org.mck.eventservice.domain.Event;
import org.mck.eventservice.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    ResponseEntity<Event> createEvent(@RequestBody Event newEvent){
        return ResponseEntity.ok(eventService.createEvent(newEvent));
    }

    @GetMapping
    ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

}
