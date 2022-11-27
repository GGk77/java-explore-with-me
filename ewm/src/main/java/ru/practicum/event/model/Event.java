package ru.practicum.event.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.WhereJoinTable;
import ru.practicum.category.model.Category;
import ru.practicum.event.enums.EventState;
import ru.practicum.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    Integer id;

    @Column(name = "annotation", nullable = false)
    String annotation;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description", nullable = false)
    String description;

    @Builder.Default
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    EventState state = EventState.PENDING;

    @CreationTimestamp
    @Column(name = "created")
    LocalDateTime createdOn;

    @Column(name = "published")
    LocalDateTime  publishedOn;

    @Column(name = "start_date")
    LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "initiator_id", referencedColumnName = "user_id")
    User initiator;

    @Column(name = "lat")
    Double lat;

    @Column(name = "lon")
    Double lon;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    Category category;

    @Column(name = "paid")
    Boolean paid;

    @Column(name = "moderation")
    Boolean moderation;

    @Column(name = "participant_limit")
    Integer participantLimit;

    @WhereJoinTable(clause = "status='CONFIRMED'")
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "requests",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> participants = new ArrayList<>();

    @Transient
    Integer views;
}