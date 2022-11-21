package ru.practicum.event.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.practicum.category.model.Category;
import ru.practicum.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    @Size(max = 1000)
    @NotNull
    @Column(name = "annotation", nullable = false, length = 1000)
    String annotation;

    @Size(max = 500)
    @NotNull
    @Column(name = "title", nullable = false, length = 500)
    String title;

    @Size(max = 5000)
    @NotNull
    @Column(name = "description", nullable = false, length = 5000)
    String description;

    @Size(max = 500)
    @NotNull
    @Column(name = "state", nullable = false, length = 500)
    String state;

    @Column(name = "created")
    LocalDateTime created;

    @Column(name = "published")
    LocalDateTime  published;

    @NotNull
    @Column(name = "start_date", nullable = false)
    LocalDateTime startDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "initiator_id", nullable = false)
    User initiator;

    @Column(name = "lat")
    Double lat;

    @Column(name = "lon")
    Double lon;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Column(name = "paid")
    Boolean paid;

    @Column(name = "moderation")
    Boolean moderation;

    @NotNull
    @Column(name = "participant_limit", nullable = false)
    Integer participantLimit;

    @Transient
    Integer views;



}