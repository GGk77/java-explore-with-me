package ru.practicum.request.model;

import lombok.*;
import ru.practicum.event.model.Event;
import ru.practicum.request.enums.Status;
import ru.practicum.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User requester;


    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status = Status.PENDING;

    @Column(name = "created")
    LocalDateTime created;

}