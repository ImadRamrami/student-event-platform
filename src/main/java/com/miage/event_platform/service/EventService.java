package com.miage.event_platform.service;

import com.miage.event_platform.model.Event;
import com.miage.event_platform.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public boolean registerUser(Long eventId) {
        Event event = getEventById(eventId);
        if (event != null && event.getNumberOfPlaces() > 0) {
            event.setNumberOfPlaces(event.getNumberOfPlaces() - 1);
            eventRepository.save(event);
            return true;
        }
        return false;
    }
}
