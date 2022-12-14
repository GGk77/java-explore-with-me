package ru.practicum.comment.model;

import lombok.*;
import ru.practicum.event.model.Event;
import ru.practicum.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false, referencedColumnName = "event_id")
    Event event;

    @NotBlank
    @Column(name = "text", nullable = false)
    String text;

    @Column(name = "created")
    LocalDateTime created;
}
