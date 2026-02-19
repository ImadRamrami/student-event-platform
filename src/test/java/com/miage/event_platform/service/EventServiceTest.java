package com.miage.event_platform.service;

import com.miage.event_platform.model.Event;
import com.miage.event_platform.model.User;
import com.miage.event_platform.repository.EventRepository;
import com.miage.event_platform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void registerUser_shouldFail_whenCapacityReached() {
        // Arrange
        Long eventId = 1L;
        String username = "testuser";

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("existing");

        User newUser = new User();
        newUser.setId(2L);
        newUser.setUsername(username);

        Event event = new Event();
        event.setId(eventId);
        event.setCapacity(1);
        Set<User> attendees = new HashSet<>();
        attendees.add(existingUser);
        event.setAttendees(attendees);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(newUser));

        // Act
        boolean result = eventService.registerUser(eventId, username);

        // Assert
        assertFalse(result, "Registration should fail when capacity is full");
        verify(eventRepository, never()).save(any(Event.class));
    }
}
