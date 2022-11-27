package ru.practicum.event.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.event.enums.EventState;
import ru.practicum.event.model.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> getByIdIn(List<Integer> ids);

    List<Event> getByInitiator_IdOrderByEventDateDesc(Integer id, Pageable pageable);

    @Query("SELECT e FROM Event AS e " +
            "WHERE ((:users) IS NULL OR e.initiator.id IN :users) " +
            "AND ((:states) IS NULL OR e.state IN :states) " +
            "AND ((:catIds) IS NULL OR e.category.id IN :catIds) " +
            "AND (e.eventDate >= :rangeStart) " +
            "AND CAST(:rangeEnd AS date) IS NULL OR e.eventDate <= :rangeEnd " +
            "order by e.id desc ")
    List<Event> getAllUsersEvents(List<Integer> users, List<Integer> catIds, List<EventState> states,
                                   LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    @Query("SELECT e FROM Event e " +
            "WHERE (upper(e.annotation) like upper(CONCAT('%',?1,'%')) " +
            "or upper(e.description) like upper(CONCAT('%',?1,'%')) or ?1 is null) " +
            "and e.category.id in (?2) " +
            "and e.paid = ?3 " +
            "and e.eventDate > ?4 " +
            "and e.eventDate < ?5")
    List<Event> getFilterEvents(String text, List<Integer> categories,
                                  Boolean paid, LocalDateTime start, LocalDateTime end,
                                  Pageable pageable);
}