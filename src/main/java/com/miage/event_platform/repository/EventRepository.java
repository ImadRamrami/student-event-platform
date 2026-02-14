package com.miage.event_platform.repository;

import com.miage.event_platform.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    java.util.List<Event> findByOrganizer(com.miage.event_platform.model.User organizer);

    java.util.List<Event> findByAttendeesContaining(com.miage.event_platform.model.User user);
}