package com.miage.event_platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    @ManyToOne
    private User organizer;

    @ManyToMany
    @JoinTable(name = "event_attendees", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private java.util.Set<User> attendees = new java.util.HashSet<>();

    private Integer capacity; // Renamed from numberOfPlaces

    public Integer getRemainingPlaces() {
        return capacity - attendees.size();
    }
}