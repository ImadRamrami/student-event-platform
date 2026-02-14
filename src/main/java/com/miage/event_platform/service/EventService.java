package com.miage.event_platform.service;

import com.miage.event_platform.model.Event;
import com.miage.event_platform.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private com.miage.event_platform.repository.UserRepository userRepository;

    public java.util.List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void createEvent(Event event, String username) {
        com.miage.event_platform.model.User user = userRepository.findByUsername(username).orElse(null);
        event.setOrganizer(user);
        eventRepository.save(event);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public boolean registerUser(Long eventId, String username) {
        Event event = getEventById(eventId);
        com.miage.event_platform.model.User user = userRepository.findByUsername(username).orElse(null);

        if (event != null && user != null) {
            // Check if already registered
            if (event.getAttendees().contains(user)) {
                return false; // Already registered
            }
            // Check capacity
            if (event.getAttendees().size() < event.getCapacity()) {
                event.getAttendees().add(user);
                eventRepository.save(event);
                return true;
            }
        }
        return false;
    }

    public void unregisterUser(Long eventId, String username) {
        Event event = getEventById(eventId);
        com.miage.event_platform.model.User user = userRepository.findByUsername(username).orElse(null);

        if (event != null && user != null) {
            if (event.getAttendees().contains(user)) {
                event.getAttendees().remove(user);
                eventRepository.save(event);
            }
        }
    }

    public java.util.List<Event> getEventsByOrganizer(String username) {
        com.miage.event_platform.model.User user = userRepository.findByUsername(username).orElse(null);
        return eventRepository.findByOrganizer(user);
    }

    public java.util.List<Event> getEventsRegisteredBy(String username) {
        com.miage.event_platform.model.User user = userRepository.findByUsername(username).orElse(null);
        return eventRepository.findByAttendeesContaining(user);
    }
}
